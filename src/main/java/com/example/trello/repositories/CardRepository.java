package com.example.trello.repositories;

import com.example.trello.model.Board;
import com.example.trello.model.Card;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends PagingAndSortingRepository<Card,Long> {
    List<Card> findByBoard(Board board);

    Card findOneById(Long id);
}
