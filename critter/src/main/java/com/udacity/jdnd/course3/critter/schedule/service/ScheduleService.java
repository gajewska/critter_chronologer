package com.udacity.jdnd.course3.critter.schedule.service;

import com.udacity.jdnd.course3.critter.schedule.entity.Schedule;
import com.udacity.jdnd.course3.critter.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedule() {
        return (List<Schedule>) scheduleRepository.findAll();
    }

    public List<Schedule> getSchedulesByPetId(Long petId) {
        return scheduleRepository.findSchedulesByPetsIdIn(Collections.singleton(petId));
    }

    public List<Schedule> getSchedulesByEmployeeId(Long employeeId) {
        return scheduleRepository.findScheduleByEmployeesIdIn(Collections.singleton(employeeId));
    }

    public List<Schedule> getSchedulesByCustomerId(Long customerId) {
        return scheduleRepository.findSchedulesByPetsOwnerId(customerId);
    }

}
