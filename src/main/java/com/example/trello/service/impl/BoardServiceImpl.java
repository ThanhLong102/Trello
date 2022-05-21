package com.example.trello.service.impl;

import com.example.trello.dto.BoardDTO;
import com.example.trello.dto.ListDTO;
import com.example.trello.model.Board;
import com.example.trello.repositories.BoardRepository;
import com.example.trello.service.BoardService;
import com.example.trello.service.ListService;
import com.example.trello.service.mapper.BoardMapper;
import com.example.trello.web.vm.BoardVm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class BoardServiceImpl implements BoardService {
    private final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);

    private final BoardRepository boardRepository;

    private final BoardMapper boardMapper;

    private final ListService listService;


    public BoardServiceImpl(BoardRepository boardRepository, BoardMapper boardMapper, ListService listService) {
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
        this.listService = listService;
    }

    @Override
    public BoardDTO save(BoardDTO boardDTO){
        log.debug("Request to save board : {}", boardDTO);
        Board board = boardMapper.toEntity(boardDTO);
        board = boardRepository.save(board);
        return boardMapper.toDto(board);
    }

    @Transactional(readOnly = true)
    @Override
    public BoardVm findOne(Long id) {
        log.debug("Request to get board : {}", id);
        Board board = boardRepository.findOneById(id);
        List<ListDTO> lists = listService.findAllByBoard(id);
        return new BoardVm(board,lists);
    }


    @Override
    public void delete(Long id) {
        log.debug("Request to delete board : {}", id);
        boardRepository.deleteById(id);
    }
}
