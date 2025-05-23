package io.github.jthamayo.backend.service;

import java.util.List;

import io.github.jthamayo.backend.dto.AddressDto;
import io.github.jthamayo.backend.dto.JobDto;
import io.github.jthamayo.backend.dto.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long userId, UserDto updatedUser);

    void deleteUser(Long userId);
    
    UserDto addHomeAddress(Long userId, AddressDto homeAddressDto);
    
    UserDto addJob(Long userId, JobDto jobDto, AddressDto addressDto);
    
    List<JobDto> getJobs(Long userId);
    
    AddressDto getHomeAddress(Long userId);
    
    
    
}
