package com.epam.lab.mapper;

import com.epam.lab.dto.EntityDto;
import com.epam.lab.model.AbstractEntity;
import java.util.List;

public interface AbstractMapper<T extends EntityDto, K extends AbstractEntity> {

    K toEntity(T dto);
    T toDto(K entity);
    List<T> toDtoList(List<K> entities);
    List<K> toEntityList(List<T> dtos);

}
