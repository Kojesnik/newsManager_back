package com.epam.lab.service.impl;

import com.epam.lab.dto.impl.TagDto;
import com.epam.lab.exception.AlreadyExistsException;
import com.epam.lab.exception.FindException;
import com.epam.lab.mapper.impl.TagMapper;
import com.epam.lab.model.impl.Tag;
import com.epam.lab.repository.impl.TagRepositoryImpl;
import com.epam.lab.specification.impl.tag.TagAllSpecification;
import com.epam.lab.specification.impl.tag.TagIdSpecification;
import com.epam.lab.specification.impl.tag.TagNameSpecification;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TagServiceImplTest {

    private TagServiceImpl tagServiceImpl;
    private TagMapper tagMapper;
    private TagRepositoryImpl tagRepositoryImpl = mock(TagRepositoryImpl.class);

    @Before
    public void setUp() throws Exception {
        tagMapper = new TagMapper(new ModelMapper());

        tagServiceImpl = new TagServiceImpl(tagRepositoryImpl, tagMapper);
    }

    @Test
    public void add() {
        Tag tag = new Tag(1L, "spring");
        TagDto expected = new TagDto(1L, "spring");
        when(tagRepositoryImpl.add(tag)).thenReturn(tag);
        TagDto actual = tagServiceImpl.add(tagMapper.toDto(tag));
        assertEquals(expected, actual);
        verify(tagRepositoryImpl, times(1)).add(tag);
    }

    @Test(expected = AlreadyExistsException.class)
    public void addWithException() {
        Tag tag = new Tag(1L, "spring");
        TagDto expected = new TagDto(1L, "spring");
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1L, "spring"));
        when(tagRepositoryImpl.add(tag)).thenReturn(tag);
        when(tagRepositoryImpl.query(any(TagNameSpecification.class))).thenReturn(tags);
        TagDto actual = tagServiceImpl.add(tagMapper.toDto(tag));
        assertEquals(expected, actual);
        verify(tagRepositoryImpl, times(1)).query(any(TagNameSpecification.class));
    }

    @Test
    public void remove() {
        Tag tag = new Tag(1L, "spring");
        Boolean expected = true;
        when(tagRepositoryImpl.remove(tag)).thenReturn(true);
        Boolean actual = tagServiceImpl.remove(tagMapper.toDto(tag));
        assertEquals(expected, actual);
        verify(tagRepositoryImpl, times(1)).remove(tag);
    }

    @Test
    public void get() {
        Tag tag = new Tag(1L, "spring");
        List<Tag> tagList = new ArrayList();
        tagList.add(tag);
        when(tagRepositoryImpl.query(any(TagIdSpecification.class))).thenReturn(tagList);
        TagDto expected = new TagDto(1L, "spring");
        TagDto actual = tagServiceImpl.get(tagMapper.toDto(tag));
        assertEquals(expected, actual);
        verify(tagRepositoryImpl, times(1)).query(any(TagIdSpecification.class));
    }

    @Test
    public void getAll() {
        Tag tag = new Tag(1L, "spring");
        List<Tag> tagList = new ArrayList();
        tagList.add(tag);
        when(tagRepositoryImpl.query(any(TagAllSpecification.class))).thenReturn(tagList);
        List<TagDto> expected = new ArrayList<>();
        expected.add(new TagDto(1L, "spring"));
        List<TagDto> actual = tagServiceImpl.getAll();
        assertEquals(expected, actual);
        verify(tagRepositoryImpl, times(1)).query(any(TagAllSpecification.class));
    }

    @Test(expected = FindException.class)
    public void getWithException() {
        Tag tag = new Tag(1L, "spring");
        List<Tag> tagList = new ArrayList();
        when(tagRepositoryImpl.query(any(TagIdSpecification.class))).thenReturn(tagList);
        TagDto expected = new TagDto(1L, "spring");
        TagDto actual = tagServiceImpl.get(tagMapper.toDto(tag));
        assertEquals(expected, actual);
        verify(tagRepositoryImpl, times(1)).query(any(TagIdSpecification.class));
    }

    @Test
    public void update() {
        Tag tag = new Tag(1L, "spring");
        TagDto expected = new TagDto(1L, "spring");
        when(tagRepositoryImpl.update(tag)).thenReturn(tag);
        TagDto actual = tagServiceImpl.update(tagMapper.toDto(tag));
        assertEquals(expected, actual);
        verify(tagRepositoryImpl, times(1)).update(tag);
    }

    @Test(expected = AlreadyExistsException.class)
    public void updateWithException() {
        Tag tag = new Tag(1L, "spring");
        TagDto expected = new TagDto(1L, "spring");
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1L, "spring"));
        when(tagRepositoryImpl.update(tag)).thenReturn(tag);
        when(tagRepositoryImpl.query(any(TagNameSpecification.class))).thenReturn(tags);
        TagDto actual = tagServiceImpl.update(tagMapper.toDto(tag));
        assertEquals(expected, actual);
        verify(tagRepositoryImpl, times(1)).query(any(TagNameSpecification.class));
    }

}