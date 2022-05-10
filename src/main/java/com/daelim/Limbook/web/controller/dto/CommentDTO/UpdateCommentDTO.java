package com.daelim.Limbook.web.controller.dto.CommentDTO;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter @Setter
public class UpdateCommentDTO {

    @NotNull
    private String comment;

}
