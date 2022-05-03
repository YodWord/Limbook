package com.daelim.Limbook.web.service.boards;


import com.daelim.Limbook.domain.Board;
import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.controller.dto.BoardDTO.CreateBoardDTO;

public interface BoardService {

    public Board createBoard(CreateBoardDTO createBoardDTO, User user) throws Exception;

}
