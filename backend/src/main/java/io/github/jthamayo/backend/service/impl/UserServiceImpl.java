package io.github.jthamayo.backend.service.impl;

import java.util.Optional;

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
	// TODO Auto-generated method stub
	User user = UserMapper.mapToUser(userDto);
	User savedUser = userRepository.save(user);
	return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
	// TODO Auto-generated method stub
	User user = userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + userId));
	return UserMapper.mapToUserDto(user);
    }

}
