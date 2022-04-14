package com.example.trello.repositories;

import com.example.trello.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    User findOneById(Long id);

    User findOneByUserName(String userName);
}
