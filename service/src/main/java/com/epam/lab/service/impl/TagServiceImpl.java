package com.epam.lab.service.impl;

import com.epam.lab.dto.impl.TagDto;
import com.epam.lab.exception.AlreadyExistsException;
import com.epam.lab.exception.FindException;
import com.epam.lab.mapper.AbstractMapper;
import com.epam.lab.model.impl.Tag;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.service.TagService;
import com.epam.lab.specification.impl.tag.TagAllSpecification;
import com.epam.lab.specification.impl.tag.TagIdSpecification;
import com.epam.lab.specification.impl.tag.TagNameSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;
    private AbstractMapper<TagDto, Tag> tagMapper;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, AbstractMapper<TagDto, Tag> tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    @Override
    @Transactional
    public TagDto add(TagDto tagDto) {
        if (isTagNameNotExists(tagDto)) {
            return tagMapper.toDto(tagRepository.add(tagMapper.toEntity(tagDto)));
        } else {
            throw new AlreadyExistsException("Such tag already exists");
        }
    }

    @Override
    public Boolean remove(TagDto tagDto) {
        return tagRepository.remove(tagMapper.toEntity(tagDto));
    }

    @Override
    public TagDto get(TagDto tagDto) {
        List<Tag> tags = tagRepository.query(new TagIdSpecification(tagDto.getId()));
        if (tags.isEmpty()) {
            throw new FindException("No tag found with such id");
        }
        return tagMapper.toDto(tags.get(0));
    }

    @Override
    public List<TagDto> getAll() {
        List<Tag> tags = tagRepository.query(new TagAllSpecification());
        return tagMapper.toDtoList(tags);
    }

    @Override
    public TagDto update(TagDto tagDto) {
        if (isTagNameNotExists(tagDto)) {
            return tagMapper.toDto(tagRepository.update(tagMapper.toEntity(tagDto)));
        } else {
            throw new AlreadyExistsException("Such tag already exists");
        }
    }

    private boolean isTagNameNotExists(TagDto tagDto) {
        return tagRepository.query(new TagNameSpecification(tagDto.getName())).isEmpty();
    }

}
