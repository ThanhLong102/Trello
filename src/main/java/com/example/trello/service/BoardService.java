package com.example.trello.service;

import com.example.trello.dto.BoardDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardService {

    BoardDTO save(BoardDTO boardDTO);

    @Transactional(readOnly = true)
    BoardDTO findOne(Long id);

    List<BoardDTO> findAllByWorkspace(Long id);

    void delete(Long id);
}
