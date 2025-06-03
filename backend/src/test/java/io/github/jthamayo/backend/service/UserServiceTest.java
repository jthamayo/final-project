package io.github.jthamayo.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.jthamayo.backend.dto.UserDto;
import io.github.jthamayo.backend.entity.User;
import io.github.jthamayo.backend.mapper.UserMapper;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testCreateUser() {
	UserDto userDto = new UserDto("testUser", "lastname", "username", "email", "phoneNumber");
	User user = new User(1L, "testUser", "lastname", "username", "email", "phoneNumber");
	User savedUser = new User(1L, "testUser", "lastname", "username", "email", "phoneNumber");
	UserDto resultUser = new UserDto("testUser", "lastname", "username", "email", "phoneNumber");

	try (MockedStatic<UserMapper> mapperMock = mockStatic(UserMapper.class)) {
	    mapperMock.when(() -> UserMapper.mapToUser(userDto)).thenReturn(user);
	    when(userRepository.save(user)).thenReturn(savedUser);
	    mapperMock.when(() -> UserMapper.mapToUserDto(savedUser)).thenReturn(resultUser);

	    UserDto testResult = userService.createUser(userDto);
	    assertNotNull(testResult);
	    assertEquals(user, savedUser);
	}
    }

    @Test
    public void testGetUserById() {
	Long userId = 1L;
	User foundUser = new User(userId, "testUser", "lastname", "username", "email", "phoneNumber");
	UserDto userDto = new UserDto("testUser", "lastname", "username", "email", "phoneNumber");

	try (MockedStatic<UserMapper> mapperMock = mockStatic(UserMapper.class)) {
	    when(userRepository.findById(userId)).thenReturn(Optional.of(foundUser));
	    mapperMock.when(() -> UserMapper.mapToUserDto(foundUser)).thenReturn(userDto);

	    UserDto result = userService.getUserById(userId);
	    assertNotNull(result);
	    assertEquals(foundUser.getEmail(), result.getEmail());
	}
    }

    @Test
    public void testGetAllUsers() {
	User user1 = new User(2L, "testUser1", "lastname", "username", "email1", "phoneNumber");
	User user2 = new User(3L, "testUser2", "lastname", "username", "email2", "phoneNumber");
	UserDto user1Dto = new UserDto("testUser1", "lastname", "username", "email1", "phoneNumber");
	UserDto user2Dto = new UserDto("testUser2", "lastname", "username", "email2", "phoneNumber");
	List<User> users = List.of(user1, user2);

	when(userRepository.findAll()).thenReturn(users);

	try (MockedStatic<UserMapper> mapperMock = mockStatic(UserMapper.class)) {
	    mapperMock.when(() -> UserMapper.mapToUserDto(user1)).thenReturn(user1Dto);
	    mapperMock.when(() -> UserMapper.mapToUserDto(user2)).thenReturn(user2Dto);

	    List<UserDto> result = userService.getAllUsers();
	    assertNotNull(result);
	    assertEquals(2, result.size());
	    assertEquals(user1.getEmail(), result.get(0).getEmail());
	    assertEquals(user2.getEmail(), result.get(1).getEmail());
	    assertNotEquals(user1.getEmail(), result.get(1).getEmail());
	}
    }

    //TODO overwrite equals
    @Test
    public void testUpdateUser() {
	Long userId = 1L;
	User foundUser = new User(userId, "testUser", "lastname", "username", "email1", "phoneNumber");
	User savedUser = new User(userId, "testUser", "lastname", "username", "email2", "phoneNumber");
	UserDto userDto = new UserDto("changedUser", "lastname", "username", "email2", "phoneNumber");
	UserDto updatedUserDto = new UserDto("changedUser", "lastname", "username2", "email", "phoneNumber");

	try (MockedStatic<UserMapper> mapperMock = mockStatic(UserMapper.class)) {
	    when(userRepository.findById(userId)).thenReturn(Optional.of(foundUser));
	    when(userRepository.save(foundUser)).thenReturn(savedUser);
	    mapperMock.when(() -> UserMapper.mapToUserDto(savedUser)).thenReturn(updatedUserDto);

	    UserDto result = userService.updateUser(userId, userDto);
	    assertNotNull(result);
	    assertEquals(foundUser, savedUser);
	    assertNotEquals(foundUser.getEmail(), result.getEmail());
	}
    }

    @Test
    public void testDeleteUser() {
	Long userId = 1L;
	User user = new User(userId, "testUser", "lastname", "username", "email", "phoneNumber");

	when(userRepository.findById(userId)).thenReturn(Optional.of(user));
	userService.deleteUser(userId);
	
	verify(userRepository).findById(userId);
	verify(userRepository).deleteById(userId);

    }
}
