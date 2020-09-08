package com.udacity.jdnd.course3.critter.pet.service;

public class PetNotFoundException extends RuntimeException {

    public PetNotFoundException(Long id){
        super(String.format("Pet with id %d wasn't found", id));
    }
}