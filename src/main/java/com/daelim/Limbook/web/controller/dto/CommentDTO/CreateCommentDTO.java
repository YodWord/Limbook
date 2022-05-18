package com.daelim.Limbook.web.controller.dto.CommentDTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter @Setter
public class CreateCommentDTO {

    @NotBlank
    private Integer boardId;

    @NotBlank
    private String comment;

}
