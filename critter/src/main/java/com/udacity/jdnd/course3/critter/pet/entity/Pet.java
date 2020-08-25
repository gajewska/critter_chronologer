package com.udacity.jdnd.course3.critter.pet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pet {

    @GeneratedValue
    @Id
    private Long id;

    private String type;
    private String name;
    private LocalDate birthDate;
    private String notes;

    //TODO -1
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "owner_id", referencedColumnName = "id")
//    private Customer owner;
}
