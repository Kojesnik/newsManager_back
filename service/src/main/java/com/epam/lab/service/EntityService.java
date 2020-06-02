package com.epam.lab.service;

import com.epam.lab.dto.EntityDto;

public interface EntityService<T extends EntityDto> {

    T add(T dto);
    T update(T dto);
    Boolean remove(T dto);
    T get(T dto);

}
