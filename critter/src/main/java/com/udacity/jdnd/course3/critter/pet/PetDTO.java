package com.udacity.jdnd.course3.critter.pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Represents the form that pet request and response data takes. Does not map
 * to the database directly.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {
    private Long id;
    private PetType type;
    private String name;
    private Long ownerId;
    private LocalDate birthDate;
    private String notes;
}
