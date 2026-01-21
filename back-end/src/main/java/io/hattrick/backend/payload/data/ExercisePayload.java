package io.hattrick.backend.payload.data;

public record ExercisePayload(
        String id,
        String name,
        String description,
        FootballGroup footballGroup,
        RoleType roleType,
        String url
) {
}
