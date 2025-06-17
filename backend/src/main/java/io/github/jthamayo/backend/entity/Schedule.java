package io.github.jthamayo.backend.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "jobs_schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @Column(name = "week_start_date", nullable = false)
    private LocalDate weekStartDate;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimeSlot> timeSlots;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Job getJob() {
	return job;
    }

    public void setJob(Job job) {
	this.job = job;
    }

    public LocalDate getWeekStartDate() {
	return weekStartDate;
    }

    public void setWeekStartDate(LocalDate weekStartDate) {
	this.weekStartDate = weekStartDate;
    }

    public List<TimeSlot> getTimeSlots() {
	return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
	this.timeSlots = timeSlots;
    }

    public Schedule() {
    }

    public Schedule(Job job, LocalDate weekStartDate, List<TimeSlot> timeSlots) {
	this.job = job;
	this.weekStartDate = weekStartDate;
	this.timeSlots = timeSlots;
    }

    public Schedule(LocalDate weekStartDate) {
	this.weekStartDate = weekStartDate;
    }

}
