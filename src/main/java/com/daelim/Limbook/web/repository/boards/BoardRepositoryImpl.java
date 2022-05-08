package com.daelim.Limbook.web.repository.boards;

import com.daelim.Limbook.domain.Board;
import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.controller.dto.BoardDTO.CreateBoardDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class BoardRepositoryImpl implements BoardRepository{

    private final JdbcTemplate jdbcTemplate;

    public BoardRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Board saveBoard(CreateBoardDTO createBoardDTO, User user) throws Exception {

        Board board = new Board();

        board.setUser_id(user.getId());
        board.setBoard_title(createBoardDTO.getBoard_title());
        board.setBoard_contents(createBoardDTO.getBoard_contents());
        board.setBoard_create_date(Timestamp.valueOf(LocalDateTime.now()));

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("board").usingGeneratedKeyColumns("board_number");

        Map<String, Object> params = new HashMap<>();
        params.put("user_id", board.getUser_id());
        params.put("board_title", board.getBoard_title());
        params.put("board_contents", board.getBoard_contents());
        params.put("board_create_date", board.getBoard_create_date());

        log.info("board repository 실행" + params);

        jdbcInsert.execute(params);

        return board;
    }


    @Override
    public Optional<Board> findById(Integer id) throws Exception {

        String sql = "select * from board where board_number = ?";

        List<Board> result = jdbcTemplate.query(sql, boardRowMapper(), id);

        return result.stream().findAny();
    }

    private RowMapper<Board> boardRowMapper()  {
        return (rs, rowNum)  -> {
            Board board = new Board();
            board.setBoard_number(rs.getInt("board_number"));
            board.setUser_id(rs.getString("user_id"));
            board.setBoard_title(rs.getString("board_title"));
            board.setBoard_contents(rs.getString("board_contents"));
            board.setBoard_create_date(rs.getTimestamp("board_create_date"));
            return board;
        };
    }
}
