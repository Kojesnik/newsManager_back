package com.epam.lab.mapper.impl;

import com.epam.lab.dto.impl.NewsDto;
import com.epam.lab.model.impl.News;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

public class NewsMapperTest {

    private static NewsMapper newsMapper;
    private static LocalDate date;

    @BeforeClass
    public static void setUp() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        newsMapper = new NewsMapper(mapper);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        date = LocalDate.parse(LocalDate.now().format(formatter));
    }

    @Test
    public void toEntity() {
        NewsDto newsDto = new NewsDto(1L, "title", "short", "full", date, date);
        News expected = new News(1L, "title", "short", "full", date, date);
        News actual = newsMapper.toEntity(newsDto);
        assertEquals(expected, actual);
    }

    @Test
    public void toDto() {
        News news = new News(1L, "title", "short", "full", date, date);
        NewsDto expected = new NewsDto(1L, "title", "short", "full", date, date);
        NewsDto actual = newsMapper.toDto(news);
        assertEquals(expected, actual);
    }

    @Test
    public void toDtoList() {
        List<News> news = new ArrayList<>();
        Collections.addAll(news, new News(1L, "title1", "short1", "full1", date, date),
                new News(2L, "title2", "short2", "full2", date, date));
        List<NewsDto> expected = new ArrayList<>();
        Collections.addAll(expected, new NewsDto(1L, "title1", "short1", "full1", date, date),
                new NewsDto(2L, "title2", "short2", "full2", date, date));
        List<NewsDto> actual = newsMapper.toDtoList(news);
        assertEquals(expected, actual);
    }

    @Test
    public void toEntityList() {
        List<NewsDto> newsDtos = new ArrayList<>();
        Collections.addAll(newsDtos, new NewsDto(1L, "title1", "short1", "full1", date, date),
                new NewsDto(2L, "title2", "short2", "full2", date, date));
        List<News> expected = new ArrayList<>();
        Collections.addAll(expected, new News(1L, "title1", "short1", "full1", date, date),
                new News(2L, "title2", "short2", "full2", date, date));
        List<News> actual = newsMapper.toEntityList(newsDtos);
        assertEquals(expected, actual);
    }
}