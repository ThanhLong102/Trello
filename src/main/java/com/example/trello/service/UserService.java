package com.example.trello.service;

import com.example.trello.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto update(UserDto userDto);

    void delete(long id);

    List<UserDto> display();
}
