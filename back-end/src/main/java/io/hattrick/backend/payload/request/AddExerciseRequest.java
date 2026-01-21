package io.hattrick.backend.payload.request;

import io.hattrick.backend.payload.data.RoleType;
import io.hattrick.backend.payload.data.FootballGroup;

public record AddExerciseRequest(
    String name,
    String description,
    FootballGroup footballGroup,
    RoleType roleType,
    String url
) {
}
