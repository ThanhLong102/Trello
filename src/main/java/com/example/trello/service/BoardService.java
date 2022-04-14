package com.example.trello.service;

import com.example.trello.model.Board;

import java.util.List;

public interface BoardService {
    List<Board> display();

    void delete(long id);

    Board update(Board board);
}
