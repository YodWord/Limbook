package com.daelim.Limbook.web.controller.dto.BoardDTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@RequiredArgsConstructor
public class CreateBoardDTO {

    @NotBlank
    private String board_title;

    @NotBlank
    private String board_contents;

}
