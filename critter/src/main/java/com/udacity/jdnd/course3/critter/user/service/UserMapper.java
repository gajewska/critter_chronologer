package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import com.udacity.jdnd.course3.critter.pet.service.PetNotFoundException;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.user.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    PetRepository petRepository;

    public Customer customerFromDto(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setNotes(customerDTO.getNotes());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setPets(setPetToCustomer(customerDTO));
        return customer;
    }

    private List<Pet> setPetToCustomer(CustomerDTO customerDTO) {
        if (customerDTO.getPetIds() != null) {
            return findPetsByIds(customerDTO.getPetIds(), customerDTO.getId());
        }
        return null;
    }

    private List<Pet> findPetsByIds(List<Long> ids, Long ownerId) {
        List<Pet> pets = ids.stream().map(id -> findPet(id)).collect(Collectors.toList());
        pets.stream().filter(pet -> checkOwner(pet, ownerId)).collect(Collectors.toList());
        return pets;
    }

    private boolean checkOwner(Pet pet, Long ownerId) {
        if (pet.getOwner() == null || pet.getOwner().getId().equals(ownerId)) {
            return true;
        } else {
            return false;
        }
    }

    public Pet findPet(Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);

        if (optionalPet.isPresent()) {
            return optionalPet.get();
        } else {
            throw new PetNotFoundException(String.format("Pet with id %d wasn't found", id));
        }
    }

    public CustomerDTO customerToDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .notes(customer.getNotes())
                .phoneNumber(customer.getPhoneNumber())
                .petIds(getPetsId(customer.getPets()))
                .build();
    }

    private List<Long> getPetsId(List<Pet> pets) {
        return pets != null ?
                pets.stream().map(pet -> pet.getId()).collect(Collectors.toList()) : null;
    }

    public Employee employeeFromDTO(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        if (employeeDTO.getSkills() != null) {
            employee.setSkills(employeeDTO.getSkills());
        }
        if (employeeDTO.getDaysAvailable() != null) {
            employee.setDaysAvailable(employeeDTO.getDaysAvailable());
        }
        return employee;
    }

    public EmployeeDTO employeeToDTO(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .daysAvailable(employee.getDaysAvailable())
                .skills(employee.getSkills())
                .build();
    }

}
