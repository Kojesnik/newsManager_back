package com.epam.lab.service.impl;

import com.epam.lab.model.impl.User;
import com.epam.lab.repository.UserRepository;
import com.epam.lab.repository.impl.UserRepositoryImpl;
import com.epam.lab.specification.impl.user.UserLoginSpecification;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ClientDetailsServiceImplTest {

    private ClientDetailsServiceImpl clientDetailsService;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Before
    public void setUp() throws Exception {
        userRepository = mock(UserRepositoryImpl.class);
        bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        clientDetailsService = new ClientDetailsServiceImpl(userRepository, bCryptPasswordEncoder);
    }

    @Test
    public void loadClientByClientId() {
        BaseClientDetails expected = new BaseClientDetails();
        List<String> grantTypes = new ArrayList<>();
        Collections.addAll(grantTypes, "password", "authorization_code", "refresh_token");
        List<String> scopes = new ArrayList<>();
        Collections.addAll(scopes, "read", "create", "update");
        expected.setClientId("user");
        expected.setClientSecret("user");
        expected.setAuthorizedGrantTypes(grantTypes);
        expected.setScope(scopes);
        expected.setAutoApproveScopes(scopes);
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Misha", "Kolesnik", "user", "user", "user"));
        when(userRepository.query(any(UserLoginSpecification.class))).thenReturn(users);
        when(bCryptPasswordEncoder.encode("user")).thenReturn("user");
        BaseClientDetails actual = (BaseClientDetails) clientDetailsService.loadClientByClientId("user");
        assertEquals(expected, actual);
    }
}