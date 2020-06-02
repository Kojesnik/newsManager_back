package com.epam.lab.service.impl;

import com.epam.lab.dto.impl.AuthorDto;
import com.epam.lab.dto.impl.NewsCriteriaDto;
import com.epam.lab.dto.impl.NewsDto;
import com.epam.lab.dto.impl.TagDto;
import com.epam.lab.exception.FindException;
import com.epam.lab.mapper.impl.NewsCriteriaMapper;
import com.epam.lab.mapper.impl.NewsMapper;
import com.epam.lab.model.impl.Author;
import com.epam.lab.model.impl.News;
import com.epam.lab.model.impl.NewsCriteria;
import com.epam.lab.model.impl.Tag;
import com.epam.lab.repository.impl.AuthorRepositoryImpl;
import com.epam.lab.repository.impl.NewsRepositoryImpl;
import com.epam.lab.repository.impl.TagRepositoryImpl;
import com.epam.lab.specification.impl.author.AuthorSpecification;
import com.epam.lab.specification.impl.news.NewsCriteriaSpecification;
import com.epam.lab.specification.impl.news.NewsIdSpecification;
import com.epam.lab.specification.impl.tag.TagSpecification;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class NewsServiceImplTest {

    private NewsServiceImpl newsServiceImpl;
    private NewsMapper newsMapper = mock(NewsMapper.class);
    private NewsCriteriaMapper newsCriteriaMapper = mock(NewsCriteriaMapper.class);
    private NewsRepositoryImpl newsRepositoryImpl = mock(NewsRepositoryImpl.class);
    private TagRepositoryImpl tagRepositoryImpl = mock(TagRepositoryImpl.class);
    private AuthorRepositoryImpl authorRepositoryImpl = mock(AuthorRepositoryImpl.class);

    @Before
    public void setUp() throws Exception {
        newsServiceImpl = new NewsServiceImpl(newsRepositoryImpl, newsMapper, newsCriteriaMapper,
                tagRepositoryImpl, authorRepositoryImpl);
    }

    @Test
    public void add() {
        AuthorDto authorDto = new AuthorDto(1L, "Misha", "Kolesnik");
        List<TagDto> tagDtos = new ArrayList();
        Collections.addAll(tagDtos, new TagDto(1L, "spring"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));
        NewsDto newsDto = new NewsDto("title", "short", "full", tagDtos, authorDto, date, date);
        News news = new News("title", "short", "full", date, date);
        News news1 = new News(21L, "title", "short", "full", date, date);
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1L, "spring"));
        when(tagRepositoryImpl.query(any(TagSpecification.class))).thenReturn(tags);
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1L, "misha", "kol"));
        when(authorRepositoryImpl.query(any(AuthorSpecification.class))).thenReturn(authors);
        when(newsMapper.toEntity(newsDto)).thenReturn(news);
        when(newsRepositoryImpl.add(news)).thenReturn(news1);
        NewsDto expected = new NewsDto(21L,"title", "short", "full", date, date);
        when(newsMapper.toDto(news1)).thenReturn(expected);
        NewsDto actual = newsServiceImpl.add(newsDto);
        assertEquals(expected, actual);
        verify(newsRepositoryImpl, times(1)).add(news);
    }

    @Test
    public void addWithNotExistsAuthor() {
        AuthorDto authorDto = new AuthorDto("Misha", "Kolesnik");
        Author author = new Author("Misha", "Kolesnik");
        Author author1 = new Author(21L, "Misha", "Kolesnik");
        List<TagDto> tagDtos = new ArrayList();
        Collections.addAll(tagDtos, new TagDto(1L, "spring"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));
        NewsDto newsDto = new NewsDto("title", "short", "full", tagDtos, authorDto, date, date);
        News news = new News("title", "short", "full", date, date);
        News news1 = new News(21L, "title", "short", "full", date, date);
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1L, "spring"));
        when(tagRepositoryImpl.query(any(TagSpecification.class))).thenReturn(tags);
        when(newsMapper.toEntity(newsDto)).thenReturn(news);
        when(newsRepositoryImpl.add(news)).thenReturn(news1);
        when(authorRepositoryImpl.add(author)).thenReturn(author1);
        NewsDto expected = new NewsDto(21L,"title", "short", "full", date, date);
        when(newsMapper.toDto(news1)).thenReturn(expected);
        NewsDto actual = newsServiceImpl.add(newsDto);
        assertEquals(expected, actual);
        verify(newsRepositoryImpl, times(1)).add(news);
    }

    @Test
    public void addWithNotExistsTags() {
        AuthorDto authorDto = new AuthorDto(1L,"Misha", "Kolesnik");
        List<TagDto> tagDtos = new ArrayList();
        Collections.addAll(tagDtos, new TagDto("spring"));
        Tag tag = new Tag("spring");
        Tag tag1 = new Tag(21L, "spring");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));
        NewsDto newsDto = new NewsDto("title", "short", "full", authorDto, date, date);
        newsDto.setTags(tagDtos);
        News news = new News("title", "short", "full", date, date);
        News news1 = new News(21L, "title", "short", "full", date, date);
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1L, "misha", "kol"));
        when(authorRepositoryImpl.query(any(AuthorSpecification.class))).thenReturn(authors);
        when(newsMapper.toEntity(newsDto)).thenReturn(news);
        when(newsRepositoryImpl.add(news)).thenReturn(news1);
        when(tagRepositoryImpl.add(tag)).thenReturn(tag1);
        NewsDto expected = new NewsDto(21L,"title", "short", "full", date, date);
        when(newsMapper.toDto(news1)).thenReturn(expected);
        NewsDto actual = newsServiceImpl.add(newsDto);
        assertEquals(expected, actual);
        verify(newsRepositoryImpl, times(1)).add(news);
    }

    @Test
    public void update() {
        AuthorDto authorDto = new AuthorDto(1L,"Misha", "Kolesnik");
        List<TagDto> tagDtos = new ArrayList();
        Collections.addAll(tagDtos, new TagDto("spring"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));
        NewsDto newsDto = new NewsDto(1L,"title", "short", "full", date, date);
        newsDto.setTags(tagDtos);
        newsDto.setAuthor(authorDto);
        News news = new News(1L,"title","short","full", date, date);
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1L, "misha", "kol"));
        when(authorRepositoryImpl.query(any(AuthorSpecification.class))).thenReturn(authors);
        when(newsMapper.toEntity(newsDto)).thenReturn(news);
        when(newsRepositoryImpl.update(news)).thenReturn(news);
        when(newsMapper.toDto(news)).thenReturn(newsDto);
        NewsDto actual = newsServiceImpl.update(newsDto);
        assertEquals(newsDto, actual);
        verify(newsRepositoryImpl, times(1)).update(news);
    }

    @Test
    public void updateWithAuthor() {
        AuthorDto authorDto = new AuthorDto(1L, "Misha", "Kolesnik");
        List<TagDto> tagDtos = new ArrayList();
        tagDtos.add(new TagDto("spring"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));
        NewsDto newsDto = new NewsDto(1L,"title", "short", "full", authorDto, date, date);
        newsDto.setTags(tagDtos);
        News news = new News(1L,"title","short","full", date, date);
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1L, "misha", "kol"));
        when(authorRepositoryImpl.query(any(AuthorSpecification.class))).thenReturn(authors);
        when(newsMapper.toEntity(newsDto)).thenReturn(news);
        when(newsRepositoryImpl.update(news)).thenReturn(news);
        when(newsMapper.toDto(news)).thenReturn(newsDto);
        NewsDto actual = newsServiceImpl.update(newsDto);
        assertEquals(newsDto, actual);
        verify(newsRepositoryImpl, times(1)).update(news);
    }

    @Test
    public void updateWithNotExistsAuthor() {
        List<TagDto> tagDtos = new ArrayList();
        tagDtos.add(new TagDto("spring"));
        AuthorDto authorDto = new AuthorDto("Misha", "Kolesnik");
        Author author = new Author("Misha", "Kolesnik");
        Author author1 = new Author(1L, "Misha", "Kolesnik");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));
        NewsDto newsDto = new NewsDto(1L,"title", "short", "full", authorDto, date, date);
        newsDto.setTags(tagDtos);
        News news = new News(1L,"title","short","full", date, date);
        when(newsMapper.toEntity(newsDto)).thenReturn(news);
        when(newsRepositoryImpl.update(news)).thenReturn(news);
        when(newsMapper.toDto(news)).thenReturn(newsDto);
        when(authorRepositoryImpl.add(author)).thenReturn(author1);
        NewsDto actual = newsServiceImpl.update(newsDto);
        assertEquals(newsDto, actual);
        verify(newsRepositoryImpl, times(1)).update(news);
    }

    @Test
    public void updateWithTags() {
        AuthorDto authorDto = new AuthorDto(1L, "Misha", "Kolesnik");
        List<TagDto> tagDtos = new ArrayList();
        tagDtos.add(new TagDto(1L,"spring"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));
        NewsDto newsDto = new NewsDto(1L,"title", "short", "full", date, date);
        newsDto.setTags(tagDtos);
        newsDto.setAuthor(authorDto);
        News news = new News(1L,"title","short","full", date, date);
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1L, "spring"));
        when(tagRepositoryImpl.query(any(TagSpecification.class))).thenReturn(tags);
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1L, "misha", "kol"));
        when(authorRepositoryImpl.query(any(AuthorSpecification.class))).thenReturn(authors);
        when(newsMapper.toEntity(newsDto)).thenReturn(news);
        when(newsRepositoryImpl.update(news)).thenReturn(news);
        when(newsMapper.toDto(news)).thenReturn(newsDto);
        NewsDto actual = newsServiceImpl.update(newsDto);
        assertEquals(newsDto, actual);
        verify(newsRepositoryImpl, times(1)).update(news);
        List<Long> tagIds = new ArrayList();
        tagIds.add(1L);
    }

    @Test
    public void updateWithNotExistsTags() {
        List<TagDto> tagDtos = new ArrayList();
        tagDtos.add(new TagDto("spring"));
        Tag tag = new Tag("spring");
        Tag tag1 = new Tag(1L, "spring");
        AuthorDto authorDto = new AuthorDto(1L, "misha", "kol");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));
        NewsDto newsDto = new NewsDto(1L,"title", "short", "full", date, date);
        newsDto.setTags(tagDtos);
        newsDto.setAuthor(authorDto);
        News news = new News(1L,"title","short","full", date, date);
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1L, "misha", "kol"));
        when(authorRepositoryImpl.query(any(AuthorSpecification.class))).thenReturn(authors);
        when(newsMapper.toEntity(newsDto)).thenReturn(news);
        when(newsRepositoryImpl.update(news)).thenReturn(news);
        when(newsMapper.toDto(news)).thenReturn(newsDto);
        when(tagRepositoryImpl.add(tag)).thenReturn(tag1);
        NewsDto actual = newsServiceImpl.update(newsDto);
        assertEquals(newsDto, actual);
        verify(newsRepositoryImpl, times(1)).update(news);
        List<Long> tagIds = new ArrayList();
        tagIds.add(1L);
    }

    @Test
    public void remove() {
        NewsDto newsDto = new NewsDto();
        newsDto.setId(1L);
        News news = new News();
        news.setId(1L);
        when(newsMapper.toEntity(newsDto)).thenReturn(news);
        when(newsRepositoryImpl.remove(news)).thenReturn(true);
        assertEquals(true, newsServiceImpl.remove(newsDto));
        verify(newsRepositoryImpl, times(1)).remove(news);
    }

    @Test
    public void get() {
        NewsDto newsDto = new NewsDto();
        newsDto.setId(1L);
        List<News> newsList = new ArrayList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));
        newsList.add(new News(1L,"title","short","full", date, date));
        NewsDto newsDto1 = new NewsDto(1L, "title", "short", "full", date, date);
        List<Author> authors = new ArrayList();
        authors.add(new Author(1L, "Misha", "Kolesnik"));
        AuthorDto authorDto = new AuthorDto(1L, "Misha", "Kolesnik");
        List<Tag> tags = new ArrayList();
        tags.add(new Tag(1L, "spring"));
        List<TagDto> tagsDTO = new ArrayList();
        tagsDTO.add(new TagDto(1L, "spring"));
        newsDto1.setTags(tagsDTO);
        newsDto1.setAuthor(authorDto);
        when(newsRepositoryImpl.query(any(NewsIdSpecification.class))).thenReturn(newsList);
        when(newsMapper.toDto(newsList.get(0))).thenReturn(newsDto1);
        NewsDto expected = new NewsDto(1L, "title", "short", "full", authorDto, date, date);
        expected.setTags(tagsDTO);
        assertEquals(expected, newsServiceImpl.get(newsDto));
    }

    @Test(expected = FindException.class)
    public void getWithException() {
        NewsDto newsDto = new NewsDto();
        newsDto.setId(1L);
        List<News> newsList = new ArrayList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));
        newsList.add(new News(1L,"title","short","full", date, date));
        NewsDto newsDto1 = new NewsDto(1L, "title", "short", "full", date, date);
        List<Author> authors = new ArrayList();
        authors.add(new Author(1L, "Misha", "Kolesnik"));
        AuthorDto authorDto = new AuthorDto(1L, "Misha", "Kolesnik");
        List<Tag> tags = new ArrayList();
        tags.add(new Tag(1L, "spring"));
        List<TagDto> tagsDTO = new ArrayList();
        tagsDTO.add(new TagDto(1L, "spring"));
        newsDto1.setTags(tagsDTO);
        newsDto1.setAuthor(authorDto);
        List<News> newsList1 = new ArrayList<>();
        when(newsRepositoryImpl.query(any(NewsIdSpecification.class))).thenReturn(newsList1);
        when(newsMapper.toDto(newsList.get(0))).thenReturn(newsDto1);
        NewsDto expected = new NewsDto(1L, "title", "short", "full", authorDto, date, date);
        expected.setTags(tagsDTO);
        assertEquals(expected, newsServiceImpl.get(newsDto));
    }

    @Test
    public void getByCriteria() {
        NewsCriteriaDto newsCriteriaDto = new NewsCriteriaDto();
        newsCriteriaDto.setAuthorDto(new AuthorDto("Misha", "Kolesnik"));
        NewsCriteria newsCriteria = new NewsCriteria();
        newsCriteria.setAuthor(new Author("Misha", "Kolesnik"));
        List<News> news = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));
        news.add(new News(1L, "title", "short", "full", date, date));
        List<NewsDto> newsDtos = new ArrayList<>();
        newsDtos.add(new NewsDto(1L, "title", "short", "full", date, date));
        when(newsCriteriaMapper.toEntity(newsCriteriaDto)).thenReturn(newsCriteria);
        when(newsMapper.toDtoList(news)).thenReturn(newsDtos);
        when(newsRepositoryImpl.query(any(NewsCriteriaSpecification.class))).thenReturn(news);
        List<NewsDto> expected = new ArrayList<>();
        expected.add(new NewsDto(1L, "title", "short", "full", date, date));
        List<NewsDto> actual = newsServiceImpl.getByCriteria(newsCriteriaDto);
        assertEquals(expected, actual);
    }

    @Test
    public void getCount() {
        when(newsRepositoryImpl.getCount()).thenReturn(1L);
        Long expected = 1L;
        Long actual = newsServiceImpl.getCount();
        assertEquals(expected, actual);
        verify(newsRepositoryImpl, times(1)).getCount();
    }
}