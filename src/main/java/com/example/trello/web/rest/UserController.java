package com.example.trello.web.rest;

import com.example.trello.core.Constants;
import com.example.trello.dto.MessageDto;
import com.example.trello.model.User;
import com.example.trello.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.USER)
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "")
    public ResponseEntity<User> add(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.save(user));
    }

    @GetMapping(value = "")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok().body(userService.getAllUser());
    }

    @GetMapping(value = "/id={id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @GetMapping(value = "/username={username}")
    public ResponseEntity<User> findUserByUserName(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.findUserByUserName(username));
    }

    @CrossOrigin
    @GetMapping(value = "/token")
    public ResponseEntity<User> findUserByToken(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(userService.findByToken(token));
    }

    @GetMapping(value = "/role={role}")
    public ResponseEntity<List<User>> findUserByURole(@PathVariable String role) {
        return ResponseEntity.ok().body(userService.findUserByRole(role));
    }

    @PutMapping(value = "")
    public ResponseEntity<MessageDto> updateUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.updateUser(user));
    }


    @PutMapping("/deactivate")
    public ResponseEntity<Boolean>
    find(@RequestBody Long userId) {
        return ResponseEntity.ok().body(userService.detective(userId));
    }
}
