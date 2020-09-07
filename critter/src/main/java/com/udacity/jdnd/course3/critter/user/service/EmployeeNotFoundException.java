package com.udacity.jdnd.course3.critter.user.service;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long id) {
        super(String.format("Employee with id %d wasn't found", id));
    }
}
