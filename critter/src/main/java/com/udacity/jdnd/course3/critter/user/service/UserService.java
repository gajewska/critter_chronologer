package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.user.entity.Employee;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PetRepository petRepository;

    public Customer saveCustomer(Customer customer) {
        Customer saveCustomer = customerRepository.save(customer);
        if (customer.getPets() != null) {
            saveCustomer.getPets().forEach(pet -> savePet(pet, saveCustomer));
        }
        return saveCustomer;
    }

    private void savePet(Pet pet, Customer customer) {
        pet.addOwner(customer);
        petRepository.save(pet);
    }

    public List<Customer> getAllCustomer() {
        return (List<Customer>) customerRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

    public List<Employee> findEmployeesBySkills(Set<EmployeeSkill> employeeSkills, LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        List<Employee> distinctBySkillsIn = employeeRepository.findDistinctBySkillsIn(employeeSkills);
        return distinctBySkillsIn.stream()
                .filter(employee -> employee.getSkills().containsAll(employeeSkills))
                .filter(employee -> employee.getDaysAvailable().contains(dayOfWeek))
                .collect(Collectors.toList());
    }

    public Customer getCustomerByPetId(Long petId) {
        return customerRepository.findCustomerByPetsIdIn(Collections.singleton(petId));
    }
}
