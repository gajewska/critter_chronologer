package com.udacity.jdnd.course3.critter.user.repository;

import com.udacity.jdnd.course3.critter.user.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findCustomerByPetsIdIn(Collection<Long> petsIds);
}
