package com.daelim.Limbook.web.controller.dto.UserDTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserSignUpDTO {

    @NotBlank
    private String user_id;
    @NotBlank
    private String user_pw;
    @NotBlank
    private String user_email;

}
