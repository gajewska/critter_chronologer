package com.udacity.jdnd.course3.critter.user.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String name;

    @ElementCollection
    private Set<EmployeeSkill> skills = new HashSet<>();

    @ElementCollection
    private Set<DayOfWeek> daysAvailable = new HashSet<>();
}
