package com.example.trello.web.rest;

import com.example.trello.core.Constants;
import com.example.trello.dto.Workspace_URDTO;
import com.example.trello.service.Workspace_URService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.USER+"/workspaceURs")
@Api(tags = "WorkspaceUR")
@CrossOrigin(origins = "*", maxAge = 3600)
public class Workspace_URController {
    private final Workspace_URService workspace_urService;

    public Workspace_URController(Workspace_URService workspace_urService) {
        this.workspace_urService = workspace_urService;
    }

    @PostMapping
    public ResponseEntity<Workspace_URDTO> add(@Valid @RequestBody Workspace_URDTO workspace_urdto) {
        return ResponseEntity.ok().body(workspace_urService.save(workspace_urdto));
    }

    @PutMapping
    public ResponseEntity<Workspace_URDTO> update(@Valid @RequestBody Workspace_URDTO workspace_urdto) {
        return ResponseEntity.ok().body(workspace_urService.save(workspace_urdto));
    }

    @GetMapping("/id={id}")
    public ResponseEntity<Workspace_URDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(workspace_urService.findOne(id));
    }

    @PostMapping("/workspaces")
    public ResponseEntity<List<Workspace_URDTO>> findAllBoardByUserNameAndRole(@Valid @RequestBody Workspace_URDTO workspace_urdto) {
        return ResponseEntity.ok().body(workspace_urService.findAllWorkspaceByUserNameAndRole(workspace_urdto));
    }

    @DeleteMapping("/id={id}")
    public void delete(@PathVariable("id") Long id) {
        workspace_urService.delete(id);
    }
}
