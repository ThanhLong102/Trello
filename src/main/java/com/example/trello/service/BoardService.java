package com.example.trello.service;

import com.example.trello.dto.BoardDTO;
import com.example.trello.web.vm.BoardVm;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardService {

    BoardDTO save(BoardDTO boardDTO);

    List<BoardDTO> search(BoardDTO boardDTO, String token);

    @Transactional(readOnly = true)
    BoardVm findOne(Long id);

    void delete(Long id);
}
