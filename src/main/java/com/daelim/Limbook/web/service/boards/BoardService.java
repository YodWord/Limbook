package com.daelim.Limbook.web.service.boards;


import com.daelim.Limbook.domain.Board;
import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.controller.dto.BoardDTO.CreateBoardDTO;
import com.daelim.Limbook.web.controller.dto.BoardDTO.UpdateBoardDTO;

public interface BoardService {

    public Board createBoard(CreateBoardDTO createBoardDTO, User user) throws Exception;

    public Board updateBoard(UpdateBoardDTO updateBoardDTO, Integer boardId ,User user) throws Exception;

    public Board deleteBoard(Integer boardId, User user) throws Exception;

}
