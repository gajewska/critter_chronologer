package com.udacity.jdnd.course3.critter.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
public class Customer extends Person {

    private String phoneNumber;
    private String notes;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pet", cascade = CascadeType.ALL)
//    private List<Pet> pets;
    /*

     */
}
