package com.udacity.jdnd.course3.critter.schedule.service;

import com.udacity.jdnd.course3.critter.schedule.entity.Schedule;
import com.udacity.jdnd.course3.critter.schedule.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.user.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public Schedule save(Schedule schedule) {
        Schedule saveSchedule = scheduleRepository.save(schedule);
        if (saveSchedule.getEmployees() != null) {
            saveSchedule.getEmployees().forEach(
                    employee -> employeeRepository.save(employee)
            );
        }
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedule() {
        return (List<Schedule>) scheduleRepository.findAll();
    }
}
