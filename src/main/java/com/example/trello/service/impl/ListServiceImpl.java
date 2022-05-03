package com.example.trello.service.impl;

import com.example.trello.dto.ListDTO;
import com.example.trello.repositories.BoardRepository;
import com.example.trello.repositories.ListRepository;
import com.example.trello.service.ListService;
import com.example.trello.service.mapper.ListMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ListServiceImpl implements ListService {
    private final Logger log = LoggerFactory.getLogger(ListServiceImpl.class);

    private final BoardRepository boardRepository;

    private final ListMapper listMapper;

    private final ListRepository listRepository;

    public ListServiceImpl(BoardRepository boardRepository, ListMapper listMapper, ListRepository listRepository) {
        this.boardRepository = boardRepository;
        this.listMapper = listMapper;
        this.listRepository = listRepository;
    }

    @Override
    public ListDTO save(ListDTO listDTO){
        log.debug("Request to save list : {}", listDTO);
        com.example.trello.model.List list = listMapper.toEntity(listDTO);
        list = listRepository.save(list);
        return listMapper.toDto(list);
    }

    @Transactional(readOnly = true)
    @Override
    public ListDTO findOne(Long id) {
        log.debug("Request to get list : {}", id);
        return listMapper.toDto(listRepository.findOneById(id));
    }

    @Override
    public List<ListDTO> findAllByBoard(Long id) {
        log.debug("Request to get list : {}", id);
        return listMapper.toDto(listRepository.findByBoard(boardRepository.findOneById(id)));
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete list : {}", id);
        boardRepository.deleteById(id);
    }
}
