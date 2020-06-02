package com.epam.lab.mapper.impl;

import com.epam.lab.dto.impl.TagDto;
import com.epam.lab.mapper.AbstractMapper;
import com.epam.lab.model.impl.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagMapper implements AbstractMapper<TagDto, Tag> {

    private ModelMapper modelMapper;

    @Autowired
    public TagMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Tag toEntity(TagDto tagDto) {
        return modelMapper.map(tagDto, Tag.class);
    }

    @Override
    public TagDto toDto(Tag tag) {
        return modelMapper.map(tag, TagDto.class);
    }

    @Override
    public List<TagDto> toDtoList(List<Tag> tags) {
        return tags.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<Tag> toEntityList(List<TagDto> tagDtoList) {
        return tagDtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

}
