package com.epam.lab.service.impl;

import com.epam.lab.model.impl.User;
import com.epam.lab.repository.UserRepository;
import com.epam.lab.specification.impl.user.UserIdSpecification;
import com.epam.lab.specification.impl.user.UserLoginSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    private static final String ADMIN_ROLE = "admin";
    private static final String USER_ROLE = "user";

    private static final String READ_SCOPE = "read";
    private static final String CREATE_SCOPE = "create";
    private static final String UPDATE_SCOPE = "update";
    private static final String DELETE_SCOPE = "delete";

    private static final String PASSWORD_GRANT_TYPE = "password";
    private static final String AUTHORIZATION_CODE_GRANT_TYPE = "authorization_code";
    private static final String REFRESH_TOKEN_GRANT_TYPE = "refresh_token";
    private static final String IMPLICIT_GRANT_TYPE = "implicit";

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ClientDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public ClientDetailsServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public ClientDetails loadClientByClientId(String login) throws ClientRegistrationException {
        System.out.println("HERE => " + login);
        List<User> users;
        if (isDigit(login)) {
            users = userRepository.query(new UserIdSpecification(Integer.parseInt(login)));
        } else {
            users = userRepository.query(new UserLoginSpecification(login));
        }
        User user = users.get(0);
        List<String> grantTypes = new ArrayList<>();
        Collections.addAll(grantTypes, PASSWORD_GRANT_TYPE, AUTHORIZATION_CODE_GRANT_TYPE, REFRESH_TOKEN_GRANT_TYPE, IMPLICIT_GRANT_TYPE);
        List<String> scopes = new ArrayList<>();
        Set<String > uris = new HashSet<>();
        uris.add("http://localhost:8889/login");
        if (user.getRole().equals(USER_ROLE)) {
            Collections.addAll(scopes, READ_SCOPE, CREATE_SCOPE, UPDATE_SCOPE);
        } else if (user.getRole().equals(ADMIN_ROLE)) {
            Collections.addAll(scopes, READ_SCOPE, CREATE_SCOPE, UPDATE_SCOPE, DELETE_SCOPE);
        }
        BaseClientDetails base = new BaseClientDetails();
        base.setClientId(user.getLogin());
        base.setClientSecret(passwordEncoder.encode(user.getPassword()));
        base.setAuthorizedGrantTypes(grantTypes);
        base.setScope(scopes);
        base.setRegisteredRedirectUri(uris);

        base.setAutoApproveScopes(scopes);
        return base;
    }

    private boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
