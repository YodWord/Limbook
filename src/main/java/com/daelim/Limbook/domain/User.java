package com.daelim.Limbook.domain;

import com.daelim.Limbook.web.controller.dto.UserDTO.UserSignUpDTO;
import lombok.*;

@NoArgsConstructor
@Getter @Setter @ToString
public class User {

    private String user_id;
    private String user_pw;
    private String user_email;

    public User(UserSignUpDTO userSignUpDTO) {
        this.user_id = userSignUpDTO.getUser_id();
        this.user_pw = userSignUpDTO.getUser_pw();
        this.user_email = userSignUpDTO.getUser_email();
    }

    public static User createUserByDTO(UserSignUpDTO userSignUpDTO) {
        User user = new User();
        user.user_id = userSignUpDTO.getUser_id();
        user.user_pw = userSignUpDTO.getUser_pw();
        user.user_email = userSignUpDTO.getUser_email();
        return user;
    }
}

