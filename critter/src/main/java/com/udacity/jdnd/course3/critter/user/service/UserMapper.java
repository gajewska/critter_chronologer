package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.user.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;

@Component
public class UserMapper {

    public Customer customerFromDto(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setNotes(customerDTO.getNotes());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        return customer;
    }

    public CustomerDTO customerToDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .notes(customer.getNotes())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }

    public Employee employeeFromDTO(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        if (employeeDTO.getSkills() != null) {
            employee.setSkills(new ArrayList<>(employeeDTO.getSkills()));
        }
        if (employeeDTO.getDaysAvailable() != null) {
            employee.setDaysAvailable(new ArrayList<>(employeeDTO.getDaysAvailable()));
        }
        return employee;
    }

    public EmployeeDTO employeeToDTO(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .daysAvailable(new HashSet<>(employee.getDaysAvailable()))
                .skills(new HashSet<>(employee.getSkills()))
                .build();
    }

}
