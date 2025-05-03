package io.github.jthamayo.backend.mapper;

import io.github.jthamayo.backend.dto.JobDto;
import io.github.jthamayo.backend.entity.Job;

public class JobMapper {

    public static JobDto mapToJobDto(Job job) {
	return new JobDto(job.getId(), job.getAddress().getId(), job.getUser().getId(), job.isIrregular(),
		job.isNocturnal());
    }

    public static Job mapToJob(JobDto jobDto) {
	return new Job(jobDto.getId(), jobDto.isIrregular(), jobDto.isNocturnal());
    }
}
