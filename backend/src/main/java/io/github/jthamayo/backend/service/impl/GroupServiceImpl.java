package io.github.jthamayo.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.jthamayo.backend.dto.GroupDto;
import io.github.jthamayo.backend.dto.UserDto;
import io.github.jthamayo.backend.entity.Group;
import io.github.jthamayo.backend.entity.User;
import io.github.jthamayo.backend.exception.ResourceNotFoundException;
import io.github.jthamayo.backend.mapper.GroupMapper;
import io.github.jthamayo.backend.mapper.UserMapper;
import io.github.jthamayo.backend.repository.GroupRepository;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;
    private UserRepository userRepository;

    public GroupServiceImpl(UserRepository userRepository, GroupRepository groupRepository) {
	this.groupRepository = groupRepository;
	this.userRepository = userRepository;
    }

    @Override
    public GroupDto createGroup(GroupDto groupDto) {
	Group group = GroupMapper.mapToGroup(groupDto);
	List<User> users = groupDto.getUserIds().stream()
		.map((userId) -> userRepository.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException("User not found")))
		.collect(Collectors.toList());
	users.forEach(user -> user.setGroup(group));
	group.setUsers(users);
	return GroupMapper.mapToGroupDto(groupRepository.save(group));
    }

    @Override
    public GroupDto getGroupById(Long groupId) {
	Group group = groupRepository.findById(groupId)
		.orElseThrow(() -> new ResourceNotFoundException("Group not found: " + groupId));
	return GroupMapper.mapToGroupDto(group);
    }

    @Override
    public List<UserDto> getAllUsers(Long groupId) {
	Group group = groupRepository.findById(groupId)
		.orElseThrow(() -> new ResourceNotFoundException("Group not found: " + groupId));
	List<UserDto> userDtos = group.getUsers().stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
	return userDtos;
    }

    @Override
    public GroupDto updateGroup(Long groupId, GroupDto groupDto) {
	Group group = groupRepository.findById(groupId)
		.orElseThrow(() -> new ResourceNotFoundException("Group not found: " + groupId));
	List<User> users = groupDto.getUserIds().stream()
		.map((userId) -> userRepository.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId)))
		.collect(Collectors.toList());
	users.forEach(user -> user.setGroup(group));
	group.setUsers(users);
	return GroupMapper.mapToGroupDto(groupRepository.save(group));
    }

    @Override
    public void deleteGroup(Long groupId) {
	groupRepository.findById(groupId)
		.orElseThrow(() -> new ResourceNotFoundException("Group not found: " + groupId));
	groupRepository.deleteById(groupId);
    }

    @Override
    public GroupDto removeUserFromGroup(Long groupId, Long userId) {
	Group group = groupRepository.findById(groupId)
		.orElseThrow(() -> new ResourceNotFoundException("Group not found: " + groupId));
	User user = userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
	user.setGroup(null);
	group.getUsers().remove(user);
	return GroupMapper.mapToGroupDto(groupRepository.save(group));

    }

}
