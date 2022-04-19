package com.daelim.Limbook.web.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class Comment {

    private Integer commentNumber;
    private String boardNumber;
    private String userId;
    private String boardComment;
    private Timestamp boardCreatedAt;

}
