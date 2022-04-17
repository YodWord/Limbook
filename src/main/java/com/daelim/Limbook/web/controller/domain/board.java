package com.daelim.Limbook.web.controller.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class board {

    private Integer number;
    private String title;
    private String contents;
    private Timestamp createdAt;

}
