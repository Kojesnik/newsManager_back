package com.epam.lab.mapper.impl;

import com.epam.lab.dto.impl.AuthorDto;
import com.epam.lab.dto.impl.NewsCriteriaDto;
import com.epam.lab.model.impl.Author;
import com.epam.lab.model.impl.NewsCriteria;
import com.epam.lab.type.SortType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;
import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

public class NewsCriteriaMapperTest {

    private static NewsCriteriaMapper newsCriteriaMapper;

    @BeforeClass
    public static void setUp() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        newsCriteriaMapper = new NewsCriteriaMapper(mapper);
    }

    @Test
    public void toEntity() {
        List<String> tagNames = new ArrayList<>();
        Collections.addAll(tagNames, "epam", "food");
        NewsCriteriaDto newsCriteriaDto = new NewsCriteriaDto(new AuthorDto(1L, "Misha", "Kolesnik"),
                tagNames, SortType.DATE);
        NewsCriteria expected = new NewsCriteria(new Author(1L, "Misha", "Kolesnik"),
                tagNames, SortType.DATE);
        NewsCriteria actual = newsCriteriaMapper.toEntity(newsCriteriaDto);
        assertEquals(expected, actual);
    }

    @Test
    public void toDto() {
        List<String> tagNames = new ArrayList<>();
        Collections.addAll(tagNames, "epam", "food");
        NewsCriteria newsCriteria = new NewsCriteria(new Author(1L, "Misha", "Kolesnik"),
                tagNames, SortType.DATE);
        NewsCriteriaDto expected = new NewsCriteriaDto(new AuthorDto(1L, "Misha", "Kolesnik"),
                tagNames, SortType.DATE);
        NewsCriteriaDto actual = newsCriteriaMapper.toDto(newsCriteria);
        assertEquals(expected, actual);
    }
}