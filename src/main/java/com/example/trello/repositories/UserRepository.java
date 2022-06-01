package com.example.trello.repositories;

import com.example.trello.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String userName);

    User findOneByUserName(String userName);

    User findOneByEmail(String username);

    User findOneById(Long contactId);

    User findOneByPhoneNumber(String phoneNumber);

    List<User> findByEmailContainingIgnoreCase(String email);
}
