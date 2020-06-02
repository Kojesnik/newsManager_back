package com.epam.lab.repository.impl;

import com.epam.lab.TestConfig;
import com.epam.lab.exception.InsertException;
import com.epam.lab.exception.RemoveException;
import com.epam.lab.exception.UpdateException;
import com.epam.lab.model.impl.Author;
import com.epam.lab.model.impl.News;
import com.epam.lab.model.impl.NewsCriteria;
import com.epam.lab.model.impl.Tag;
import com.epam.lab.specification.impl.news.NewsAuthorIdSpecification;
import com.epam.lab.specification.impl.news.NewsCriteriaSpecification;
import com.epam.lab.specification.impl.news.NewsIdSpecification;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Sql(scripts = "classpath:drop_all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class NewsRepositoryImplTest {

    @Autowired
    private NewsRepositoryImpl newsRepositoryImpl;

    @Test
    public void add() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));
        News expected = new News(21L,"title21","short_text21","full_text22",
                date, date);
        News actual = newsRepositoryImpl.add(new News("title21", "short_text21", "full_text22",
                date, date));
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Author author = new Author(1L, "Misha", "Kolesnik");
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(2L, "epam"));
        tags.add(new Tag(3L, "food"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.of(2020,10,11).format(formatter));
        News expected = new News(1L, "title21", "short_text21", "full_text22",
                date, date);
        expected.setAuthor(author);
        expected.setTags(tags);
        News news = new News(1L,"title21", "short_text21", "full_text22",
                date, date);
        news.setAuthor(author);
        news.setTags(tags);
        News actual = newsRepositoryImpl.update(news);
        assertEquals(expected, actual);
    }

    @Test(expected = UpdateException.class)
    public void updateWithException() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));
        newsRepositoryImpl.update(new News(10000L,"title21", "short_text21", "full_text22",
                date, date));
    }

    @Test
    public void getCount() {
        Long expected = 20L;
        Long actual = newsRepositoryImpl.getCount();
        assertEquals(expected, actual);
    }

    @Test
    public void getCriteriaCount() {
        NewsCriteria newsCriteria = new NewsCriteria();
        List<String> tags = new ArrayList<>();
        tags.add("auto");
        newsCriteria.setTagNames(tags);
        newsCriteria.setAuthor(new Author("Misha", "Kolesnik"));
        Long expected = 2L;
        Long actual = newsRepositoryImpl.getCriteriaCount(newsCriteria);
        assertEquals(expected, actual);
    }

    @Test
    public void remove() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));
        Boolean expected = true;
        Boolean actual = newsRepositoryImpl.remove(new News(20L, "title20", "short_text20", "full_text20",
                date, date));
        assertEquals(expected, actual);
    }

    @Test(expected = RemoveException.class)
    public void removeWithException() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));
        newsRepositoryImpl.remove(new News(1000L, "title1", "short_text1", "full_text1",
                date, date));
    }

    @Test
    public void queryByAuthorAndTagsCriteria() {
        NewsCriteria newsCriteria = new NewsCriteria();
        Author author = new Author(10L,"Ivan","Ivanov");
        newsCriteria.setAuthor(author);
        List<String> tags = new ArrayList();
        tags.add("auto");
        tags.add("policy");
        newsCriteria.setTagNames(tags);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.of(2020,10,11).format(formatter));
        List<News> expected = new ArrayList();
        News news1 = new News(13L, "title13", "short_text13", "full_text13", date, date);
        news1.setAuthor(author);
        List<Tag> tags1 = new ArrayList<>();
        tags1.add(new Tag(1L, "auto"));
        tags1.add(new Tag(4L, "policy"));
        tags1.add(new Tag(19L, "gifts"));
        tags1.add(new Tag(11L, "java"));
        news1.setTags(tags1);
        News news2 = new News(14L, "title14", "short_text14", "full_text14", date, date);
        news2.setAuthor(author);
        List<Tag> tags2 = new ArrayList<>();
        tags2.add(new Tag(1L, "auto"));
        tags2.add(new Tag(4L, "policy"));
        tags2.add(new Tag(19L, "gifts"));
        news2.setTags(tags2);
        expected.add(news1);
        expected.add(news2);
        List<News> actual = newsRepositoryImpl.query(new NewsCriteriaSpecification(newsCriteria));
        assertEquals(expected, actual);
    }

    @Test
    public void queryByAuthorCriteria() {
        Author author = new Author(10L,"Ivan","Ivanov");
        NewsCriteria newsCriteria = new NewsCriteria();
        newsCriteria.setAuthor(new Author("Ivan","Ivanov"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.of(2020,10,11).format(formatter));
        List<News> expected = new ArrayList();
        News news1 = new News(13L, "title13", "short_text13", "full_text13", date, date);
        news1.setAuthor(author);
        List<Tag> tags1 = new ArrayList<>();
        tags1.add(new Tag(1L, "auto"));
        tags1.add(new Tag(4L, "policy"));
        tags1.add(new Tag(19L, "gifts"));
        tags1.add(new Tag(11L, "java"));
        news1.setTags(tags1);
        News news2 = new News(14L, "title14", "short_text14", "full_text14", date, date);
        news2.setAuthor(author);
        List<Tag> tags2 = new ArrayList<>();
        tags2.add(new Tag(1L, "auto"));
        tags2.add(new Tag(4L, "policy"));
        tags2.add(new Tag(19L, "gifts"));
        news2.setTags(tags2);
        News news3 = new News(15L, "title15", "short_text15", "full_text15", date, date);
        news3.setAuthor(author);
        List<Tag> tags3 = new ArrayList<>();
        tags3.add(new Tag(9L, "computer"));
        news3.setTags(tags3);
        expected.add(news1);
        expected.add(news2);
        expected.add(news3);
        List<News> actual = newsRepositoryImpl.query(new NewsCriteriaSpecification(newsCriteria));
        assertEquals(expected, actual);
    }

    @Test
    public void queryByTagsCriteria() {
        Author author0 = new Author(8L, "Roman", "Kreed");
        Author author2 = new Author(10L, "Ivan", "Ivanov");
        Author author3 = new Author(13L, "Nikita", "Koruzin");
        NewsCriteria newsCriteria = new NewsCriteria();
        List<String> tags = new ArrayList();
        tags.add("auto");
        tags.add("policy");
        newsCriteria.setTagNames(tags);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.of(2020,10,11).format(formatter));
        List<News> expected = new ArrayList();
        News news0 = new News(11L, "title11", "short_text11", "full_text11", date, date);
        news0.setAuthor(author0);
        List<Tag> tags0 = new ArrayList<>();
        tags0.add(new Tag(4L, "policy"));
        tags0.add(new Tag(10L, "home"));
        tags0.add(new Tag(1L, "auto"));
        news0.setTags(tags0);
        News news1 = new News(13L, "title13", "short_text13", "full_text13", date, date);
        news1.setAuthor(author2);
        List<Tag> tags1 = new ArrayList<>();
        tags1.add(new Tag(1L, "auto"));
        tags1.add(new Tag(4L, "policy"));
        tags1.add(new Tag(19L, "gifts"));
        tags1.add(new Tag(11L, "java"));
        news1.setTags(tags1);
        News news2 = new News(14L, "title14", "short_text14", "full_text14", date, date);
        news2.setAuthor(author2);
        List<Tag> tags2 = new ArrayList<>();
        tags2.add(new Tag(1L, "auto"));
        tags2.add(new Tag(4L, "policy"));
        tags2.add(new Tag(19L, "gifts"));
        news2.setTags(tags2);
        News news3 = new News(19L, "title19", "short_text19", "full_text19", date, date);
        news3.setAuthor(author3);
        List<Tag> tags3 = new ArrayList<>();
        tags3.add(new Tag(7L, "family"));
        tags3.add(new Tag(4L, "policy"));
        tags3.add(new Tag(1L, "auto"));
        tags3.add(new Tag(20L, "jewelry"));
        tags3.add(new Tag(18L, "cooking"));
        news3.setTags(tags3);
        expected.add(news1);
        expected.add(news2);
        expected.add(news3);
        expected.add(news0);
        List<News> actual = newsRepositoryImpl.query(new NewsCriteriaSpecification(newsCriteria));
        assertEquals(expected, actual);
    }

    @Test
    public void queryById() {
        Author author = new Author(10L,"Ivan","Ivanov");
        List<News> expected = new ArrayList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.of(2020,10,11).format(formatter));
        News news1 = new News(13L, "title13", "short_text13", "full_text13", date, date);
        news1.setAuthor(author);
        List<Tag> tags1 = new ArrayList<>();
        tags1.add(new Tag(1L, "auto"));
        tags1.add(new Tag(4L, "policy"));
        tags1.add(new Tag(19L, "gifts"));
        tags1.add(new Tag(11L, "java"));
        news1.setTags(tags1);
        expected.add(news1);
        List<News> actual = newsRepositoryImpl.query(new NewsIdSpecification(13L));
        assertEquals(expected, actual);
    }

    @Test
    public void queryByAuthorId() {
        Author author = new Author(2L,"Ilya","Belevich");
        List<News> expected = new ArrayList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.of(2020,10,11).format(formatter));
        News news1 = new News(3L, "title3", "short_text3", "full_text3", date, date);
        news1.setAuthor(author);
        List<Tag> tags1 = new ArrayList<>();
        tags1.add(new Tag(16L, "books"));
        tags1.add(new Tag(17L, "films"));
        news1.setTags(tags1);
        expected.add(news1);
        List<News> actual = newsRepositoryImpl.query(new NewsAuthorIdSpecification(2L));
        assertEquals(expected, actual);
    }

}