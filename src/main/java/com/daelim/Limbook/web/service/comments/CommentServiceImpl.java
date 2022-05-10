package com.daelim.Limbook.web.service.comments;

import com.daelim.Limbook.domain.Comment;
import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.controller.dto.CommentDTO.CreateCommentDTO;
import com.daelim.Limbook.web.repository.Comments.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Override
    public Comment createComment(Comment comment, User user) throws Exception {
        return commentRepository.saveComment(comment, user);
    }
}
