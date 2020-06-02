package com.epam.lab.service;

import com.epam.lab.dto.impl.AuthorDto;

import java.util.List;

public interface AuthorService extends EntityService<AuthorDto> {

    List<AuthorDto> getAll();

}
