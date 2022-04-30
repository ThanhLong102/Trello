package com.example.trello.repositories;

import com.example.trello.model.Board;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends PagingAndSortingRepository<Board, Long> {
    Board findOneById(Long boardId);
}
