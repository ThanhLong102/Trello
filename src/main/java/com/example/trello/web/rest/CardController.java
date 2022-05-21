package com.example.trello.web.rest;

import com.example.trello.core.Constants;
import com.example.trello.dto.CardDTO;
import com.example.trello.service.CardService;
import com.example.trello.web.vm.CardVm;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.USER+"/cards")
@Api(tags = "Card")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<CardDTO> add(@Valid @RequestBody CardDTO cardDTO) {
        return ResponseEntity.ok().body(cardService.save(cardDTO));
    }

    @PutMapping
    public ResponseEntity<CardDTO> update(@Valid @RequestBody CardDTO cardDTO) {
        return ResponseEntity.ok().body(cardService.save(cardDTO));
    }

    @GetMapping("/id={id}")
    public ResponseEntity<CardVm> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(cardService.findOne(id));
    }

    @GetMapping("/board={id}")
    public ResponseEntity<List<CardDTO>> getByListId(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(cardService.findAllByList(id));
    }

    @DeleteMapping("/id={id}")
    public void delete(@PathVariable("id") Long id) {
        cardService.delete(id);
    }
}
