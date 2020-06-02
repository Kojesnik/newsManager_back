package com.epam.lab.mapper.impl;

import com.epam.lab.dto.impl.NewsCriteriaDto;
import com.epam.lab.model.impl.NewsCriteria;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class NewsCriteriaMapper {

    private ModelMapper modelMapper;

    public NewsCriteriaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public NewsCriteria toEntity(NewsCriteriaDto newsCriteriaDto) {
        return modelMapper.map(newsCriteriaDto, NewsCriteria.class);
    }

    public NewsCriteriaDto toDto(NewsCriteria newsCriteria) {
        return modelMapper.map(newsCriteria, NewsCriteriaDto.class);
    }
}
