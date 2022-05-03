package com.daelim.Limbook.web.controller;

import com.daelim.Limbook.argumentResolver.Login;
import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.controller.dto.BoardDTO.CreateBoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
@RestController
public class BoardController {

    @PostMapping
    public HashMap<String, Object> createBoard(@RequestBody @Validated CreateBoardDTO createBoardDTO, BindingResult bindingResult, @Login User user){



        return null;
    }


}
