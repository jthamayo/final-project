package io.github.jthamayo.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.jthamayo.backend.dto.JobDto;
import io.github.jthamayo.backend.entity.Address;
import io.github.jthamayo.backend.entity.Job;
import io.github.jthamayo.backend.entity.User;
import io.github.jthamayo.backend.entity.enums.AddressType;
import io.github.jthamayo.backend.mapper.JobMapper;
import io.github.jthamayo.backend.repository.AddressRepository;
import io.github.jthamayo.backend.repository.JobRepository;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.service.impl.JobServiceImpl;

@ExtendWith(MockitoExtension.class)
public class JobServiceTest {

    private User user;
    private Address address;

    @Mock
    private JobRepository jobRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private JobServiceImpl jobService;

    @BeforeEach
    void setUp() {
	user = new User(1L, "testUser", "lastname", "username", "email", "phoneNumber");
	address = new Address(1L, "city", "street", "zip", "country", 6, AddressType.HOME);
    }

    @Test
    void testCreateJob() {
	Job newJob = new Job(1L, address, user, false, false);
	Job savedJob = new Job(1L, address, user, false, false);
	JobDto newJobDto = new JobDto(1L, address.getId(), user.getId(), false, false);
	JobDto savedJobDto = new JobDto(1L, address.getId(), user.getId(), false, false);

	when(userRepository.findById(1L)).thenReturn(Optional.of(user));
	when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
	when(jobRepository.save(newJob)).thenReturn(savedJob);
	try (MockedStatic<JobMapper> mapperMock = mockStatic(JobMapper.class)) {
	    mapperMock.when(() -> JobMapper.mapToJob(newJobDto)).thenReturn(newJob);
	    mapperMock.when(() -> JobMapper.mapToJobDto(savedJob)).thenReturn(savedJobDto);

	    JobDto testResult = jobService.createJob(newJobDto);
	    assertNotNull(testResult);
	    assertEquals(user.getId(), testResult.getUserId());
	}

    }

    @Test
    void testGetJobById() {
	Long jobId = 1L;
	Job job = new Job(1L, address, user, false, false);
	JobDto jobDto = new JobDto(1L, address.getId(), user.getId(), false, false);

	when(jobRepository.findById(jobId)).thenReturn(Optional.of(job));
	try (MockedStatic<JobMapper> mapperMock = mockStatic(JobMapper.class)) {
	    mapperMock.when(() -> JobMapper.mapToJobDto(job)).thenReturn(jobDto);
	    JobDto result = jobService.getJobById(jobId);
	    assertNotNull(result);
	}

    }

    @Test
    void testDeleteJob() {
	Long jobId = 1L;
	Job job = new Job(1L, address, user, false, false);
	when(jobRepository.findById(jobId)).thenReturn(Optional.of(job));

	jobService.deleteJob(jobId);
	verify(jobRepository).deleteById(jobId);
    }

    @Test
    void updateJob() {
	Long jobId = 1L;
	Job job = new Job(1L, address, user, false, false);
	Job savedJob = new Job(1L, address, user, false, false);
	JobDto savedJobDto = new JobDto(1L, address.getId(), user.getId(), false, false);
	JobDto updatedJobDto = new JobDto(1L, address.getId(), user.getId(), false, false);

	when(userRepository.findById(jobId)).thenReturn(Optional.of(user));
	when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
	when(jobRepository.findById(jobId)).thenReturn(Optional.of(job));
	try (MockedStatic<JobMapper> mapperMock = mockStatic(JobMapper.class)) {
	    when(jobRepository.save(job)).thenReturn(savedJob);
	    mapperMock.when(() -> JobMapper.mapToJobDto(savedJob)).thenReturn(savedJobDto);
	    JobDto result = jobService.updateJob(jobId, updatedJobDto);
	    assertNotNull(result);
	}
    }

    @Test
    void testGetAllJobs() {
	when(jobRepository.findAll()).thenReturn(
		Stream.of(new Job(1L, address, user, false, false), new Job(1L, address, user, false, false))
			.collect(Collectors.toList()));

	try (MockedStatic<JobMapper> mockMapper = mockStatic(JobMapper.class)) {
	    mockMapper.when(() -> JobMapper.mapToJobDto(any(Job.class))).thenAnswer(invocation -> {
		Job job = invocation.getArgument(0);
		return new JobDto(job.getUser().getId(), job.getAddress().getId(), job.isIrregular(),
			job.isNocturnal());
	    });
	    List<JobDto> result = jobService.getAllJobs();
	    assertNotNull(result);
	    assertEquals(2, result.size());
	}

    }
}
