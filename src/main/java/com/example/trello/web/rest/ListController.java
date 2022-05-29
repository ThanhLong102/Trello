package com.example.trello.web.rest;

import com.example.trello.core.Constants;
import com.example.trello.dto.ListDTO;
import com.example.trello.service.ListService;
import com.example.trello.web.vm.ListVm;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.USER+"/lists")
@Api(tags = "List")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ListController {
    private final ListService listService;

    public ListController(ListService listService) {
        this.listService = listService;
    }

    @PostMapping
    public ResponseEntity<ListVm> add(@Valid @RequestBody ListDTO listDTO) {
        return ResponseEntity.ok().body(listService.save(listDTO));
    }

    @PutMapping
    public ResponseEntity<ListVm> update(@Valid @RequestBody ListDTO listDTO) {
        return ResponseEntity.ok().body(listService.save(listDTO));
    }

    @GetMapping("/id={id}")
    public ResponseEntity<ListDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(listService.findOne(id));
    }

    @GetMapping("/board={id}")
    public ResponseEntity<List<ListDTO>> getByBoardId(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(listService.findAllByBoard(id));
    }

    @DeleteMapping("/id={id}")
    public void delete(@PathVariable("id") Long id) {
        listService.delete(id);
    }
}
