package com.daelim.Limbook.web.controller;

import com.daelim.Limbook.web.argumentResolver.Login;
import com.daelim.Limbook.domain.Board;
import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.controller.dto.BoardDTO.CreateBoardDTO;
import com.daelim.Limbook.web.controller.dto.BoardDTO.UpdateBoardDTO;
import com.daelim.Limbook.web.service.boards.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RequestMapping("/boards")
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    /**
    *   게시글 생성
    *
    * */
    @PostMapping
    public HashMap<String, Object> createBoard(@RequestBody @Validated CreateBoardDTO createBoardDTO, BindingResult bindingResult,
                                               /*@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User user*/
                                               @Login User user
                                               ) throws Exception{

        HashMap <String, Object> response = new HashMap<>();

        if(user == null){
            throw new Exception("로그인이 필요합니다.");
        }

        if(bindingResult.hasErrors()){
            response.put("result", "실패");
            return response;
        }

        log.info("board controller 실행" + user);
        Board board = boardService.createBoard(createBoardDTO, user);

        response.put("result", "성공");
        response.put("board",board);


        return response;
    }

    /**
     *   게시글 수정
     *
     * */
    @PatchMapping("/{boardId}")
    public HashMap<String, Object> updateBoard(@RequestBody @Validated UpdateBoardDTO updateBoardDTO, BindingResult bindingResult,
                                               @PathVariable Integer boardId,
                                               /* @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User user*/
                                               @Login User user) throws Exception{

        HashMap <String, Object> response = new HashMap<>();

        if(user == null){
            response.put("result", "로그인이 필요합니다.");
            return response;
            //throw new Exception("로그인이 필요합니다.");
        }

        if(bindingResult.hasErrors()){
            response.put("result", "입력값 확인 필요.");
            return response;
        }

        Board board = boardService.updateBoard(updateBoardDTO, boardId,user);

        response.put("result","성공");
        response.put("board",board);

        return response;
    }

    /**
     *   게시글 삭제
     *
     * */
    @DeleteMapping("/{boardId}")
    public HashMap<String, Object> deleteBoard(@PathVariable Integer boardId,
                                               @Login User user
                                               /*@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User user*/) throws Exception{

        HashMap<String, Object> response = new HashMap<>();

        if(user == null){
            response.put("result", "로그인이 필요합니다.");
            return response;
            //throw new Exception("로그인이 필요합니다");
        }

        Board board = boardService.deleteBoard(boardId, user);

        response.put("result", "성공");
        response.put("board", board);

        return response;
    }

    /**
     *   게시글 하나 조회
     *
     * */
    @GetMapping("/{boardId}")
    public HashMap<String, Object> findBoardById(@PathVariable Integer boardId) throws Exception {

        Board board = boardService.findById(boardId);

        HashMap<String, Object> response = new HashMap<>();

        response.put("result", "성공");
        response.put("board",board);

        return response;
    }

    /**
     *   게시글 전체 조회
     *
     * */
    //Service 에서 Optional 안써도 될까...
    @GetMapping
    public HashMap<String, Object> findAllBoard() throws Exception{

        List<Board> boardList = boardService.findAll();

        HashMap<String, Object> response = new HashMap<>();

        response.put("result", "성공");
        response.put("board",boardList);

        return response;
    }

}
