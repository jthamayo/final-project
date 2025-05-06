package io.github.jthamayo.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.jthamayo.backend.dto.JobDto;
import io.github.jthamayo.backend.entity.Address;
import io.github.jthamayo.backend.entity.Job;
import io.github.jthamayo.backend.entity.User;
import io.github.jthamayo.backend.exception.ResourceNotFoundException;
import io.github.jthamayo.backend.mapper.JobMapper;
import io.github.jthamayo.backend.repository.AddressRepository;
import io.github.jthamayo.backend.repository.JobRepository;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.service.JobService;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;
    private AddressRepository addressRepository;
    private UserRepository userRepository;

    public JobServiceImpl(JobRepository jobRepository, AddressRepository addressRepository,
	    UserRepository userRepository) {
	this.jobRepository = jobRepository;
	this.addressRepository = addressRepository;
	this.userRepository = userRepository;
    }

    @Override
    public JobDto createJob(JobDto jobDto) {
	Job job = JobMapper.mapToJob(jobDto);
	User user = userRepository.findById(jobDto.getUserId())
		.orElseThrow(() -> new ResourceNotFoundException("User not found"));
	job.setUser(user);
	if (jobDto.getAddressId() != null) {
	    Address address = addressRepository.findById(jobDto.getAddressId())
		    .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
	    job.setAddress(address);
	}
	// TODO user.getJobs().add(job);
	return JobMapper.mapToJobDto(jobRepository.save(job));
    }

    @Override
    public JobDto getJobById(Long jobId) {
	Job job = jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("Job not found"));
	return JobMapper.mapToJobDto(job);
    }

    @Override
    public JobDto updateJob(Long jobId, JobDto jobDto) {
	Job job = jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("Job not found"));
	job.setIrregular(jobDto.isIrregular());
	job.setNocturnal(jobDto.isNocturnal());
	User user = userRepository.findById(jobDto.getUserId())
		.orElseThrow(() -> new ResourceNotFoundException("User not found"));
	job.setUser(user);
	if (jobDto.getAddressId() != null) {
	    Address address = addressRepository.findById(jobDto.getAddressId())
		    .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
	    job.setAddress(address);
	}
	// TODO user.getJobs().add(job);
	return JobMapper.mapToJobDto(jobRepository.save(job));
    }

    @Override
    public void deleteJob(Long jobId) {
	jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("Job not found"));
	jobRepository.deleteById(jobId);
    }

    @Override
    public List<JobDto> getAllJobs() {
	List<Job> jobs = jobRepository.findAll();
	return jobs.stream().map(JobMapper::mapToJobDto).collect(Collectors.toList());
    }

}
