package com.example.trello.converter;

import com.example.trello.dto.UserRoleDto;
import com.example.trello.model.UserRole;
import com.example.trello.repositories.RoleRepository;
import com.example.trello.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRoleConverter {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserRole toEntity(UserRoleDto userRoleDto) {
        UserRole userRole = new UserRole();
        userRole.setUser(userRepository.findOneByUserName(userRoleDto.getUserName()));
        userRole.setRole(roleRepository.findOneByContent(userRoleDto.getRole()));
        return userRole;
    }

    public UserRole toEntity(UserRoleDto dto, UserRole entity) {
        entity.setRole(roleRepository.findOneByContent(dto.getRole()));
        return entity;
    }

    public UserRoleDto toDto(UserRole userRole) {
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setUserName(userRole.getUser().getUserName());
        userRoleDto.setRole(userRole.getRole().getContent());
        return userRoleDto;
    }
}
