package com.udacity.jdnd.course3.critter.schedule.service;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import com.udacity.jdnd.course3.critter.pet.service.PetNotFoundException;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.schedule.entity.Schedule;
import com.udacity.jdnd.course3.critter.user.entity.Employee;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.service.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ScheduleMapper {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    public Schedule fromDTO(ScheduleDTO scheduleDTO) {
        return Schedule.builder()
                .id(scheduleDTO.getId())
                .date(scheduleDTO.getDate())
                .activities(scheduleDTO.getActivities())
                .employees(setEmployeesToSchedule(scheduleDTO))
                .pets(setPetsToSchedule(scheduleDTO))
                .build();
    }

    private List<Employee> setEmployeesToSchedule(ScheduleDTO scheduleDTO) {
        if (scheduleDTO.getEmployeeIds() != null) {
            return scheduleDTO.getEmployeeIds().stream().map(id -> findEmployee(id)).collect(Collectors.toList());
        }

        return null;
    }

    private List<Pet> setPetsToSchedule(ScheduleDTO scheduleDTO) {
        if (scheduleDTO.getPetIds() != null) {
            return scheduleDTO.getPetIds().stream().map(id -> findPet(id)).collect(Collectors.toList());
        }

        return null;
    }

    public Employee findEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

    public Pet findPet(Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);

        if (optionalPet.isPresent()) {
            return optionalPet.get();
        } else {
            throw new PetNotFoundException(id);
        }
    }

    public ScheduleDTO toDTO(Schedule schedule) {
        return ScheduleDTO.builder()
                .id(schedule.getId())
                .date(schedule.getDate())
                .activities(schedule.getActivities())
                .employeeIds(getEmployeesId(schedule.getEmployees()))
                .petIds(getPetsId(schedule.getPets()))
                .build();
    }

    private List<Long> getEmployeesId(List<Employee> employees) {
        return employees != null ?
                employees.stream().map(employee -> employee.getId()).collect(Collectors.toList()) : null;
    }

    private List<Long> getPetsId(List<Pet> pets) {
        return pets != null ?
                pets.stream().map(employee -> employee.getId()).collect(Collectors.toList()) : null;
    }
}
