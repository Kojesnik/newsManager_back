package com.epam.lab.mapper.impl;

import com.epam.lab.dto.impl.AuthorDto;
import com.epam.lab.mapper.AbstractMapper;
import com.epam.lab.model.impl.Author;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapper implements AbstractMapper<AuthorDto, Author> {

    @Autowired
    private ModelMapper modelMapper;

    public AuthorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Author toEntity(AuthorDto authorDto) {
        return modelMapper.map(authorDto, Author.class);
    }

    @Override
    public AuthorDto toDto(Author author) {
        return modelMapper.map(author, AuthorDto.class);
    }

    @Override
    public List<AuthorDto> toDtoList(List<Author> authors) {
        return authors.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<Author> toEntityList(List<AuthorDto> authorDtoList) {
        return authorDtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
