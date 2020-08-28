package com.udacity.jdnd.course3.critter.user.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
public class Employee extends Person {

    @ElementCollection
    private Collection<EmployeeSkill> skills = new ArrayList<EmployeeSkill>();

    @ElementCollection
    private Collection<DayOfWeek> daysAvailable = new ArrayList<DayOfWeek>();
}
