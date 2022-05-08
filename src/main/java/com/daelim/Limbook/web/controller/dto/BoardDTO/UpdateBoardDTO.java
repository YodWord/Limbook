package com.daelim.Limbook.web.controller.dto.BoardDTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter @Setter
public class UpdateBoardDTO {

    @NotNull
    private String board_title;

    @NotNull
    private String board_contents;

}
