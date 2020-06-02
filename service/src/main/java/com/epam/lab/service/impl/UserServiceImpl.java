package com.epam.lab.service.impl;

import com.epam.lab.dto.impl.UserDto;
import com.epam.lab.exception.FindException;
import com.epam.lab.mapper.AbstractMapper;
import com.epam.lab.model.impl.Tag;
import com.epam.lab.model.impl.User;
import com.epam.lab.repository.UserRepository;
import com.epam.lab.service.UserService;
import com.epam.lab.specification.impl.tag.TagIdSpecification;
import com.epam.lab.specification.impl.user.UserLoginSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private AbstractMapper<UserDto, User> userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AbstractMapper<UserDto, User> userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto add(UserDto userDto) {
        return userMapper.toDto(userRepository.add(userMapper.toEntity(userDto)));
    }

    @Override
    public UserDto update(UserDto dto) {
        return null;
    }

    @Override
    public Boolean remove(UserDto userDto) {
        return userRepository.remove(userMapper.toEntity(userDto));
    }

    @Override
    public UserDto get(UserDto userDto) {
        List<User> users = userRepository.query(new UserLoginSpecification(userDto.getLogin()));
        if (users.isEmpty()) {
            throw new FindException("No user found with such login");
        }
        return userMapper.toDto(users.get(0));
    }

}
