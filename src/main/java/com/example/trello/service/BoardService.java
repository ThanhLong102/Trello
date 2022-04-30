package com.example.trello.service;

import com.example.trello.dto.BoardDTO;
import org.springframework.transaction.annotation.Transactional;

public interface BoardService {

    BoardDTO save(BoardDTO boardDTO);

    @Transactional(readOnly = true)
    BoardDTO findOne(Long id);

    void delete(Long id);
}
