package com.daelim.Limbook.domain;

import com.daelim.Limbook.web.controller.dto.UserDTO.UserSignUpDTO;
import lombok.*;

@NoArgsConstructor
@Getter @Setter @ToString
public class User {

    private String id;
    private String pw;
    private String name;
    private String phone;
    private String department;

    public User(UserSignUpDTO userSignUpDTO) {
        this.id = userSignUpDTO.getUser_id();
        this.pw = userSignUpDTO.getUser_pw();
        this.name = userSignUpDTO.getUser_name();
        this.phone = userSignUpDTO.getUser_phone();
        this.department = userSignUpDTO.getUser_department();
    }

    public static User createUserByDTO(UserSignUpDTO userSignUpDTO) {
        User user = new User();
        user.id = userSignUpDTO.getUser_id();
        user.pw = userSignUpDTO.getUser_pw();
        user.name = userSignUpDTO.getUser_name();
        user.phone = userSignUpDTO.getUser_phone();
        user.department = userSignUpDTO.getUser_department();
        return user;
    }
}

