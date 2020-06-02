package com.epam.lab.service.impl;

import com.epam.lab.exception.FindException;
import com.epam.lab.repository.UserRepository;
import com.epam.lab.specification.impl.user.UserLoginSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.epam.lab.model.impl.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public  UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public  UserDetailsServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userRepository.query(new UserLoginSpecification(username));
        if (users.isEmpty()) {
            throw new FindException("No user found with such login");
        }
        GrantedAuthority authority = new SimpleGrantedAuthority(users.get(0).getRole());
        return new org.springframework.security.core.userdetails.User(users.get(0).getLogin(), passwordEncoder.encode(users.get(0).getPassword()), Arrays.asList(authority));
    }
}
