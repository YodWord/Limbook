package com.daelim.Limbook.web.controller.dto.BoardDTO;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateBoardDTO {

    @NotNull
    private String board_title;

    @NotNull
    private String board_contents;

}
