package com.example.trello.service;

import com.example.trello.dto.BoardDTO;
import com.example.trello.web.vm.BoardVm;
import org.springframework.transaction.annotation.Transactional;

public interface BoardService {

    BoardDTO save(BoardDTO boardDTO);

    @Transactional(readOnly = true)
    BoardVm findOne(Long id);

    void delete(Long id);
}
