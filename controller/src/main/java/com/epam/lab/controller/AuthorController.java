package com.epam.lab.controller;

import com.epam.lab.dto.impl.AuthorDto;
import com.epam.lab.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://epbyminw8106:3000" })
@RestController
@RequestMapping("/authors")
@Validated
public class AuthorController {

    private static final String ID_NULL_MESSAGE = "Provide author id";
    private static final String ID_INVALID_MESSAGE = "Invalid author id. Min id = 1";

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorDto> add(@RequestBody @Valid AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.add(authorDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable @NotNull(message = ID_NULL_MESSAGE)
                                     @Min(value = 1, message = ID_INVALID_MESSAGE) long id) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(id);
        return new ResponseEntity<>(authorService.remove(authorDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> get(@PathVariable @NotNull(message = ID_NULL_MESSAGE)
                          @Min(value = 1, message = ID_INVALID_MESSAGE) long id) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(id);
        return new ResponseEntity<>(authorService.get(authorDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAll() {
        return new ResponseEntity<>(authorService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> update(@RequestBody @Valid AuthorDto authorDto, @PathVariable @NotNull(message = ID_NULL_MESSAGE)
                            @Min(value = 1, message = ID_INVALID_MESSAGE) long id) {
        authorDto.setId(id);
        return new ResponseEntity<>(authorService.update(authorDto), HttpStatus.ACCEPTED);
    }

}
