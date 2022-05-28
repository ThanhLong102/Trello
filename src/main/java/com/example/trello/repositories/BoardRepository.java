package com.example.trello.repositories;

import com.example.trello.model.Board;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends PagingAndSortingRepository<Board, Long> {
    Board findOneById(Long boardId);

    List<Board> findByTitleContainingIgnoreCase(String title);
}
