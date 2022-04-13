package com.daelim.Limbook.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserSignUpDTO {

    @NotBlank
    private String user_id;
    @NotBlank
    private String user_pw;
    @NotBlank
    private String user_name;
    @NotBlank
    private String user_phone;
    @NotBlank
    private String user_department;

}
