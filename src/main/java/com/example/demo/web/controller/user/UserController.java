package com.example.demo.web.controller.user;

import static org.springframework.http.HttpStatus.OK;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.example.demo.service.user.UserConverter;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAll() {
        var users = userService.getAll();
        var responseBody = users.stream().map(UserConverter::toResponse).collect(Collectors.toList());
        return ResponseEntity.status(OK).body(responseBody);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<UserResponse> getOneById(@RequestParam UUID id) {
        var user = userService.getOneBy(id);
        var responseBody = UserConverter.toResponse(user);
        return ResponseEntity.status(OK).body(responseBody);
    }
    
    @PostMapping()
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest requestBody) {
        var user = userService.save(UserConverter.toUser(requestBody));
        var responseBody = UserConverter.toResponse(user);
        return ResponseEntity.status(OK).body(responseBody);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<UserResponse> replace(@RequestParam UUID id, @RequestBody UserRequest requestBody) {
        var user = userService.replace(id, UserConverter.toUser(requestBody));
        var responseBody = UserConverter.toResponse(user);
        return ResponseEntity.status(OK).body(responseBody);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@RequestParam UUID id) {
        userService.delete(id);
        return ResponseEntity.status(OK).build();
    }

}