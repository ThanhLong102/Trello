package com.example.trello.repositories;

import com.example.trello.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String userName);

    User findOneByUserName(String userName);

    User findOneByEmail(String username);

    User findOneById(Long contactId);

    Optional<User> findByPhoneNumber(String phoneNumber);

    User findOneByPhoneNumber(String phoneNumber);

}
