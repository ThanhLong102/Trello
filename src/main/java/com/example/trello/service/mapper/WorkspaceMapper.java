package com.example.trello.service.mapper;

import com.example.trello.dto.WorkspaceDTO;
import com.example.trello.model.Workspace;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkspaceMapper implements EntityMapper<WorkspaceDTO, Workspace>{
    @Override
    public Workspace toEntity(WorkspaceDTO dto) {
        if (dto == null) {
            return null;
        }
        Workspace entity = new Workspace();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public WorkspaceDTO toDto(Workspace entity) {
        if (entity == null) {
            return null;
        }

        WorkspaceDTO dto = new WorkspaceDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public List<Workspace> toEntity(List<WorkspaceDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<WorkspaceDTO> toDto(List<Workspace> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
