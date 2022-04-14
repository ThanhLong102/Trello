package com.example.trello.converter;

import com.example.trello.dto.UserDto;
import com.example.trello.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUserName(userDto.getUserName());
        user.setFirstName(userDto.getFirstName());
        user.setIsEnabled(userDto.getIsEnabled());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public User toEntity(UserDto dto, User entity) {
        entity.setUserName(dto.getUserName());
        entity.setIsEnabled(dto.getIsEnabled());
        entity.setLastName(dto.getLastName());
        entity.setFirstName(dto.getFirstName());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        return entity;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUserName(user.getUserName());
        userDto.setFirstName(user.getFirstName());
        userDto.setIsEnabled(user.getIsEnabled());
        userDto.setLastName(user.getLastName());
        return userDto;
    }
}
