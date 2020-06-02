package com.epam.lab.controller;

import com.epam.lab.dto.impl.TagDto;
import com.epam.lab.service.TagService;
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
@RequestMapping("/tags")
@Validated
public class TagController {

    private static final String ID_NULL_MESSAGE = "Provide tag id";
    private static final String ID_INVALID_MESSAGE = "Invalid tag id. Min id = 1";

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseEntity<TagDto> add(@RequestBody @Valid @NotNull TagDto tagDto) {
        return new ResponseEntity<>(tagService.add(tagDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable @NotNull(message = ID_NULL_MESSAGE)
                             @Min(value = 1, message = ID_INVALID_MESSAGE) long id) {
        TagDto tagDto = new TagDto();
        tagDto.setId(id);
        return new ResponseEntity<>(tagService.remove(tagDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> get(@PathVariable @NotNull(message = ID_NULL_MESSAGE)
                          @Min(value = 1, message = ID_INVALID_MESSAGE) long id) {
        TagDto tagDto = new TagDto();
        tagDto.setId(id);
        return new ResponseEntity<>(tagService.get(tagDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TagDto>> getAll() {
        return new ResponseEntity<>(tagService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDto> update(@RequestBody @Valid TagDto tagDto,
                         @PathVariable @NotNull(message = ID_NULL_MESSAGE)
                         @Min(value = 1, message = ID_INVALID_MESSAGE) long id) {
        tagDto.setId(id);
        return new ResponseEntity<>(tagService.update(tagDto), HttpStatus.ACCEPTED);
    }

}
