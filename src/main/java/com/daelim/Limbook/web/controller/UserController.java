package com.daelim.Limbook.web.controller;

import com.daelim.Limbook.web.controller.dto.UserSignUpDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
@RestController
public class UserController {

    @PostMapping
    public ResponseEntity<Object> signUp(@RequestBody @Validated UserSignUpDTO userSignUpDTO, BindingResult bindingResult)throws Exception{
        return null;
    }

}
