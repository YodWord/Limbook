package com.daelim.Limbook.web.repository.Comments;

import com.daelim.Limbook.domain.Comment;
import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.controller.dto.CommentDTO.UpdateCommentDTO;

import java.util.Optional;

public interface CommentRepository {

    public Comment saveComment(Comment comment, User user) throws Exception;

    public Comment updateComment(Integer commentId, UpdateCommentDTO updateCommentDTO, User user) throws Exception;

    public Comment deleteComment(Integer commentId, User user) throws Exception;

    public Optional<Comment> findById(Integer commentId) throws Exception;

}
