package com.example.trello.service.mapper;

import com.example.trello.dto.Workspace_URDTO;
import com.example.trello.model.Workspace_UR;
import com.example.trello.repositories.RoleRepository;
import com.example.trello.repositories.UserRepository;
import com.example.trello.repositories.WorkspaceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Workspace_URMapper implements EntityMapper<Workspace_URDTO, Workspace_UR>{
    private final WorkspaceRepository workspaceRepository;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public Workspace_URMapper(WorkspaceRepository workspaceRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.workspaceRepository = workspaceRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public Workspace_UR toEntity(Workspace_URDTO dto) {
        if (dto == null) {
            return null;
        }
        Workspace_UR entity = new Workspace_UR();
        BeanUtils.copyProperties(dto, entity);
        entity.setWorkspace(workspaceRepository.findOneById(dto.getWorkspaceId()));
        entity.setRole(roleRepository.findOneByContent(dto.getRole()));
        entity.setUser(userRepository.findOneByUserName(dto.getUserName()));
        return entity;
    }

    @Override
    public Workspace_URDTO toDto(Workspace_UR entity) {
        if (entity == null) {
            return null;
        }

        Workspace_URDTO dto = new Workspace_URDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setUserName(entity.getUser().getUserName());
        dto.setRole(entity.getRole().getContent());
        dto.setWorkspaceId(entity.getWorkspace().getId());
        return dto;
    }

    @Override
    public List<Workspace_UR> toEntity(List<Workspace_URDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<Workspace_URDTO> toDto(List<Workspace_UR> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
