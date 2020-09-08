package com.udacity.jdnd.course3.critter.pet.service;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Pet save(Pet pet) {
        Pet savePet = petRepository.save(pet);
        if (pet.getOwner() != null) {
            saveCustomer(savePet.getOwner(), savePet);
        }
        return savePet;
    }

    private void saveCustomer(Customer customer, Pet pet) {
        customer.addPet(pet);
        customerRepository.save(customer);
    }

    public Pet getById(Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) {
            return optionalPet.get();
        } else {
            throw new PetNotFoundException(id);
        }
    }

    public List<Pet> getAll() {
        return (List<Pet>) petRepository.findAll();
    }

    public List<Pet> getByOwnerId(Long id) {
        return petRepository.findAllByOwnerId(id);
    }
}
