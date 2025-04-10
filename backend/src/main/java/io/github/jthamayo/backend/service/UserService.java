package io.github.jthamayo.backend.service;

import io.github.jthamayo.backend.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userId);
}
