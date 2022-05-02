package com.daelim.Limbook.web.service;

import com.daelim.Limbook.web.controller.dto.UserDTO.UserLoginDTO;
import com.daelim.Limbook.domain.User;

public interface UserService {

    //화원가입
    public User signUp(User user) throws Exception;

    public User login(UserLoginDTO userLoginDTO) throws Exception;



}
