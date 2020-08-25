package com.udacity.jdnd.course3.critter.pet.service;

public class PetNotFoundException extends RuntimeException {

    public PetNotFoundException(String message){
        super(message);
    }
}