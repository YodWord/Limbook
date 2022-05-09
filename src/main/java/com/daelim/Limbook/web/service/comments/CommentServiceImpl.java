package com.daelim.Limbook.web.service.comments;

import com.daelim.Limbook.domain.Comment;
import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.controller.dto.CommentDTO.CreateCommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    @Override
    public Comment createComment(CreateCommentDTO createCommentDTO, User user) throws Exception {


        return null;
    }
}
