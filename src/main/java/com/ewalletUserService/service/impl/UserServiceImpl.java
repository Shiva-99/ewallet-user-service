package com.ewalletUserService.service.impl;

import com.ewalletUserService.dto.UserDto;
import com.ewalletUserService.entity.User;
import com.ewalletUserService.mapper.UserMapper;
import com.ewalletUserService.repository.UserRepository;
import com.ewalletUserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.maptoUser(userDto);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream()
                .map(UserMapper::mapToUserDto)
                .toList();
    }

    @Override
    public UserDto editUser(Long id, Map<String, Object> values) {
        return UserMapper.mapToUserDto(userRepository
                .findById(id)
                .map(user -> {
            if(values.get("username") != null) user.setUsername((String) values.get("username"));
            if(values.get("email") != null) user.setEmail((String) values.get("email"));
            if(values.get("password") != null) user.setPassword((String) values.get("password"));
            if(values.get("role") != null) user.setRole((String) values.get("role"));
            return userRepository.save(user);
        })
                .orElseThrow(() -> new RuntimeException("User Not Found")));
    }

    @Override
    public boolean deleteUser(Long id) {
        if(!userRepository.existsById(id)) {
            return false;
        } else {
            userRepository.deleteById(id);
            return true;
        }
    }
}
