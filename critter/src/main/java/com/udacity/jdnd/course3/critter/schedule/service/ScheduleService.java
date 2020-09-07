package com.udacity.jdnd.course3.critter.schedule.service;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import com.udacity.jdnd.course3.critter.pet.service.PetNotFoundException;
import com.udacity.jdnd.course3.critter.schedule.entity.Schedule;
import com.udacity.jdnd.course3.critter.schedule.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.user.entity.Employee;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.service.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.user.service.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Schedule save(Schedule schedule) {
        Schedule saveSchedule = scheduleRepository.save(schedule);
        if (saveSchedule.getEmployees() != null) {
            saveSchedule.getEmployees().forEach(
                    employee -> employeeRepository.save(employee)
            );
        } //TODO saveSchedule.addEmployee as in addPet
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedule() {
        return (List<Schedule>) scheduleRepository.findAll();
    }

    public List<Schedule> getSchedulesByPetId(Long petId) {
        Pet pet = findPet(petId);

        return scheduleRepository.findSchedulesByPetsIn(Collections.singleton(pet));
        //TODO try removing findPet and using findSchedulesByPetsIdIn
    }

    public List<Schedule> getSchedulesByEmployeeId(Long employeeId) {
        Employee employee = findEmployee(employeeId);

        return scheduleRepository.findScheduleByEmployeesIn(Collections.singleton(employee));
        //TODO as above
    }

    public List<Schedule> getSchedulesByCustomerId(Long customerId) {
        Customer customer = findCustomer(customerId);

        return scheduleRepository.findSchedulesByPetsOwner(customer);
        //TODO as above
    }

    private Pet findPet(Long id) {
        return petRepository.findById(id).orElseThrow(() -> new PetNotFoundException(id));
    }

    private Employee findEmployee(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    private Customer findCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

}
