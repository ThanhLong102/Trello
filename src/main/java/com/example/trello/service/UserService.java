package com.example.trello.service;

import com.example.trello.dto.MessageDto;
import com.example.trello.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    User findById(Long id);

    User findUserByUserName(String userName);

    User findByEmail(String email);

    User findByToken(String Token);

    List<User> findUserByRole(String role);

    User save(User user);

    MessageDto updateUser(User user);

    boolean detective(Long userId);

    void createNewUserAfterOAuthLoginSuccess(String email, String name);

    void updateNewUserAfterOAuthLoginSuccess(User user, String name);

    List<User> findByEmailContain(String email);

}
