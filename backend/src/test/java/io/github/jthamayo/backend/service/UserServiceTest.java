package io.github.jthamayo.backend.service;

import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;


public class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    public void testCreateUser(UserDto userDto) {
	
    };

    public void testGetUserById(Long userId) {
	
    };

    public void testGetAllUsers() {
	
    };

    public void testUpdateUser(Long userId, UserDto updatedUser) {
	
    };

    public void testDeleteUser(Long userId) {
	
    };
}
