package com.daelim.Limbook.web.service.boards;

import com.daelim.Limbook.domain.Board;
import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.controller.dto.BoardDTO.CreateBoardDTO;
import com.daelim.Limbook.web.controller.dto.BoardDTO.UpdateBoardDTO;
import com.daelim.Limbook.web.repository.boards.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public Board createBoard(CreateBoardDTO createBoardDTO, User user) throws Exception {
        return boardRepository.saveBoard(createBoardDTO, user);
    }

    @Override
    public Board updateBoard(UpdateBoardDTO updateBoardDTO, Integer boardId, User user) throws Exception {

        Optional<Board> boardOptional = boardRepository.findById(boardId);

        if(boardOptional.isEmpty()) throw new Exception("해당 글을 찾을수 없습니다.");

        Board board = boardOptional.get();

        if(!board.getUser_id().equals(user.getId())) throw new Exception("작성자만 수정 가능합니다.");

        return boardRepository.updateBoard(updateBoardDTO, boardId, user);
    }

    @Override
    public Board deleteBoard(Integer boardId, User user) throws Exception {

        Optional<Board> boardOptional = boardRepository.findById(boardId);

        if(boardOptional.isEmpty()){
            throw new Exception("board id에 해당하는 글을 찾을 수 없습니다.");
        }

        Board board = boardOptional.get();

        if(!board.getUser_id().equals(user.getId())){
            throw new Exception("작성자만 삭제 할 수 있습니다.");
        }

        return boardRepository.deleteBoard(boardId, user);
    }
}
