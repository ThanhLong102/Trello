package com.example.trello.repositories;

import com.example.trello.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {

    Card findOneById(Long id);

    List<Card> findByList(com.example.trello.model.List oneById);
}
