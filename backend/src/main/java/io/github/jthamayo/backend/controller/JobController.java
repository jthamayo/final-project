package io.github.jthamayo.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jthamayo.backend.dto.JobDto;
import io.github.jthamayo.backend.service.JobService;

@RestController
@RequestMapping("api/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
	this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<JobDto> createJob(@RequestBody JobDto jobDto) {
	JobDto savedJob = jobService.createJob(jobDto);
	return new ResponseEntity<>(savedJob, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable("id") Long jobId) {
	JobDto jobDto = jobService.getJobById(jobId);
	return ResponseEntity.ok(jobDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<JobDto> updateJob(@PathVariable("id") Long jobId, @RequestBody JobDto updatedJob) {
	JobDto jobDto = jobService.updateJob(jobId, updatedJob);
	return ResponseEntity.ok(jobDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteJob(@PathVariable("id") Long jobId) {
	jobService.deleteJob(jobId);
	return ResponseEntity.ok("The job has been successfully deleted");
    }

    @GetMapping
    public ResponseEntity<List<JobDto>> getAllJobs() {
	List<JobDto> jobsDto = jobService.getAllJobs();
	return ResponseEntity.ok(jobsDto);
    }
}
