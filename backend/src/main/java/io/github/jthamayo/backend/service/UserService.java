package io.github.jthamayo.backend.service;

import java.util.List;

import io.github.jthamayo.backend.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long userId, UserDto updatedUser);
}
