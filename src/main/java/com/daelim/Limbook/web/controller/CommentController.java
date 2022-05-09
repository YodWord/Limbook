package com.daelim.Limbook.web.controller;

import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.SessionConst;
import com.daelim.Limbook.web.controller.dto.CommentDTO.CreateCommentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RequestMapping("/comments")
@RequiredArgsConstructor
@RestController
public class CommentController {
    
    //댓글작성
    @PostMapping
    public HashMap<String, Object> createComment (@RequestBody @Validated CreateCommentDTO createCommentDTO, BindingResult bindingResult,
                                                  @SessionAttribute(value = SessionConst.LOGIN_USER, required = false) User user) throws Exception{

        HashMap<String, Object> response = new HashMap<>();

        if(user == null){
            throw new Exception("로그인이 필요합니다.");
        }
        if(bindingResult.hasErrors()){
            response.put("result", "실패");
            return response;
        }



        return null;
    }
    
    //댓글수정

    
    //댓글삭제
    
    
}
