package com.daelim.Limbook.web.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserLoginDTO {

    @NotBlank
    String user_id;
    @NotBlank
    String user_pw;

}
