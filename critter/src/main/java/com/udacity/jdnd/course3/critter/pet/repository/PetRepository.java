package com.udacity.jdnd.course3.critter.pet.repository;

import java.util.List;
import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {
    //TODO
   // List<Pet> findAllByOwnerId(long id);
}
