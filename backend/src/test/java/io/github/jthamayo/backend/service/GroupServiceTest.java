package io.github.jthamayo.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.jthamayo.backend.dto.GroupDto;
import io.github.jthamayo.backend.entity.Group;
import io.github.jthamayo.backend.entity.User;
import io.github.jthamayo.backend.mapper.GroupMapper;
import io.github.jthamayo.backend.repository.GroupRepository;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.service.impl.GroupServiceImpl;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {

    private List<User> users;
    private List<Long> usersDto;

    @Mock
    private GroupRepository groupRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private GroupServiceImpl groupService;

    @Nested
    class CreateGroupTest {

	@BeforeEach
	void setUp() {
	    User user1 = new User(2L, "testUser1", "lastname", "username", "email", "phoneNumber");
	    User user2 = new User(3L, "testUser2", "lastname", "username", "email", "phoneNumber");
	    users = List.of(user1, user2);
	    usersDto = List.of(user1.getId(), user2.getId());
	    when(userRepository.findById(2L)).thenReturn(Optional.of(user1));
	    when(userRepository.findById(3L)).thenReturn(Optional.of(user2));
	}

	@Test
	void testCreateGroup() {
	    Group group = new Group(1L, users);
	    Group savedGroup = new Group(1L, users);
	    GroupDto groupDto = new GroupDto(usersDto);
	    GroupDto resultGroup = new GroupDto(usersDto);

	    when(groupRepository.save(group)).thenReturn(savedGroup);

	    try (MockedStatic<GroupMapper> mapperMock = mockStatic(GroupMapper.class)) {
		mapperMock.when(() -> GroupMapper.mapToGroup(groupDto)).thenReturn(group);
		mapperMock.when(() -> GroupMapper.mapToGroupDto(savedGroup)).thenReturn(resultGroup);

		GroupDto result = groupService.createGroup(groupDto);
		assertNotNull(result);
		// TODO assertEquals
	    }
	}
	
	@Test
	void updateGroup() {
	    Long groupId = 1L;
	    GroupDto upatedGroup = new GroupDto(usersDto);
	    Group group = new Group(groupId, users);
	    Group savedGroup = new Group(groupId, users);
	    GroupDto savedGroupDto = new GroupDto(usersDto);

	    when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));
	    when(groupRepository.save(group)).thenReturn(savedGroup);
	    
	    try (MockedStatic<GroupMapper> mapperMock = mockStatic(GroupMapper.class)) {
		mapperMock.when(() -> GroupMapper.mapToGroup(upatedGroup)).thenReturn(group);
		mapperMock.when(() -> GroupMapper.mapToGroupDto(savedGroup)).thenReturn(savedGroupDto);
		GroupDto result = groupService.updateGroup(groupId, upatedGroup);
		assertNotNull(result);
		
	    }


	}

    }

    @Nested
    class getGroupTest {

	@BeforeEach
	void setUp() {
	    User user1 = new User(2L, "testUser1", "lastname", "username", "email", "phoneNumber");
	    User user2 = new User(3L, "testUser2", "lastname", "username", "email", "phoneNumber");
	    users = List.of(user1, user2);
	    usersDto = List.of(user1.getId(), user2.getId());
	}

	@Test
	void testGetGroupById() {
	    Long groupId = 1L;
	    Group group = new Group(groupId, users);
	    GroupDto groupDto = new GroupDto(usersDto);

	    when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));

	    try (MockedStatic<GroupMapper> mapperMock = mockStatic(GroupMapper.class)) {
		mapperMock.when(() -> GroupMapper.mapToGroupDto(group)).thenReturn(groupDto);

		GroupDto result = groupService.getGroupById(groupId);
		assertNotNull(result);
	    }

	}

	@Test
	void testGetAllGroups() {
	    Group group1 = new Group(1L, users);
	    Group group2 = new Group(2L, users);
	    GroupDto groupDto1 = new GroupDto(usersDto);
	    GroupDto groupDto2 = new GroupDto(usersDto);
	    List<Group> groups = List.of(group1, group2);

	    when(groupRepository.findAll()).thenReturn(groups);

	    try (MockedStatic<GroupMapper> mapperMock = mockStatic(GroupMapper.class)) {
		mapperMock.when(() -> GroupMapper.mapToGroupDto(group1)).thenReturn(groupDto1);
		mapperMock.when(() -> GroupMapper.mapToGroupDto(group2)).thenReturn(groupDto2);

		List<GroupDto> result = groupService.getAllGroups();
		assertNotNull(result);
		assertEquals(2, result.size());
	    }
	}

	@Test
	void testDeleteGroup() {
	    Long groupId = 1L;

	    Group group = new Group(groupId, users);

	    when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));
	    groupService.deleteGroup(groupId);
	    verify(groupRepository).deleteById(groupId);
	}
    }
}
