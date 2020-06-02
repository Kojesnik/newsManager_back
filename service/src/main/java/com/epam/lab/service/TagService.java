package com.epam.lab.service;

import com.epam.lab.dto.impl.TagDto;

import java.util.List;

public interface TagService extends EntityService<TagDto> {

    List<TagDto> getAll();

}
