package io.github.jthamayo.backend.dto;

import java.time.LocalDate;

public class TimeSlotDto {
    private Long id;
    private LocalDate date;
    private LocalDate start;
    private LocalDate end;
    private Long scheduleId;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public LocalDate getDate() {
	return date;
    }

    public void setDate(LocalDate date) {
	this.date = date;
    }

    public LocalDate getStart() {
	return start;
    }

    public void setStart(LocalDate start) {
	this.start = start;
    }

    public LocalDate getEnd() {
	return end;
    }

    public void setEnd(LocalDate end) {
	this.end = end;
    }

    public Long getScheduleId() {
	return scheduleId;
    }

    public void setSchedule(Long scheduleId) {
	this.scheduleId = scheduleId;
    }

    public TimeSlotDto(Long id, LocalDate date, LocalDate start, LocalDate end, Long scheduleId) {
	this.id = id;
	this.date = date;
	this.start = start;
	this.end = end;
	this.scheduleId = scheduleId;
    }

    public TimeSlotDto() {
    }
}
