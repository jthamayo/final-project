package io.github.jthamayo.backend.mapper;

import java.util.stream.Collectors;

import io.github.jthamayo.backend.dto.ScheduleDto;
import io.github.jthamayo.backend.entity.Schedule;

public class ScheduleMapper {

    public static ScheduleDto mapToScheduleDto(Schedule schedule) {
	return new ScheduleDto(schedule.getId(), schedule.getJob().getId(), schedule.getWeekStartDate(),
		schedule.getTimeSlots() != null
			? schedule.getTimeSlots().stream().map((timeSlot) -> timeSlot.getId())
				.collect(Collectors.toList())
			: null);
    }

    public static Schedule maptToSchedule(ScheduleDto scheduleDto) {
	return new Schedule(scheduleDto.getWeekStartDate());
    }
}
