package com.epam.lab.repository.impl;

import com.epam.lab.TestConfig;
import com.epam.lab.exception.RemoveException;
import com.epam.lab.model.impl.User;
import com.epam.lab.repository.UserRepository;
import com.epam.lab.specification.impl.user.UserIdSpecification;
import com.epam.lab.specification.impl.user.UserLoginSpecification;
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
public class UserRepositoryImplTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void add() {
        User expected = new User(3, "Ray", "Klaymong", "ray", "pass", "user");
        User actual = userRepository.add(new User("Ray", "Klaymong", "ray", "pass", "user"));
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
    }

    @Test
    public void remove() {
        boolean expected = true;
        boolean actual = userRepository.remove(new User(1, "Misha", "Kolesnik", "admin", "admin", "admin"));
        assertEquals(expected, actual);
    }

    @Test(expected = RemoveException.class)
    public void removeWithException() {
        userRepository.remove(new User(500, "Misha", "Kolesnik", "admin", "admin", "admin"));
    }

    @Test
    public void queryById() {
        List<User> expected = new ArrayList<>();
        expected.add(new User(1, "Misha", "Kolesnik", "admin", "admin", "admin"));
        List<User> actual = userRepository.query(new UserIdSpecification(1));
        assertEquals(expected, actual);
    }

    @Test
    public void queryByLogin() {
        List<User> expected = new ArrayList<>();
        expected.add(new User(1, "Misha", "Kolesnik", "admin", "admin", "admin"));
        List<User> actual = userRepository.query(new UserLoginSpecification("admin"));
        assertEquals(expected, actual);
    }
}