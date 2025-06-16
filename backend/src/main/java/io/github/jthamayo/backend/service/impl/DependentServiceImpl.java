package io.github.jthamayo.backend.service.impl;

import org.springframework.stereotype.Service;

import io.github.jthamayo.backend.dto.DependentDto;
import io.github.jthamayo.backend.entity.Dependent;
import io.github.jthamayo.backend.entity.User;
import io.github.jthamayo.backend.exception.ResourceNotFoundException;
import io.github.jthamayo.backend.mapper.DependentMapper;
import io.github.jthamayo.backend.repository.DependentRepository;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.service.DependentService;

@Service
public class DependentServiceImpl implements DependentService {

    private UserRepository userRepository;
    private DependentRepository dependentRepository;

    public DependentServiceImpl(UserRepository userRepository, DependentRepository dependentRepository) {
	this.userRepository = userRepository;
	this.dependentRepository = dependentRepository;
    }

    @Override
    public DependentDto createDependent(DependentDto dependentDto) {
	Dependent dependent = DependentMapper.mapToDependent(dependentDto);
	User user = userRepository.findById(dependentDto.getGuardianId())
		.orElseThrow(() -> new ResourceNotFoundException(
			"User does not exist with given id: " + dependentDto.getGuardianId()));
	dependent.setGuardian(user);
	// userAdd Child
	return DependentMapper.mapToDependentDto(dependentRepository.save(dependent));
    }

    @Override
    public DependentDto updateDependent(Long dependentId, DependentDto dependentDto) {
	Dependent dependent = dependentRepository.findById(dependentId).orElseThrow(
		() -> new ResourceNotFoundException("Dependent does not exist with given id: " + dependentId));
	dependent.setFirstName(dependentDto.getFirstName());
	dependent.setLastName(dependentDto.getLastName());
	dependent.setAllergies(dependentDto.getAllergies());
	dependent.setAllergic(dependentDto.isAllergic());
	dependent.setSpecialNeeds(dependentDto.isSpecialNeeds());
	return DependentMapper.mapToDependentDto(dependentRepository.save(dependent));
    }

    @Override
    public DependentDto getDependentById(Long dependentId) {
	Dependent dependent = dependentRepository.findById(dependentId).orElseThrow(
		() -> new ResourceNotFoundException("Dependent does not exist with given id: " + dependentId));
	return DependentMapper.mapToDependentDto(dependent);
    }

    @Override
    public void deleteDependent(Long dependentId) {
	dependentRepository.findById(dependentId).orElseThrow(
		() -> new ResourceNotFoundException("Dependent does not exist with given id: " + dependentId));
	dependentRepository.deleteById(dependentId);
    }

}
