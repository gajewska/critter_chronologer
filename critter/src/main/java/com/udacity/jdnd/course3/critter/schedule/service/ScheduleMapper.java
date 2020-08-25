package com.udacity.jdnd.course3.critter.schedule.service;

import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.schedule.entity.Schedule;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ScheduleMapper {

    public Schedule fromDTO(ScheduleDTO scheduleDTO) {
        return Schedule.builder()
                .id(scheduleDTO.getId())
                .date(scheduleDTO.getDate())
                .activities(scheduleDTO.getActivities().stream().collect(Collectors.toList()))
                .build();
    }

    //TODO employId petId
    public ScheduleDTO toDTO(Schedule schedule) {
        return ScheduleDTO.builder()
                .id(schedule.getId())
                .date(schedule.getDate())
                .activities(schedule.getActivities().stream().collect(Collectors.toSet()))
                .build();
    }

}
