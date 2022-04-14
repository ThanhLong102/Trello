package com.example.trello.service;

import com.example.trello.converter.UserConverter;
import com.example.trello.dto.UserDto;
import com.example.trello.model.User;
import com.example.trello.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDto update(UserDto userDto) {
        User user1;
        if (userDto.getId() != null) {
            User oldUser = userRepository.findOneById(userDto.getId());
            user1 = userConverter.toEntity(userDto,oldUser);
        } else {
            user1 = userConverter.toEntity(userDto);
        }
        user1 = userRepository.save(user1);
        return userConverter.toDto(user1);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> display() {
        List<User> userList = (List<User>) userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList){
            userDtoList.add(userConverter.toDto(user));
        }
        return userDtoList;
    }

}
