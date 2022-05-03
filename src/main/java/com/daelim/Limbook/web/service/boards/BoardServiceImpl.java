package com.daelim.Limbook.web.service.boards;

import com.daelim.Limbook.domain.Board;
import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.controller.dto.BoardDTO.CreateBoardDTO;
import com.daelim.Limbook.web.repository.boards.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public Board createBoard(CreateBoardDTO createBoardDTO, User user) throws Exception {
        return boardRepository.saveBoard(createBoardDTO, user);
    }
}
