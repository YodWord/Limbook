package com.daelim.Limbook.web.service.comments;

import com.daelim.Limbook.domain.Comment;
import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.controller.dto.CommentDTO.CreateCommentDTO;
import com.daelim.Limbook.web.controller.dto.CommentDTO.UpdateCommentDTO;

import java.util.Optional;

public interface CommentService {

    public Comment createComment(Comment comment, User user) throws Exception;

    public Comment updateComment(Integer commentId, UpdateCommentDTO updateCommentDTO, User usre) throws Exception;

}
