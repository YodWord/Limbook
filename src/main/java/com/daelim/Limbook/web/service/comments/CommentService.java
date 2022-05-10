package com.daelim.Limbook.web.service.comments;

import com.daelim.Limbook.domain.Comment;
import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.controller.dto.CommentDTO.CreateCommentDTO;

public interface CommentService {

    public Comment createComment(Comment comment, User user) throws Exception;

}
