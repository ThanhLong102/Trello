package com.example.trello.service;

import com.example.trello.model.Board;
import com.example.trello.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public List<Board> display() {
        return (List<Board>) boardRepository.findAll();
    }

    @Override
    public void delete(long id) {
        boardRepository.deleteById(id);
    }

    @Override
    public Board update(Board board){
        return boardRepository.save(board);
    }
}
