package io.github.jthamayo.backend.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import io.github.jthamayo.backend.dto.AddressDto;
import io.github.jthamayo.backend.dto.JobDto;
import io.github.jthamayo.backend.dto.UserDto;
import io.github.jthamayo.backend.dto.UserProfileDto;
import io.github.jthamayo.backend.dto.VehicleDto;
import io.github.jthamayo.backend.entity.Address;
import io.github.jthamayo.backend.entity.Group;
import io.github.jthamayo.backend.entity.Job;
import io.github.jthamayo.backend.entity.User;
import io.github.jthamayo.backend.entity.Vehicle;
import io.github.jthamayo.backend.entity.enums.AddressType;
import io.github.jthamayo.backend.exception.BadRequestException;
import io.github.jthamayo.backend.exception.InvalidOperationException;
import io.github.jthamayo.backend.exception.ResourceNotFoundException;
import io.github.jthamayo.backend.mapper.AddressMapper;
import io.github.jthamayo.backend.mapper.JobMapper;
import io.github.jthamayo.backend.mapper.UserMapper;
import io.github.jthamayo.backend.mapper.VehicleMapper;
import io.github.jthamayo.backend.repository.AddressRepository;
import io.github.jthamayo.backend.repository.GroupRepository;
import io.github.jthamayo.backend.repository.JobRepository;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.repository.VehicleRepository;
import io.github.jthamayo.backend.service.CloudinaryService;
import io.github.jthamayo.backend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private GroupRepository groupRepository;
    private AddressRepository addressRepository;
    private VehicleRepository vehicleRepository;
    private JobRepository jobRepository;

    private CloudinaryService cloudinaryService;

    public UserServiceImpl(UserRepository userRepository, GroupRepository groupRepository,
	    AddressRepository addressRepository, JobRepository jobRepository, VehicleRepository vehicleRepository,
	    CloudinaryService cloudinaryService) {
	this.userRepository = userRepository;
	this.groupRepository = groupRepository;
	this.addressRepository = addressRepository;
	this.jobRepository = jobRepository;
	this.vehicleRepository = vehicleRepository;
	this.cloudinaryService = cloudinaryService;
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
    @Transactional
    public UserDto updateUser(Long userId, UserDto updatedUser) {
	User user = userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + userId));
	user.setFirstName(updatedUser.getFirstName());
	user.setLastName(updatedUser.getLastName());
	user.setUsername(updatedUser.getUsername());
	user.setEmail(updatedUser.getEmail());
	user.setPhoneNumber(updatedUser.getPhoneNumber());
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
	if (updatedUser.getVehicleId() != null) {
	    Vehicle vehicle = vehicleRepository.findById(updatedUser.getVehicleId())
		    .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
	    user.setVehicle(vehicle);
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

    @Override
    public UserDto getUserByUsername(String username) {
	User user = userRepository.findByUsername(username).orElseThrow(
		() -> new ResourceNotFoundException("User does not exist with given username: " + username));
	return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto addVehicle(Long userId, VehicleDto vehicleDto) {
	User user = userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + userId));
	if (user.getVehicle() != null) {
	    throw new InvalidOperationException("User already has a vehicle");
	}
	Vehicle vehicle = vehicleRepository.save(VehicleMapper.mapToVehicle(vehicleDto));
	user.setVehicle(vehicle);
	return UserMapper.mapToUserDto(userRepository.save(user));
    }

    @Override
    public VehicleDto getVehicle(Long userId) {
	User user = userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + userId));

	return VehicleMapper.mapToVehicleDto(user.getVehicle());

    }

    @Override
    public UserProfileDto getProfile(Long userId) {
	User user = userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + userId));
	UserProfileDto profile = new UserProfileDto(userId, user.getUsername(), user.getEmail(), user.getPhoneNumber(),
		user.getFirstName(), user.getLastName(),
		user.getJobs() != null
			? user.getJobs().stream().map(JobMapper::mapToJobDto).collect(Collectors.toList())
			: new ArrayList<>(),
		user.getHomeAddress() != null ? AddressMapper.mapToAddressDto(user.getHomeAddress()) : null,
		user.getVehicle() != null ? VehicleMapper.mapToVehicleDto(user.getVehicle()) : null, user.isVerified());
	return profile;
    }

    @Override
    public String uploadProfilePicture(Long userId, MultipartFile file) {
	if (file.isEmpty()) {
	    throw new BadRequestException("File is empty");
	}

	String url = cloudinaryService.uploadFile(file, "user_profiles");
	User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	user.setProfilePictureUrl(url);
	userRepository.save(user);
	return user.getProfilePictureUrl();
    }

}
