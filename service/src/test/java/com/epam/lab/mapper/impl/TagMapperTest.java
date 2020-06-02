package com.epam.lab.mapper.impl;

import com.epam.lab.dto.impl.TagDto;
import com.epam.lab.model.impl.Tag;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

public class TagMapperTest {

    private static TagMapper tagMapper;

    @BeforeClass
    public static void setUp() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        tagMapper = new TagMapper(mapper);
    }

    @Test
    public void toEntity() {
        TagDto tagDto = new TagDto(1L, "epam");
        Tag expected = new Tag(1L, "epam");
        Tag actual = tagMapper.toEntity(tagDto);
        assertEquals(expected, actual);
    }

    @Test
    public void toDto() {
        Tag tag = new Tag(1L, "epam");
        TagDto expected = new TagDto(1L, "epam");
        TagDto actual = tagMapper.toDto(tag);
        assertEquals(expected, actual);
    }

    @Test
    public void toDtoList() {
        List<Tag> tags = new ArrayList<>();
        Collections.addAll(tags, new Tag(1L, "epam"), new Tag(2L, "auto"));
        List<TagDto> expected = new ArrayList<>();
        Collections.addAll(expected, new TagDto(1L, "epam"), new TagDto(2L, "auto"));
        List<TagDto> actual = tagMapper.toDtoList(tags);
        assertEquals(expected, actual);
    }

    @Test
    public void toEntityList() {
        List<TagDto> tagDtos = new ArrayList<>();
        Collections.addAll(tagDtos, new TagDto(1L, "epam"), new TagDto(2L, "auto"));
        List<Tag> expected = new ArrayList<>();
        Collections.addAll(expected, new Tag(1L, "epam"), new Tag(2L, "auto"));
        List<Tag> actual = tagMapper.toEntityList(tagDtos);
        assertEquals(expected, actual);
    }
}