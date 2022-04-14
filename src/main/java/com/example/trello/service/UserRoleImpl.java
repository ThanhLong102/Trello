package com.example.trello.service;

import com.example.trello.converter.UserRoleConverter;
import com.example.trello.dto.UserRoleDto;
import com.example.trello.model.UserRole;
import com.example.trello.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleImpl implements UserRoleService{

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRoleConverter userRoleConverter;


    @Override
    public UserRoleDto update(UserRoleDto userRoleDto) {
        UserRole userRole;
        if (userRoleDto.getId()!= null) {
            UserRole oldUserRole = userRoleRepository.findOneById(userRoleDto.getId());
            userRole = userRoleConverter.toEntity(userRoleDto,oldUserRole);
        } else {
            userRole = userRoleConverter.toEntity(userRoleDto);
        }
        userRole = userRoleRepository.save(userRole);
        return userRoleConverter.toDto(userRole);
    }

    @Override
    public void delete(long id) {
        userRoleRepository.deleteById(id);
    }
}
