package com.udacity.jdnd.course3.critter.pet.service;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet getById(Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) {
            return optionalPet.get();
        } else {
            throw new PetNotFoundException(String.format("Pet with id %d wasn't found", id));
        }
    }

    public List<Pet> getAll() {
        return (List<Pet>) petRepository.findAll();
    }

    //TODO
//    public List<Pet> getByOwnerId(Long id) {
//        return petRepository.findAllByOwnerId(id);
//    }
}
