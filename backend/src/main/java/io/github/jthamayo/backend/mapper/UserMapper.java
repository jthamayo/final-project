package io.github.jthamayo.backend.mapper;

import io.github.jthamayo.backend.dto.UserDto;
import io.github.jthamayo.backend.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
	return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getNumber(),
		user.getGroup() != null ? user.getGroup().getId() : null);
    }

    public static User mapToUser(UserDto userDto) {
	return new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(),
		userDto.getNumber());
    }
}
