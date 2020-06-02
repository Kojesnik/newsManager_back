package com.epam.lab.repository.impl;

import com.epam.lab.TestConfig;
import com.epam.lab.exception.RemoveException;
import com.epam.lab.exception.UpdateException;
import com.epam.lab.model.impl.Author;
import com.epam.lab.specification.impl.author.AuthorIdSpecification;
import com.epam.lab.specification.impl.author.AuthorNameSurnameSpecification;
import com.epam.lab.specification.impl.author.AuthorSpecification;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Sql(scripts = "classpath:drop_all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AuthorRepositoryImplTest {

    @Autowired
    private AuthorRepositoryImpl authorRepositoryImpl;

    @Test
    public void add() {
        Author expected = new Author(21L, "Dima", "Kulenkov");
        Author actual = authorRepositoryImpl.add(new Author("Dima", "Kulenkov"));
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Author expected = new Author(2L, "Ilya", "Bulka");
        Author actual = authorRepositoryImpl.update(new Author(2L, "Ilya", "Bulka"));
        assertEquals(expected, actual);
    }

    @Test(expected = UpdateException.class)
    public void updateWithException() {
        authorRepositoryImpl.update(new Author(20000L, "Ilya", "Bulka"));
    }

    @Test
    public void remove() {
        Boolean expected = true;
        Boolean actual = authorRepositoryImpl.remove(new Author(3L, "Nikita", "Kolos"));
        assertEquals(expected, actual);
    }

    @Test(expected = RemoveException.class)
    public void removeWithException() {
        authorRepositoryImpl.remove(new Author(300L, "Nikita", "Kolos"));
    }

    @Test
    public void queryById() {
        List<Author> expected = new ArrayList<>();
        List<Author> actual =  authorRepositoryImpl.query(new AuthorIdSpecification(1L));
        expected.add(new Author(1L, "Misha", "Kolesnik"));
        assertEquals(expected, actual);
    }

    @Test
    public void queryByNameAndSurname() {
        List<Author> expected = new ArrayList<>();
        List<Author> actual = authorRepositoryImpl.query(new AuthorNameSurnameSpecification("Misha", "Kolesnik"));
        expected.add(new Author(1L, "Misha", "Kolesnik"));
        assertEquals(expected, actual);
    }

    @Test
    public void queryByNameAndSurnameAndId() {
        List<Author> expected = new ArrayList<>();
        List<Author> actual = authorRepositoryImpl.query(new AuthorSpecification("Misha", "Kolesnik", 1L));
        expected.add(new Author(1L, "Misha", "Kolesnik"));
        assertEquals(expected, actual);
    }

}