package com.udacity.jdnd.course3.critter.user.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue()
    private Long id;

    @Nationalized
    private String name;
}
