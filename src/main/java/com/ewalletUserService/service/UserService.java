package com.ewalletUserService.service;

import com.ewalletUserService.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long id);
    List<UserDto> getAllUser();
    UserDto editUser(Long id, Map<String, Object> values);
    boolean deleteUser(Long id);
}
