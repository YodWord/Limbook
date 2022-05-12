package com.daelim.Limbook.web.service.comments;

import com.daelim.Limbook.domain.Comment;
import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.controller.dto.CommentDTO.UpdateCommentDTO;
import com.daelim.Limbook.web.repository.Comments.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Override
    public Comment createComment(Comment comment, User user) throws Exception {
        return commentRepository.saveComment(comment, user);
    }

    @Override
    public Comment updateComment(Integer commentId, UpdateCommentDTO updateCommentDTO, User user) throws Exception {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);

        if(commentOptional.isEmpty()){
            throw new Exception("해당 댓글을 찾을 수 없습니다.");
        }

        Comment comment = commentOptional.get();

        if(!comment.getUserId().equals(user.getId())){
            throw new Exception("작성자만 수정 할 수 있습니다.");
        }

        return commentRepository.updateComment(commentId, updateCommentDTO, user);
    }

    @Override
    public Comment deleteComment(Integer commentId, User user) throws Exception {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);

        if(commentOptional.isEmpty()){
            throw new Exception("해당 댓글을 찾을 수 없습니다.");
        }

        Comment comment = commentOptional.get();

        if(!comment.getUserId().equals(user.getId())){
            throw new Exception("작성자만 삭제 할 수 있습니다.");
        }

        return commentRepository.deleteComment(commentId, user);
    }


}
