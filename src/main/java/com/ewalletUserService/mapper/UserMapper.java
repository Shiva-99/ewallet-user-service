package com.ewalletUserService.mapper;

import com.ewalletUserService.dto.UserDto;
import com.ewalletUserService.entity.User;

public class UserMapper {

    public static User maptoUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRole()
        );
    }

    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }
}