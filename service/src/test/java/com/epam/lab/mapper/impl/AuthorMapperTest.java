package com.epam.lab.mapper.impl;

import com.epam.lab.dto.impl.AuthorDto;
import com.epam.lab.model.impl.Author;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;
import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

public class AuthorMapperTest {

    private static AuthorMapper authorMapper;

    @BeforeClass
    public static void setUp() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        authorMapper = new AuthorMapper(mapper);
    }

    @Test
    public void toEntity() {
        AuthorDto authorDto = new AuthorDto(1L, "Misha", "Kolesnik");
        Author expected = new Author(1L, "Misha", "Kolesnik");
        Author actual = authorMapper.toEntity(authorDto);
        assertEquals(expected, actual);
    }

    @Test
    public void toDto() {
        Author author = new Author(1L, "Misha", "Kolesnik");
        AuthorDto expected = new AuthorDto(1L, "Misha", "Kolesnik");
        AuthorDto actual = authorMapper.toDto(author);
        assertEquals(expected, actual);
    }

    @Test
    public void toDtoList() {
        List<Author> authors = new ArrayList<>();
        Collections.addAll(authors, new Author(1L, "Misha", "Kolesnik"),
                new Author(2L, "Vasya", "Pupkin"),
                new Author(3L, "Art", "Kos"));
        List<AuthorDto> expected = new ArrayList<>();
        Collections.addAll(expected, new AuthorDto(1L, "Misha", "Kolesnik"),
                new AuthorDto(2L, "Vasya", "Pupkin"),
                new AuthorDto(3L, "Art", "Kos"));
        List<AuthorDto> actual = authorMapper.toDtoList(authors);
        assertEquals(expected, actual);
    }

    @Test
    public void toEntityList() {
        List<AuthorDto> authorDtos = new ArrayList<>();
        Collections.addAll(authorDtos, new AuthorDto(1L, "Misha", "Kolesnik"),
                new AuthorDto(2L, "Vasya", "Pupkin"),
                new AuthorDto(3L, "Art", "Kos"));
        List<Author> expected = new ArrayList<>();
        Collections.addAll(expected, new Author(1L, "Misha", "Kolesnik"),
                new Author(2L, "Vasya", "Pupkin"),
                new Author(3L, "Art", "Kos"));
        List<Author> actual = authorMapper.toEntityList(authorDtos);
        assertEquals(expected, actual);
    }
}