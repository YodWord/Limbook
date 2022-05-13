package com.daelim.Limbook.web.repository.Comments;

import com.daelim.Limbook.domain.Comment;
import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.controller.dto.CommentDTO.UpdateCommentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class CommentRepositoryImpl implements CommentRepository{

    private final JdbcTemplate jdbcTemplate;

    public CommentRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Comment saveComment(Comment comment, User user) throws Exception {

        Number key = null;

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("board_comment").usingGeneratedKeyColumns("board_comment_number");

        Map<String, Object> params = new HashMap<>();
        params.put("board_number", comment.getBoardNumber());
        params.put("user_id", user.getId());
        params.put("board_comment_contents", comment.getBoardCommentContents());
        params.put("board_comment_create_date", comment.getBoardCreatedAt());

        try{
            key = jdbcInsert.executeAndReturnKey(params);
        }catch (Exception e){
            throw new Exception("DB오류!");
        }

        return findById(key.intValue()).get();
    }

    @Override
    public Comment updateComment(Integer commentId, UpdateCommentDTO updateCommentDTO, User user) throws Exception {
        String sql = "update board_comment set board_comment_contents = ? where board_comment_number = ? ";

        try{
            jdbcTemplate.update(sql, updateCommentDTO.getComment(), commentId);
        }catch(Exception e){
            throw new Exception("DB오류!");
        }

        return findById(commentId.intValue()).get();
    }

    @Override
    public Comment deleteComment(Integer commentId, User user) throws Exception {
        Comment comment = findById(commentId).get();

        String sql = "delete from board_comment where board_comment_number = ?";

        try{
            jdbcTemplate.update(sql, commentId);
        }catch (Exception e){
            throw new Exception("DB오류!");
        }

        return comment;
    }

    @Override
    public List<Comment> findByBoardId(Integer boardId) throws Exception {
        String sql = "select * from board_comment where board_number = ?";

        List<Comment> result = jdbcTemplate.query(sql, commentRowMapper(), boardId);

        return result;
    }

    @Override
    public Optional<Comment> findById(Integer commentId) throws Exception {
        String sql = "select * from board_comment where board_comment_number = ?";

        List<Comment> result = jdbcTemplate.query(sql, commentRowMapper(), commentId);

        return result.stream().findAny();
    }

    private RowMapper<Comment> commentRowMapper() {
        return (rs, rowNum) -> {
            Comment comment = new Comment();
            comment.setCommentNumber(rs.getInt("board_comment_number"));
            comment.setUserId(rs.getString("user_id"));
            comment.setBoardNumber(rs.getInt("board_number"));
            comment.setBoardCommentContents(rs.getString("board_comment_contents"));
            comment.setBoardCreatedAt(rs.getTimestamp("board_comment_create_date"));

            return comment;
        };
    }

}
