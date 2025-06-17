package io.github.jthamayo.backend.mapper;

import io.github.jthamayo.backend.dto.TimeSlotDto;
import io.github.jthamayo.backend.entity.TimeSlot;

public class TimeSlotMapper {

    public static TimeSlotDto mapToTimeSlotDto(TimeSlot timeSlot) {
	return new TimeSlotDto(timeSlot.getId(), timeSlot.getDate(), timeSlot.getStart(), timeSlot.getEnd(),
		timeSlot.getSchedule().getId());
    }

    public static TimeSlot mapToTimeSlot(TimeSlotDto timeSlot) {
	return new TimeSlot(timeSlot.getDate(), timeSlot.getStart(), timeSlot.getEnd());	
    }
}
