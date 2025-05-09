package io.github.jthamayo.backend.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jthamayo.backend.dto.AddressDto;
import io.github.jthamayo.backend.dto.JobDto;
import io.github.jthamayo.backend.dto.UserDto;
import io.github.jthamayo.backend.entity.Address;
import io.github.jthamayo.backend.entity.Group;
import io.github.jthamayo.backend.entity.Job;
import io.github.jthamayo.backend.entity.User;
import io.github.jthamayo.backend.entity.enums.AddressType;
import io.github.jthamayo.backend.exception.InvalidOperationException;
import io.github.jthamayo.backend.exception.ResourceNotFoundException;
import io.github.jthamayo.backend.mapper.AddressMapper;
import io.github.jthamayo.backend.mapper.JobMapper;
import io.github.jthamayo.backend.mapper.UserMapper;
import io.github.jthamayo.backend.repository.AddressRepository;
import io.github.jthamayo.backend.repository.GroupRepository;
import io.github.jthamayo.backend.repository.JobRepository;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private GroupRepository groupRepository;
    private AddressRepository addressRepository;
    private JobRepository jobRepository;

    public UserServiceImpl(UserRepository userRepository, GroupRepository groupRepository,
	    AddressRepository addressRepository, JobRepository jobRepository) {
	this.userRepository = userRepository;
	this.groupRepository = groupRepository;
	this.addressRepository = addressRepository;
	this.jobRepository = jobRepository;
    }

    // TODO add verified status, last_active, type of user admin, professional,
    // client
    // TODO create login

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
    @Transactional	
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
	    Set<User> members = new HashSet<>(group.getUsers());
	    members.add(user);
	    group.setUsers(members.stream().collect(Collectors.toList()));
	}
	if (updatedUser.getHomeAddressId() != null) {
	    Address address = addressRepository.findById(updatedUser.getHomeAddressId())
		    .orElseThrow(() -> new ResourceNotFoundException("Home Address not found"));
	    user.setHomeAddress(address);
	}
	if (updatedUser.getJobIds() != null && !updatedUser.getJobIds().isEmpty()) {
	    List<Job> jobs = updatedUser.getJobIds().stream()
		    .map((jobId) -> jobRepository.findById(jobId)
			    .orElseThrow(() -> new ResourceNotFoundException("Job not found")))
		    .collect(Collectors.toList());
	    user.setJobs(jobs);
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

    @Override
    @Transactional
    public UserDto addHomeAddress(Long userId, AddressDto homeAddressDto) {
	User user = userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + userId));
	if (user.getHomeAddress() != null) {
	    throw new InvalidOperationException("User already has a home address");
	}
	if (!homeAddressDto.getType().equals(AddressType.HOME)) {
	    throw new InvalidOperationException("Address must be type home");
	}
	Address homeAddress = addressRepository.save(AddressMapper.mapToAddress(homeAddressDto));
	user.setHomeAddress(homeAddress);
	return UserMapper.mapToUserDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserDto addJob(Long userId, JobDto jobDto, AddressDto addressDto) {
	User user = userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + userId));
	Job job = JobMapper.mapToJob(jobDto);
	Address address = AddressMapper.mapToAddress(addressDto);
	address.setType(AddressType.WORK);
	addressRepository.save(address);
	job.setAddress(address);
	job.setUser(user);
	user.getJobs().add(jobRepository.save(job));
	return UserMapper.mapToUserDto(userRepository.save(user));
    }

    @Override
    public List<JobDto> getJobs(Long userId) {
	User user = userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + userId));
	return user.getJobs().stream().map(JobMapper::mapToJobDto).collect(Collectors.toList());
    }

    @Override
    public AddressDto getHomeAddress(Long userId) {
	User user = userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + userId));
	return AddressMapper.mapToAddressDto(user.getHomeAddress());
    }

}
