package com.epam.lab.mapper.impl;

import com.epam.lab.dto.impl.NewsDto;
import com.epam.lab.mapper.AbstractMapper;
import com.epam.lab.model.impl.News;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NewsMapper implements AbstractMapper<NewsDto, News> {

    private ModelMapper modelMapper;

    @Autowired
    public NewsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public News toEntity(NewsDto newsDto) {
        return modelMapper.map(newsDto, News.class);
    }

    @Override
    public NewsDto toDto(News news) {
        return modelMapper.map(news, NewsDto.class);
    }

    @Override
    public List<NewsDto> toDtoList(List<News> newsList) {
        return newsList.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<News> toEntityList(List<NewsDto> newsDtoList) {
        return newsDtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
