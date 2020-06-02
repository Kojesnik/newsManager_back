package com.epam.lab.repository.impl;

import com.epam.lab.TestConfig;
import com.epam.lab.exception.RemoveException;
import com.epam.lab.exception.UpdateException;
import com.epam.lab.model.impl.Tag;
import com.epam.lab.specification.impl.tag.TagAllSpecification;
import com.epam.lab.specification.impl.tag.TagIdSpecification;
import com.epam.lab.specification.impl.tag.TagNameSpecification;
import com.epam.lab.specification.impl.tag.TagSpecification;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Sql(scripts = "classpath:drop_all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TagRepositoryImplTest {

    @Autowired
    private TagRepositoryImpl tagRepositoryImpl;

    @Test
    public void add() {
        Tag expected = new Tag(21L, "english");
        Tag actual = tagRepositoryImpl.add(new Tag("english"));
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Tag expected = new Tag(1L, "auto");
        Tag actual = tagRepositoryImpl.update(new Tag(1L, "auto"));
        assertEquals(expected, actual);
    }

    @Test(expected = UpdateException.class)
    public void updateWithException() {
        tagRepositoryImpl.update(new Tag(10000L, "cars"));
    }

    @Test
    public void remove() {
        Boolean expected = true;
        Boolean actual = tagRepositoryImpl.remove(new Tag(3L, "food"));
        assertEquals(expected, actual);
    }

    @Test(expected = RemoveException.class)
    public void removeWithException() {
        tagRepositoryImpl.remove(new Tag(1000L, "food"));
    }

    @Test
    public void queryById() {
        List<Tag> expected = new ArrayList();
        expected.add(new Tag(2L, "epam"));
        List<Tag> actual = tagRepositoryImpl.query(new TagIdSpecification(2L));
        assertEquals(expected, actual);
    }

    @Test
    public void queryByName() {
        List<Tag> expected = new ArrayList();
        expected.add(new Tag(2L, "epam"));
        List<Tag> actual = tagRepositoryImpl.query(new TagNameSpecification("epam"));
        assertEquals(expected, actual);
    }

    @Test
    public void queryByNameAndId() {
        List<Tag> expected = new ArrayList();
        expected.add(new Tag(2L, "epam"));
        List<Tag> actual = tagRepositoryImpl.query(new TagSpecification(2L,"epam"));
        assertEquals(expected, actual);
    }

    @Test
    public void queryAll() {
        List<Tag> expected = new ArrayList();
        Collections.addAll(expected, new Tag(1L, "auto"), new Tag(2L, "epam"),
                new Tag(3L, "food"), new Tag(4L, "policy"), new Tag(5L, "nature"),
                new Tag(6L, "summer"), new Tag(7L, "family"), new Tag(8L, "work"),
                new Tag(9L, "computer"), new Tag(10L, "home"), new Tag(11L, "java"),
                new Tag(12L, "technic"), new Tag(13L, "web"), new Tag(14L, "vacation"),
                new Tag(15L, "kids"), new Tag(16L, "books"), new Tag(17L, "films"),
                new Tag(18L, "cooking"), new Tag(19L, "gifts"), new Tag(20L, "jewelry"));
        List<Tag> actual = tagRepositoryImpl.query(new TagAllSpecification());
        assertEquals(expected, actual);
    }

}