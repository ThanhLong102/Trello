package com.example.trello.repositories;

import com.example.trello.model.Board;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BoardRepository extends PagingAndSortingRepository<Board, Long> {
}
