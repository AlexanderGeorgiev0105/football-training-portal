package io.hattrick.backend.payload.response;

import io.hattrick.backend.payload.data.ExercisePayload;

import java.util.List;

public record GetExercisesResponse(List<ExercisePayload> exercises) {
}
