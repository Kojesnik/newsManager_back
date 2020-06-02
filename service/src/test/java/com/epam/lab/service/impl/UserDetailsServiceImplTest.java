package com.epam.lab.service.impl;

import com.epam.lab.exception.FindException;
import com.epam.lab.model.impl.User;
import com.epam.lab.repository.UserRepository;
import com.epam.lab.repository.impl.UserRepositoryImpl;
import com.epam.lab.specification.impl.user.UserLoginSpecification;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTest {

    private UserDetailsServiceImpl userDetailsServiceImpl;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Before
    public void setUp() throws Exception {
        userRepository = mock(UserRepositoryImpl.class);
        bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        userDetailsServiceImpl = new UserDetailsServiceImpl(userRepository, bCryptPasswordEncoder);
    }

    @Test
    public void loadUserByUsername() {
        UserDetails expected = new org.springframework.security.core.userdetails.User("admin", "admin", Arrays.asList(new SimpleGrantedAuthority("admin")));
        List<User> users = Arrays.asList(new User("Misha", "Kolesnik", "admin", "admin", "admin"));
        when(userRepository.query(any(UserLoginSpecification.class))).thenReturn(users);
        when(bCryptPasswordEncoder.encode("admin")).thenReturn("admin");
        UserDetails actual = userDetailsServiceImpl.loadUserByUsername("admin");
    }

    @Test(expected = FindException.class)
    public void loadUserByUsernameWithException() {
        UserDetails expected = new org.springframework.security.core.userdetails.User("admin", "admin", Arrays.asList(new SimpleGrantedAuthority("admin")));
        List<User> users = new ArrayList<>();
        when(userRepository.query(any(UserLoginSpecification.class))).thenReturn(users);
        when(bCryptPasswordEncoder.encode("admin")).thenReturn("admin");
        UserDetails actual = userDetailsServiceImpl.loadUserByUsername("admin");
    }
}