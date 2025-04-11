package io.github.jthamayo.backend.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.jthamayo.backend.dto.UserDto;
import io.github.jthamayo.backend.entity.User;
import io.github.jthamayo.backend.exception.ResourceNotFoundException;
import io.github.jthamayo.backend.mapper.UserMapper;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
	this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
	User user = UserMapper.mapToUser(userDto);
	User savedUser = userRepository.save(user);
	return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
	User user = userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + userId));
	return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
	List<User> users = userRepository.findAll();
	return users.stream().map((user) -> UserMapper.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long userId, UserDto updatedUser) {
	User user = userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + userId));
	user.setFirstName(updatedUser.getFirstName());
	user.setLastName(updatedUser.getLastName());
	user.setEmail(updatedUser.getEmail());
	return UserMapper.mapToUserDto(userRepository.save(user));
    }

}
