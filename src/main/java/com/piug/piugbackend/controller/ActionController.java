package com.piug.piugbackend.controller;

import com.piug.piugbackend.common.Action;
import com.piug.piugbackend.common.ActionType;
import com.piug.piugbackend.common.AnimalStatus;
import com.piug.piugbackend.entity.ActionEntity;
import com.piug.piugbackend.entity.AnimalEntity;
import com.piug.piugbackend.repository.ActionRepository;
import com.piug.piugbackend.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/actions")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ActionController {

    private final ActionRepository actionRepository;

    private final AnimalRepository animalRepository;

    @GetMapping("/actionEntities")
    public ResponseEntity<List<Action>> getAll() {
        List<ActionEntity> all = actionRepository.findAll();
        List<Action> collect = all.stream()
                .map(actionEntity -> Action.builder()
                        .id(actionEntity.getId())
                        .description(actionEntity.getDescription())
                        .actionType(actionEntity.getActionType())
                        .animalId(actionEntity.getAnimalEntity().getId())
                        .date(actionEntity.getDate())
                        .issuerEmail(actionEntity.getIssuerEmail())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }


    @PostMapping("/new")
    public ResponseEntity<Action> createAction(@RequestBody Action action) {
        AnimalEntity animal = animalRepository.getById(action.getAnimalId());


        ActionEntity actionEntity = new ActionEntity();
        actionEntity.setActionType(action.getActionType());
        actionEntity.setDescription(action.getDescription());
        actionEntity.setIssuerEmail(action.getIssuerEmail());
        actionEntity.setAnimalEntity(animal);
        ActionEntity save = actionRepository.save(actionEntity);

        Action toReturn = Action.builder()
                .description(save.getDescription())
                .actionType(save.getActionType())
                .animalId(save.getAnimalEntity().getId())
                .id(save.getId())
                .issuerEmail(save.getIssuerEmail())
                .date(save.getDate())
                .build();

        if (action.getActionType().equals(ActionType.CHANGED_TO_ADOPTED)) {
            animal.setStatus(AnimalStatus.ADOPTED);
            animalRepository.save(animal);
        } else if (action.getActionType().equals(ActionType.CHANGED_TO_IN_CUSTODY)) {
            animal.setStatus(AnimalStatus.IN_CUSTODY);
            animalRepository.save(animal);
        }

        return ResponseEntity.ok(toReturn);
    }
}
