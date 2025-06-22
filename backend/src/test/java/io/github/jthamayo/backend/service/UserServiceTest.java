package io.github.jthamayo.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.jthamayo.backend.dto.AddressDto;
import io.github.jthamayo.backend.dto.GroupDto;
import io.github.jthamayo.backend.dto.UserDto;
import io.github.jthamayo.backend.dto.VehicleDto;
import io.github.jthamayo.backend.entity.Address;
import io.github.jthamayo.backend.entity.Dependent;
import io.github.jthamayo.backend.entity.Group;
import io.github.jthamayo.backend.entity.Job;
import io.github.jthamayo.backend.entity.User;
import io.github.jthamayo.backend.entity.Vehicle;
import io.github.jthamayo.backend.entity.enums.AddressType;
import io.github.jthamayo.backend.mapper.UserMapper;
import io.github.jthamayo.backend.repository.AddressRepository;
import io.github.jthamayo.backend.repository.DependentRepository;
import io.github.jthamayo.backend.repository.GroupRepository;
import io.github.jthamayo.backend.repository.JobRepository;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.repository.VehicleRepository;
import io.github.jthamayo.backend.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private GroupRepository groupRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private VehicleRepository vehicleRepository;
    @Mock
    private JobRepository jobRepository;
    @Mock
    private DependentRepository dependentRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testCreateUser() {
	UserDto userDto = new UserDto(1L, "testUser", "lastname", "username", "email", "phoneNumber", 1L,
		new ArrayList<>(List.of(1L, 2L)), 1L, 1L, new ArrayList<>(List.of(1L, 2L)), "url", false,
		List.of(1L, 2L));
	User user = new User(1L, "testUser", "lastname", "username", "email", "phoneNumber", false);
	User savedUser = new User(1L, "testUser", "lastname", "username", "email", "phoneNumber", false);
	UserDto resultUser = new UserDto(1L, "testUser", "lastname", "username", "email", "phoneNumber", 1L,
		new ArrayList<>(List.of(1L, 2L)), 1L, 1L, new ArrayList<>(List.of(1L, 2L)), "url", false,
		List.of(1L, 2L));

	Group group = new Group(new ArrayList<>(List.of(new User(), new User())));

	try (MockedStatic<UserMapper> mapperMock = mockStatic(UserMapper.class)) {
	    mapperMock.when(() -> UserMapper.mapToUser(userDto)).thenReturn(user);
	    when(userRepository.save(user)).thenReturn(savedUser);
	    when(groupRepository.findById(userDto.getGroupId())).thenReturn(Optional.of(group));
	    mapperMock.when(() -> UserMapper.mapToUserDto(savedUser)).thenReturn(resultUser);

	    UserDto testResult = userService.createUser(userDto);
	    assertNotNull(testResult);
	    assertEquals(user, savedUser);
	}
    }

    @Test
    public void testGetUserById() {
	Long userId = 1L;
	User foundUser = new User(userId, "testUser", "lastname", "username", "email", "phoneNumber", false);
	UserDto userDto = new UserDto(1L, "testUser", "lastname", "username", "email", "phoneNumber", 1L,
		new ArrayList<>(List.of(1L, 2L)), 1L, 1L, new ArrayList<>(List.of(1L, 2L)), "url", false,
		List.of(1L, 2L));

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
	User user1 = new User(2L, "testUser1", "lastname", "username", "email1", "phoneNumber", false);
	User user2 = new User(3L, "testUser2", "lastname", "username", "email2", "phoneNumber", false);
	UserDto user1Dto = new UserDto(2L, "testUser1", "lastname", "username", "email1", "phoneNumber", 1L,
		new ArrayList<>(List.of(1L, 2L)), 1L, 1L, new ArrayList<>(List.of(1L, 2L)), "url", false,
		List.of(1L, 2L));
	UserDto user2Dto = new UserDto(3L, "testUser2", "lastname", "username", "email2", "phoneNumber", 1L,
		new ArrayList<>(List.of(1L, 2L)), 1L, 1L, new ArrayList<>(List.of(1L, 2L)), "url", false,
		List.of(1L, 2L));

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

    // TODO overwrite equals
    @Test
    public void testUpdateUser() {
	Long userId = 1L;
	User foundUser = new User(userId, "testUser", "lastname", "username", "email1", "phoneNumber", false);
	User savedUser = new User(userId, "testUser", "lastname", "username", "email2", "phoneNumber", false);
	UserDto userDto = new UserDto(1L, "testUser", "lastname", "username", "email1", "phoneNumber", 1L,
		new ArrayList<>(List.of(1L, 2L)), 1L, 1L, new ArrayList<>(List.of(1L, 2L)), "url", false,
		List.of(1L, 2L));

	UserDto updatedUserDto = new UserDto(1L, "testUser", "lastname", "username", "email2", "phoneNumber", 1L,
		new ArrayList<>(List.of(1L, 2L)), 1L, 1L, new ArrayList<>(List.of(1L, 2L)), "url", false,
		List.of(1L, 2L));
	Group group = new Group(new ArrayList<>(List.of(new User(), new User())));
	Address address = new Address(1L, "city", "street", "zip", "country", 6, AddressType.HOME);
	Vehicle vehicle = new Vehicle();
	Job job1 = new Job();
	Job job2 = new Job();
	Dependent dependent1 = new Dependent();
	Dependent dependent2 = new Dependent();

	try (MockedStatic<UserMapper> mapperMock = mockStatic(UserMapper.class)) {
	    when(userRepository.findById(userId)).thenReturn(Optional.of(foundUser));
	    when(userRepository.save(foundUser)).thenReturn(savedUser);
	    when(groupRepository.findById(userDto.getGroupId())).thenReturn(Optional.of(group));
	    when(addressRepository.findById(userDto.getHomeAddressId())).thenReturn(Optional.of(address));
	    when(vehicleRepository.findById(userDto.getVehicleId())).thenReturn(Optional.of(vehicle));
	    when(jobRepository.findById(1L)).thenReturn(Optional.of(job1));
	    when(jobRepository.findById(2L)).thenReturn(Optional.of(job2));
	    when(dependentRepository.findById(1L)).thenReturn(Optional.of(dependent1));
	    when(dependentRepository.findById(2L)).thenReturn(Optional.of(dependent2));
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
	User user = new User(userId, "testUser", "lastname", "username", "email", "phoneNumber", false);

	when(userRepository.findById(userId)).thenReturn(Optional.of(user));
	userService.deleteUser(userId);

	verify(userRepository).findById(userId);
	verify(userRepository).deleteById(userId);

    }
}
