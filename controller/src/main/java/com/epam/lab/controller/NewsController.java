package com.epam.lab.controller;

import com.epam.lab.dto.impl.AuthorDto;
import com.epam.lab.dto.impl.NewsCriteriaDto;
import com.epam.lab.dto.impl.NewsDto;
import com.epam.lab.service.NewsService;
import com.epam.lab.type.SortType;
import com.epam.lab.validator.annotation.Sort;
import com.epam.lab.validator.annotation.TagNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = { "http://localhost:3000", "http://epbyminw8106:3000" })
@RestController
@RequestMapping("/news")
@Validated
public class NewsController {

    private static final String ID_NULL_MESSAGE = "Provide news id";
    private static final String ID_INVALID_MESSAGE = "Invalid news id. Min id = 1";

    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping
    public ResponseEntity<NewsDto> add(@RequestBody @Valid NewsDto newsDto) {
        return new ResponseEntity<>(newsService.add(newsDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable @NotNull(message = ID_NULL_MESSAGE)
                             @Min(value = 1, message = ID_INVALID_MESSAGE)  long id) {
        NewsDto newsDto = new NewsDto();
        newsDto.setId(id);
        return new ResponseEntity<>(newsService.remove(newsDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> get(@PathVariable @NotNull(message = ID_NULL_MESSAGE)
                          @Min(value = 1, message = ID_INVALID_MESSAGE) long id) {
        NewsDto newsDto = new NewsDto();
        newsDto.setId(id);
        return new ResponseEntity<>(newsService.get(newsDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<NewsDto>> get(@RequestParam(required = false) @Size(min = 1, max = 30) String name,
                                             @RequestParam(required = false) @Size(min = 1, max = 30) String surname,
                                             @RequestParam(required = false) @TagNames List<String> tagNames,
                                             @RequestParam(required = false) @Sort String sort,
                                             @RequestParam(required = false) Long pageNumber,
                                             @RequestParam(required = false) Long pageSize) {
        NewsCriteriaDto newsCriteriaDto = new NewsCriteriaDto();
        if (Objects.nonNull(name) && Objects.nonNull(surname)) {
            newsCriteriaDto.setAuthorDto(new AuthorDto(name, surname));
        }
        newsCriteriaDto.setTagNames(tagNames);
        if (Objects.nonNull(sort)) {
            newsCriteriaDto.setSortType(SortType.valueOf(sort.toUpperCase()));
        }
        if (Objects.nonNull(pageSize) && Objects.nonNull(pageNumber)) {
            newsCriteriaDto.setPageNumber(pageNumber);
            newsCriteriaDto.setPageSize(pageSize);
        }
        return new ResponseEntity<>(newsService.getByCriteria(newsCriteriaDto), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<NewsDto> update(@RequestBody @Valid NewsDto newsDto, @PathVariable @NotNull(message = ID_NULL_MESSAGE)
                            @Min(value = 1, message = ID_INVALID_MESSAGE) long id) {
        newsDto.setId(id);
        return new ResponseEntity<>(newsService.update(newsDto), HttpStatus.ACCEPTED);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        return new ResponseEntity<>(newsService.getCount(), HttpStatus.OK);
    }

    @GetMapping("/criteriaCount")
    public ResponseEntity<Long> getCriteriaCount(@RequestParam(required = false) @Size(min = 1, max = 30) String name,
                                                 @RequestParam(required = false) @Size(min = 1, max = 30) String surname,
                                                 @RequestParam(required = false) @TagNames List<String> tagNames) {
        NewsCriteriaDto newsCriteriaDto = new NewsCriteriaDto();
        if (Objects.nonNull(name) && Objects.nonNull(surname)) {
            newsCriteriaDto.setAuthorDto(new AuthorDto(name, surname));
        }
        newsCriteriaDto.setTagNames(tagNames);
        return new ResponseEntity<>(newsService.getCriteriaCount(newsCriteriaDto), HttpStatus.OK);
    }

}
