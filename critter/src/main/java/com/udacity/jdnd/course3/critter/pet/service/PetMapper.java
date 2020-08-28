package com.udacity.jdnd.course3.critter.pet.service;

import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.PetType;
import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.service.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PetMapper {

    @Autowired
    CustomerRepository customerRepository;

    public Pet fromDTO(PetDTO petDTO) {
        return Pet.builder()
                .id(petDTO.getId())
                .name(petDTO.getName())
                .birthDate(petDTO.getBirthDate())
                .notes(petDTO.getNotes())
                .type(petDTO.getType().toString())
                .owner(findCustomerById(petDTO.getOwnerId()))
                .build();
    }

    private Customer findCustomerById(Long id) {
        if (id != null) {
            Optional<Customer> optionalCustomer = customerRepository.findById(id);

            if (optionalCustomer.isPresent()) {
                return optionalCustomer.get();
            } else {
                throw new CustomerNotFoundException(String.format("Pet with id %d wasn't found", id));
            }
        } else {
            return null;
        }
    }

    public PetDTO toDTO(Pet pet) {
        return PetDTO.builder()
                .id(pet.getId())
                .name(pet.getName())
                .birthDate(pet.getBirthDate())
                .notes(pet.getNotes())
                .type(PetType.valueOf(pet.getType()))
                .ownerId(pet.getOwner() != null ? pet.getOwner().getId() : null)
                .build();
    }

}
