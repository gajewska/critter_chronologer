package com.udacity.jdnd.course3.critter.user.service;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long id){
        super(String.format("Customer with id %d wasn't found", id));
    }
}
