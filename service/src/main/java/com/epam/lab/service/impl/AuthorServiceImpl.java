package com.epam.lab.service.impl;

import com.epam.lab.dto.impl.AuthorDto;
import com.epam.lab.exception.AlreadyExistsException;
import com.epam.lab.exception.FindException;
import com.epam.lab.mapper.AbstractMapper;
import com.epam.lab.model.impl.Author;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.service.AuthorService;
import com.epam.lab.specification.impl.author.AuthorAllSpecification;
import com.epam.lab.specification.impl.author.AuthorIdSpecification;
import com.epam.lab.specification.impl.author.AuthorNameSurnameSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private AbstractMapper<AuthorDto, Author> authorMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AbstractMapper<AuthorDto, Author> authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public AuthorDto add(AuthorDto authorDto) {
        if (isAuthorNameNotExists(authorDto)) {
            return authorMapper.toDto(authorRepository.add(authorMapper.toEntity(authorDto)));
        } else {
            throw new AlreadyExistsException("Such author already exists");
        }
    }

    @Override
    public AuthorDto update(AuthorDto authorDto) {
        if (isAuthorNameNotExists(authorDto)) {
            return authorMapper.toDto(authorRepository.update(authorMapper.toEntity(authorDto)));
        } else {
            throw new AlreadyExistsException("Such author already exists");
        }
    }

    private boolean isAuthorNameNotExists(AuthorDto authorDto) {
        return authorRepository.query(new AuthorNameSurnameSpecification(authorDto.getName(), authorDto.getSurname())).isEmpty();
    }

    @Override
    @Transactional
    public Boolean remove(AuthorDto authorDto) {
        return authorRepository.remove(authorMapper.toEntity(authorDto));
    }

    @Override
    public AuthorDto get(AuthorDto authorDto) {
        List<Author> authors = authorRepository.query(new AuthorIdSpecification(authorDto.getId()));
        if (authors.isEmpty()) {
            throw new FindException("No author found by such id");
        }
        return authorMapper.toDto(authors.get(0));
    }

    @Override
    public List<AuthorDto> getAll() {
        List<Author> authors = authorRepository.query(new AuthorAllSpecification());
        return authorMapper.toDtoList(authors);
    }

}
