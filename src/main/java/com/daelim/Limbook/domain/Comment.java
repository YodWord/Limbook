package com.daelim.Limbook.domain;

import com.daelim.Limbook.web.controller.dto.CommentDTO.CreateCommentDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class Comment {

    private Integer commentNumber;
    private Integer boardNumber;
    private String userId;
    private String boardCommentContents;
    private Timestamp boardCreatedAt;

    public Comment(CreateCommentDTO createCommentDTO, User user) {
        this.boardNumber = createCommentDTO.getBoardId();
        this.boardCommentContents = createCommentDTO.getComment();
        this.userId = user.getId();
        this.boardCreatedAt = Timestamp.valueOf(LocalDateTime.now());
    }
}
