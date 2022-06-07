package com.daelim.Limbook.web.repository.boards;

import com.daelim.Limbook.domain.Board;
import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.controller.dto.BoardDTO.CreateBoardDTO;
import com.daelim.Limbook.web.controller.dto.BoardDTO.UpdateBoardDTO;
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

        Number key = null;

        Board board = new Board();
        log.info(createBoardDTO.getBoard_title());
        board.setUser_id(user.getUser_id());
        board.setBoard_title(createBoardDTO.getBoard_title());
        board.setBoard_contents(createBoardDTO.getBoard_contents());
        board.setBoard_price(createBoardDTO.getBoard_price());
        board.setBoard_create_date(Timestamp.valueOf(LocalDateTime.now()));


        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("board").usingGeneratedKeyColumns("board_id");

        Map<String, Object> params = new HashMap<>();
        params.put("user_id", board.getUser_id());
        params.put("board_title", board.getBoard_title());
        params.put("board_contents", board.getBoard_contents());
        params.put("board_price", board.getBoard_price());
        params.put("board_created_date", board.getBoard_create_date());

        log.info("board repository 실행" + params);

        try {
            key = jdbcInsert.executeAndReturnKey(params);
        }catch (Exception e) {
            throw new Exception("DB 에러");
        }


        return findById(key.intValue()).get();
    }

    @Override
    public Board updateBoard(UpdateBoardDTO updateBoardDTO, Integer boardId, User user) throws Exception {
        String sql = "update board set board_title = ?, board_contents = ?, board_price= ? where board_id = ?";

        try {
            jdbcTemplate.update(sql, updateBoardDTO.getBoard_title() ,updateBoardDTO.getBoard_contents(), updateBoardDTO.getBoard_price(), boardId);
        }catch (Exception e) {
            throw new Exception("DB 에러!");
        }

        return findById(boardId).get();
    }

    @Override
    public Board deleteBoard(Integer boardId, User user) throws Exception {
        Board board = findById(boardId).get();

        String sql = "delete from board where board_id = ?";

        try{
            jdbcTemplate.update(sql, boardId);
        }catch (Exception e){
            throw new Exception("DB에러!");
        }

        return board;
    }

    @Override
    public Optional<Board> findById(Integer id) throws Exception {

        String sql = "select * from board where board_id = ?";

        List<Board> result = jdbcTemplate.query(sql, boardRowMapper(), id);

        return result.stream().findAny();
    }

    @Override
    public List<Board> findAll() throws Exception {
        String sql = "select * from board";

        return jdbcTemplate.query(sql, boardRowMapper());
    }

    private RowMapper<Board> boardRowMapper()  {
        return (rs, rowNum)  -> {
            Board board = new Board();
            board.setBoard_number(rs.getInt("board_id"));
            board.setUser_id(rs.getString("user_id"));
            board.setBoard_title(rs.getString("board_title"));
            board.setBoard_contents(rs.getString("board_contents"));
            board.setBoard_price(rs.getInt("board_price"));
            board.setBoard_create_date(rs.getTimestamp("board_created_date"));
            return board;
        };
    }

}
