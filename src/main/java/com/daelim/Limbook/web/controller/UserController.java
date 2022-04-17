package com.daelim.Limbook.web.controller;

import com.daelim.Limbook.web.controller.domain.User;
import com.daelim.Limbook.web.controller.dto.UserSignUpDTO;
import com.daelim.Limbook.web.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    public HashMap<String, Object> signUp(@RequestBody @Validated UserSignUpDTO userSignUpDTO, BindingResult bindingResult)throws Exception{

        HashMap<String, Object> response = new HashMap<>();

        User user = new User(userSignUpDTO);

        userService.signUp(user);

        HashMap<String, Object> userResponse = new HashMap<>();
        userResponse.put("user_id", user.getId());
        userResponse.put("user_pw", user.getPw());
        userResponse.put("user_name", user.getName());
        userResponse.put("user_phone", user.getPhone());
        userResponse.put("user_department", user.getDepartment());

        response.put("result","성공");
        response.put("user", userResponse);

        return response;
    }

}
