package io.github.jthamayo.backend.mapper;

import java.util.ArrayList;
import java.util.stream.Collectors;

import io.github.jthamayo.backend.dto.UserDto;
import io.github.jthamayo.backend.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
	return new UserDto(user.getId(), user.getFirstName(), user.getLastName(),user.getUsername(), user.getEmail(), user.getPhoneNumber(),
		user.getGroup() != null ? user.getGroup().getId() : null,
		user.getJobs() != null ? user.getJobs().stream().map((job) -> job.getId()).collect(Collectors.toList())
			: new ArrayList<>(),
		user.getHomeAddress() != null ? user.getHomeAddress().getId() : null);
    }

    public static User mapToUser(UserDto userDto) {
	return new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(),userDto.getUsername(), userDto.getEmail(),
		userDto.getPhoneNumber());
    }
}
