package com.daelim.Limbook.web.service;

import com.daelim.Limbook.web.controller.dto.UserLoginDTO;
import com.daelim.Limbook.web.domain.User;

import java.util.Optional;

public interface UserService {

    //화원가입
    public User signUp(User user) throws Exception;

    public User login(UserLoginDTO userLoginDTO) throws Exception;



}
