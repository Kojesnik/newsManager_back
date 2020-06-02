package com.epam.lab.service.impl;

import com.epam.lab.dto.impl.UserDto;
import com.epam.lab.exception.FindException;
import com.epam.lab.mapper.AbstractMapper;
import com.epam.lab.mapper.impl.UserMapper;
import com.epam.lab.model.impl.User;
import com.epam.lab.repository.UserRepository;
import com.epam.lab.repository.impl.UserRepositoryImpl;
import com.epam.lab.service.UserService;
import com.epam.lab.specification.impl.user.UserIdSpecification;
import com.epam.lab.specification.impl.user.UserLoginSpecification;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private UserService userService;
    private UserRepository userRepository;
    private AbstractMapper<UserDto, User> userMapper;

    @Before
    public void setUp() throws Exception {
        userRepository = mock(UserRepositoryImpl.class);
        userMapper = new UserMapper(new ModelMapper());
        userService = new UserServiceImpl(userRepository, userMapper);
    }

    @Test
    public void add() {
        UserDto expected = new UserDto(1, "Misha", "Kolesnik", "admin", "admin", "admin");
        when(userRepository.add(any(User.class))).thenReturn(new User(1, "Misha", "Kolesnik", "admin", "admin", "admin"));
        UserDto actual = userService.add(new UserDto("Misha", "Kolesnik", "admin", "admin", "admin"));
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
    }

    @Test
    public void remove() {
        Boolean expected = true;
        when(userRepository.remove(any(User.class))).thenReturn(true);
        Boolean actual = userService.remove(new UserDto());
        assertEquals(expected, actual);
    }

    @Test
    public void get() {
        List<User> users = Arrays.asList(new User(1, "Misha", "Kolesnik", "admin", "admin", "admin"));
        when(userRepository.query(any(UserLoginSpecification.class))).thenReturn(users);
        UserDto expected = new UserDto(1, "Misha", "Kolesnik", "admin", "admin", "admin");
        UserDto userDto = new UserDto();
        userDto.setLogin("admin");
        UserDto actual = userService.get(userDto);
        assertEquals(expected, actual);
    }

    @Test(expected = FindException.class)
    public void getWithException() {
        List<User> users = new ArrayList<>();
        when(userRepository.query(any(UserLoginSpecification.class))).thenReturn(users);
        UserDto expected = new UserDto(1, "Misha", "Kolesnik", "admin", "admin", "admin");
        UserDto userDto = new UserDto();
        userDto.setLogin("admin");
        UserDto actual = userService.get(userDto);
        assertEquals(expected, actual);
    }
}