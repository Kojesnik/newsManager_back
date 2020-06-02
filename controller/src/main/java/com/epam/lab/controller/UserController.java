package com.epam.lab.controller;

import com.epam.lab.dto.impl.TagDto;
import com.epam.lab.dto.impl.UserDto;
import com.epam.lab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "http://localhost:3000", "http://epbyminw8106:3000" })
@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> add(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.add(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/{login}")
    public ResponseEntity<UserDto> get(@PathVariable String login) {
        UserDto userDto = new UserDto();
        userDto.setLogin(login);
        return new ResponseEntity<>(userService.get(userDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable long id) {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        return new ResponseEntity<>(userService.remove(userDto), HttpStatus.OK);
    }

}
