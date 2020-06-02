package com.epam.lab.service.impl;

import com.epam.lab.dto.impl.AuthorDto;
import com.epam.lab.exception.AlreadyExistsException;
import com.epam.lab.exception.FindException;
import com.epam.lab.mapper.impl.AuthorMapper;
import com.epam.lab.model.impl.Author;
import com.epam.lab.model.impl.News;
import com.epam.lab.repository.impl.AuthorRepositoryImpl;
import com.epam.lab.specification.impl.author.AuthorAllSpecification;
import com.epam.lab.specification.impl.author.AuthorIdSpecification;
import com.epam.lab.specification.impl.author.AuthorNameSurnameSpecification;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AuthorServiceImplTest {

    private AuthorServiceImpl authorServiceImpl;
    private AuthorMapper authorMapper;
    private AuthorRepositoryImpl authorRepositoryImpl = mock(AuthorRepositoryImpl.class);

    @Before
    public void setUp() throws Exception {
        authorMapper = new AuthorMapper(new ModelMapper());
        authorServiceImpl = new AuthorServiceImpl(authorRepositoryImpl, authorMapper);
    }

    @Test
    public void add() {
        Author author = new Author(1L, "Misha", "Kolesnik");
        AuthorDto expected = new AuthorDto(1L, "Misha", "Kolesnik");
        when(authorRepositoryImpl.add(author)).thenReturn(author);
        AuthorDto actual = authorServiceImpl.add(authorMapper.toDto(author));
        assertEquals(expected, actual);
        verify(authorRepositoryImpl, times(1)).add(author);
    }

    @Test(expected = AlreadyExistsException.class)
    public void addWithException() {
        Author author = new Author(1L, "Misha", "Kolesnik");
        AuthorDto expected = new AuthorDto(1L, "Misha", "Kolesnik");
        when(authorRepositoryImpl.add(author)).thenReturn(author);
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1L, "Misha", "Kolesnik"));
        when(authorRepositoryImpl.query(any(AuthorNameSurnameSpecification.class))).thenReturn(authors);
        AuthorDto actual = authorServiceImpl.add(authorMapper.toDto(author));
        assertEquals(expected, actual);
        verify(authorRepositoryImpl, times(1)).query(any(AuthorNameSurnameSpecification.class));
    }

    @Test
    public void update() {
        Author author = new Author(1L, "Misha", "Kolesnik");
        AuthorDto expected = new AuthorDto(1L, "Misha", "Kolesnik");
        when(authorRepositoryImpl.update(author)).thenReturn(author);
        AuthorDto actual = authorServiceImpl.update(authorMapper.toDto(author));
        assertEquals(expected, actual);
        verify(authorRepositoryImpl, times(1)).update(author);
    }

    @Test(expected = AlreadyExistsException.class)
    public void updateWithException() {
        Author author = new Author(1L, "Misha", "Kolesnik");
        AuthorDto expected = new AuthorDto(1L, "Misha", "Kolesnik");
        when(authorRepositoryImpl.update(author)).thenReturn(author);
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1L, "Misha", "Kolesnik"));
        when(authorRepositoryImpl.query(any(AuthorNameSurnameSpecification.class))).thenReturn(authors);
        AuthorDto actual = authorServiceImpl.update(authorMapper.toDto(author));
        assertEquals(expected, actual);
        verify(authorRepositoryImpl, times(1)).query(any(AuthorNameSurnameSpecification.class));
    }

    @Test
    public void remove() {
        Author author = new Author(1L, "Misha", "Kolesnik");
        Boolean expected = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));
        List<News> news = new ArrayList<>();
        Collections.addAll(news, new News(1L, "title", "short", "full", date, date));
        when(authorRepositoryImpl.remove(author)).thenReturn(true);
        Boolean actual = authorServiceImpl.remove(authorMapper.toDto(author));
        assertEquals(expected, actual);
        verify(authorRepositoryImpl, times(1)).remove(author);
    }

    @Test
    public void get() {
        Author author = new Author(1L, "Misha", "Kolesnik");
        List<Author> authors = new ArrayList();
        authors.add(author);
        when(authorRepositoryImpl.query(any(AuthorIdSpecification.class))).thenReturn(authors);
        AuthorDto expected = new AuthorDto(1L, "Misha", "Kolesnik");
        AuthorDto actual = authorServiceImpl.get(authorMapper.toDto(author));
        assertEquals(expected, actual);
        verify(authorRepositoryImpl, times(1)).query(any(AuthorIdSpecification.class));
    }

    @Test(expected = FindException.class)
    public void getWithException() {
        Author author = new Author(1L, "Misha", "Kolesnik");
        List<Author> authors = new ArrayList();
        when(authorRepositoryImpl.query(any(AuthorIdSpecification.class))).thenReturn(authors);
        AuthorDto expected = new AuthorDto(1L, "Misha", "Kolesnik");
        AuthorDto actual = authorServiceImpl.get(authorMapper.toDto(author));
        assertEquals(expected, actual);
        verify(authorRepositoryImpl, times(1)).query(any(AuthorIdSpecification.class));
    }

    @Test
    public void getAll() {
        Author author = new Author(1L, "Misha", "Kolesnik");
        List<Author> authors = new ArrayList();
        authors.add(author);
        when(authorRepositoryImpl.query(any(AuthorAllSpecification.class))).thenReturn(authors);
        List<AuthorDto> expected = new ArrayList<>();
        expected.add(new AuthorDto(1L, "Misha", "Kolesnik"));
        List<AuthorDto> actual = authorServiceImpl.getAll();
        assertEquals(expected, actual);
        verify(authorRepositoryImpl, times(1)).query(any(AuthorAllSpecification.class));
    }

}