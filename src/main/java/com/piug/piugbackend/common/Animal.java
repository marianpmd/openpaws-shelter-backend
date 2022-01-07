package com.piug.piugbackend.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Animal {
    private Long id;
    private String name;
    private AnimalType animalType;
    private int age;
    private String description;
    private AnimalStatus status;
}
