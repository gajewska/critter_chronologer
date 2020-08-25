package com.udacity.jdnd.course3.critter.pet.service;

import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.PetType;
import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {

    //TODO map ownerId to Owner
    public Pet fromDTO(PetDTO petDTO) {
        return Pet.builder()
                .id(petDTO.getId())
                .name(petDTO.getName())
                .birthDate(petDTO.getBirthDate())
                .notes(petDTO.getNotes())
                .type(petDTO.getType().toString())
                .build();
    }

    public PetDTO toDTO(Pet pet) {
        return PetDTO.builder()
                .id(pet.getId())
                .name(pet.getName())
                .birthDate(pet.getBirthDate())
                .notes(pet.getNotes())
                .type(PetType.valueOf(pet.getType()))
                //TODO
               // .ownerId(pet.getOwner().getId())
                .build();
    }

}
