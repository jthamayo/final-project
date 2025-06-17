package io.github.jthamayo.backend.dto;

import java.time.LocalDate;
import java.util.List;

public class ScheduleDto {

    private Long id;
    private Long jobId;
    private LocalDate weekStartDate;
    private List<Long> timeSlotIds;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getJobId() {
	return jobId;
    }

    public void setJobId(Long jobId) {
	this.jobId = jobId;
    }

    public LocalDate getWeekStartDate() {
	return weekStartDate;
    }

    public void setWeekStartDate(LocalDate weekStartDate) {
	this.weekStartDate = weekStartDate;
    }

    public List<Long> getTimeSlots() {
	return timeSlotIds;
    }

    public void setTimeSlots(List<Long> timeSlotIds) {
	this.timeSlotIds = timeSlotIds;
    }

    public ScheduleDto() {
    }

    public ScheduleDto(Long id, Long jobId, LocalDate weekStartDate, List<Long> timeSlotIds) {
	this.id = id;
	this.jobId = jobId;
	this.weekStartDate = weekStartDate;
	this.timeSlotIds = timeSlotIds;
    }
}
