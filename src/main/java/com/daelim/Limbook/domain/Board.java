package com.daelim.Limbook.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class Board {

    private Integer board_number;
    private String user_id;
    private String board_title;
    private String board_contents;
    private Timestamp board_create_date;

}
