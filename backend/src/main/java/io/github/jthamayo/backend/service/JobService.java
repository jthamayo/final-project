package io.github.jthamayo.backend.service;

import java.util.List;

import io.github.jthamayo.backend.dto.JobDto;

public interface JobService {

    JobDto createJob(JobDto jobDto);

    JobDto getJobById(Long jobId);

    JobDto updateJob(Long jobId, JobDto jobDto);

    void deleteJob(Long jobId);

    List<JobDto> getAllJobs();

}
