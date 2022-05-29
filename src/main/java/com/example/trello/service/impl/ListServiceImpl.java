package com.example.trello.service.impl;

import com.example.trello.dto.CardDTO;
import com.example.trello.dto.ListDTO;
import com.example.trello.repositories.BoardRepository;
import com.example.trello.repositories.CardRepository;
import com.example.trello.repositories.ListRepository;
import com.example.trello.service.ListService;
import com.example.trello.service.mapper.CardMapper;
import com.example.trello.service.mapper.ListMapper;
import com.example.trello.web.vm.ListVm;
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

    private final CardMapper cardMapper;

    private final ListRepository listRepository;

    private final CardRepository cardRepository;

    public ListServiceImpl(BoardRepository boardRepository, ListMapper listMapper, CardMapper cardMapper, ListRepository listRepository, CardRepository cardRepository) {
        this.boardRepository = boardRepository;
        this.listMapper = listMapper;
        this.cardMapper = cardMapper;
        this.listRepository = listRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public ListVm save(ListDTO listDTO){
        log.debug("Request to save list : {}", listDTO);
        com.example.trello.model.List list = listMapper.toEntity(listDTO);
        list = listRepository.save(list);
        List<CardDTO> cardDTOList = cardMapper.toDto(cardRepository.findByList(list));
        ListVm listVm = new ListVm(listMapper.toDto(list),cardDTOList);
        return listVm;
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
        listRepository.deleteById(id);
    }
}
