package com.daelim.Limbook.web.controller.dto.BoardDTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter @Setter
public class UpdateBoardDTO {

    @NotBlank
    private String board_title;

    @NotBlank
    private String board_contents;

    @NotNull
    private Integer board_price;




}
