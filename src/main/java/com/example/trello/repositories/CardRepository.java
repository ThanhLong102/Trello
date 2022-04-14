package com.example.trello.repositories;

import com.example.trello.model.Card;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CardRepository extends PagingAndSortingRepository<Card,Long> {
}
