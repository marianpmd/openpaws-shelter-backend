package com.piug.piugbackend.controller;

import com.piug.piugbackend.common.Animal;
import com.piug.piugbackend.entity.AnimalEntity;
import com.piug.piugbackend.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animalEntities")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AnimalController {

    private final AnimalRepository animalRepository;

    @GetMapping("/id{id}")
    public ResponseEntity<Animal> getById(@RequestParam Long id){
        System.out.println(id);
        AnimalEntity byId = animalRepository.getById(id);
        Animal animal = Animal.builder()
                .id(byId.getId())
                .animalType(byId.getAnimalType())
                .age(byId.getAge())
                .name(byId.getName())
                .description(byId.getDescription())
                .status(byId.getStatus())
                .build();
        return ResponseEntity.ok(animal);
    }
}
