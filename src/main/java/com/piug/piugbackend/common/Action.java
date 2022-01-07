package com.piug.piugbackend.common;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class Action {
    private Long id;

    private Instant date;

    private String description;

    private ActionType actionType;

    private Long animalId;

    private String issuerEmail;
}
