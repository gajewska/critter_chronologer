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
import java.util.Optional;

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
        }
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedule() {
        return (List<Schedule>) scheduleRepository.findAll();
    }

    public List<Schedule> getSchedulesByPetId(Long petId) {
        Pet pet = findPet(petId);

        return scheduleRepository.findSchedulesByPetsIn(Collections.singleton(pet));
    }

    public List<Schedule> getSchedulesByEmployeeId(Long employeeId) {
        Employee employee = findEmployee(employeeId);

        return scheduleRepository.findScheduleByEmployeesIn(Collections.singleton(employee));
    }

    public List<Schedule> getSchedulesByCustomerId(Long customerId) {
        Customer customer = findCustomer(customerId);

        return scheduleRepository.findSchedulesByPetsOwner(customer);
    }

    private Pet findPet(Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);

        if (optionalPet.isPresent()) {
            return optionalPet.get();
        }

        throw new PetNotFoundException(String.format("Pet with id %d wasn't found", id));
    }

    private Employee findEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        }

        throw new EmployeeNotFoundException(String.format("Employee with id %d wasn't found", id));
    }

    private Customer findCustomer(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        }

        throw new CustomerNotFoundException(String.format("Customer with id %d wasn't found", id));
    }

}
