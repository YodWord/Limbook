package com.daelim.Limbook.web.controller;

import com.daelim.Limbook.web.argumentResolver.Login;
import com.daelim.Limbook.domain.Board;
import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.controller.dto.BoardDTO.CreateBoardDTO;
import com.daelim.Limbook.web.service.boards.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RequestMapping("/boards")
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public HashMap<String, Object> createBoard(@RequestBody @Validated CreateBoardDTO createBoardDTO,
                                               BindingResult bindingResult, @Login User user)throws Exception{

        HashMap <String, Object> response = new HashMap<>();

        if(user == null){
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        if(bindingResult.hasErrors()){
            response.put("result", "실패");
        }

        Board board = boardService.createBoard(createBoardDTO, user);

        response.put("result", "성공");
        response.put("board",board);


        return response;
    }


}
