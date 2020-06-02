package com.epam.lab.service.impl;

import com.epam.lab.dto.impl.AuthorDto;
import com.epam.lab.dto.impl.NewsCriteriaDto;
import com.epam.lab.dto.impl.NewsDto;
import com.epam.lab.dto.impl.TagDto;
import com.epam.lab.exception.AlreadyExistsException;
import com.epam.lab.exception.NotExistsException;
import com.epam.lab.exception.FindException;
import com.epam.lab.mapper.AbstractMapper;
import com.epam.lab.mapper.impl.NewsCriteriaMapper;
import com.epam.lab.model.impl.News;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.service.NewsService;
import com.epam.lab.specification.impl.author.AuthorNameSurnameSpecification;
import com.epam.lab.specification.impl.author.AuthorSpecification;
import com.epam.lab.specification.impl.news.NewsCriteriaSpecification;
import com.epam.lab.specification.impl.news.NewsIdSpecification;
import com.epam.lab.specification.impl.tag.TagNameSpecification;
import com.epam.lab.specification.impl.tag.TagSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class NewsServiceImpl implements NewsService {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private DateTimeFormatter formatter;

    private AbstractMapper<NewsDto, News> newsMapper;
    private NewsCriteriaMapper newsCriteriaMapper;

    private NewsRepository newsRepository;
    private TagRepository tagRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository, AbstractMapper<NewsDto, News> newsMapper,
                           NewsCriteriaMapper newsCriteriaMapper, TagRepository tagRepository,
                           AuthorRepository authorRepository) {
        this.newsRepository = newsRepository;
        this.tagRepository = tagRepository;
        this.authorRepository = authorRepository;
        this.newsMapper = newsMapper;
        this.newsCriteriaMapper = newsCriteriaMapper;
        formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    }

    @Override
    @Transactional
    public NewsDto add(NewsDto newsDto) {
        setDates(newsDto);
        checkTags(newsDto.getTags());
        checkAuthor(newsDto.getAuthor());
        News news = newsRepository.add(newsMapper.toEntity(newsDto));
        return newsMapper.toDto(news);
    }

    private void setDates(NewsDto newsDto) {
        newsDto.setCreationDate(LocalDate.parse(LocalDate.now().format(formatter)));
        newsDto.setModificationDate(LocalDate.parse(LocalDate.now().format(formatter)));
    }

    @Override
    @Transactional
    public NewsDto update(NewsDto newsDto) {
        newsDto.setModificationDate(LocalDate.parse(LocalDate.now().format(formatter)));
        checkTags(newsDto.getTags());
        checkAuthor(newsDto.getAuthor());
        News news = newsMapper.toEntity(newsDto);
        return newsMapper.toDto(newsRepository.update(news));
    }

    private void checkTags(List<TagDto> tags) {
        for (TagDto tagDto: tags) {
            if (tagDto.getId() == 0) {
                if (isTagNameExists(tagDto)) {
                    throw new AlreadyExistsException("Such tag already exists");
                }
            } else {
                if (isTagNotExists(tagDto)) {
                    throw new NotExistsException("Such tag not exists");
                }
            }
        }
    }

    private boolean isTagNameExists(TagDto tagDto) {
        return !tagRepository.query(new TagNameSpecification(tagDto.getName())).isEmpty();
    }

    private boolean isTagNotExists(TagDto tagDto) {
        return tagRepository.query(new TagSpecification(tagDto.getId(), tagDto.getName())).isEmpty();
    }

    private void checkAuthor(AuthorDto authorDto) {
        if (authorDto.getId() == 0) {
            if (isAuthorNameExists(authorDto)) {
                throw new AlreadyExistsException("Such author already exists");
            }
        } else {
            if (isAuthorNotExists(authorDto)) {
                throw new NotExistsException("Such author not exists");
            }
        }
    }

    private boolean isAuthorNameExists(AuthorDto authorDto) {
        return !authorRepository.query(new AuthorNameSurnameSpecification(authorDto.getName(), authorDto.getSurname())).isEmpty();
    }

    private boolean isAuthorNotExists(AuthorDto authorDto) {
        return authorRepository.query(new AuthorSpecification(authorDto.getName(), authorDto.getSurname(), authorDto.getId())).isEmpty();
    }

    @Override
    public Boolean remove(NewsDto newsDto) {
        return newsRepository.remove(newsMapper.toEntity(newsDto));
    }

    @Override
    public NewsDto get(NewsDto newsDto) {
        List<News> news = newsRepository.query(new NewsIdSpecification(newsDto.getId()));
        if (news.isEmpty()) {
            throw new FindException("No news found by such id");
        }
        newsDto = newsMapper.toDto(news.get(0));
        return newsDto;
    }

    @Override
    public List<NewsDto> getByCriteria(NewsCriteriaDto newsCriteriaDto) {
        return newsMapper.toDtoList(newsRepository.query(new NewsCriteriaSpecification(newsCriteriaMapper.toEntity(newsCriteriaDto))));
    }

    @Override
    public Long getCount() {
        return newsRepository.getCount();
    }

    @Override
    public Long getCriteriaCount(NewsCriteriaDto newsCriteriaDto) {
        return newsRepository.getCriteriaCount(newsCriteriaMapper.toEntity(newsCriteriaDto));
    }

}
