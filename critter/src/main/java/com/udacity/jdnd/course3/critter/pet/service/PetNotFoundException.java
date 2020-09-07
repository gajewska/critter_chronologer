package com.udacity.jdnd.course3.critter.pet.service;

import java.util.function.Supplier;

public class PetNotFoundException extends RuntimeException {

    public PetNotFoundException(Long id){
        super(String.format("Pet with id %d wasn't found", id));
    }
}