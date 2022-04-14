package com.example.trello.service;

import com.example.trello.dto.UserRoleDto;

public interface UserRoleService {
    UserRoleDto update(UserRoleDto userRoleDto);

    void delete(long id);
}
