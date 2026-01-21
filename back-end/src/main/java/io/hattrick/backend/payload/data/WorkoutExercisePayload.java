package io.hattrick.backend.payload.data;

public record WorkoutExercisePayload(
        String exerciseId,
        int sets,
        int drills,
        int repeats
) {
}
