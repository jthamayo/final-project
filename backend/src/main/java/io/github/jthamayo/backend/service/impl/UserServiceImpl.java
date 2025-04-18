package io.github.jthamayo.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.jthamayo.backend.dto.UserDto;
import io.github.jthamayo.backend.entity.Group;
import io.github.jthamayo.backend.entity.User;
import io.github.jthamayo.backend.exception.ResourceNotFoundException;
import io.github.jthamayo.backend.mapper.UserMapper;
import io.github.jthamayo.backend.repository.GroupRepository;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private GroupRepository groupRepository;

    public UserServiceImpl(UserRepository userRepository, GroupRepository groupRepository) {
	this.userRepository = userRepository;
	this.groupRepository = groupRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
	User user = UserMapper.mapToUser(userDto);
	if (userDto.getGroupId() != null) {
	    Group group = groupRepository.findById(userDto.getGroupId())
		    .orElseThrow(() -> new ResourceNotFoundException("Group not found"));
	    user.setGroup(group);
	    group.getUsers().add(user);
	}
	return UserMapper.mapToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(Long userId, UserDto updatedUser) {
	User user = userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + userId));
	user.setFirstName(updatedUser.getFirstName());
	user.setLastName(updatedUser.getLastName());
	user.setEmail(updatedUser.getEmail());
	user.setNumber(updatedUser.getNumber());
	if (updatedUser.getGroupId() != null) {
	    Group group = groupRepository.findById(updatedUser.getGroupId())
		    .orElseThrow(() -> new ResourceNotFoundException("Group not found"));
	    user.setGroup(group);
	    //TODO Cambiar Lista de usuarios a set (?)
	    group.getUsers().add(user);
	}
	return UserMapper.mapToUserDto(userRepository.save(user));
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
    public void deleteUser(Long userId) {
	userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + userId));
	userRepository.deleteById(userId);
    }

}
