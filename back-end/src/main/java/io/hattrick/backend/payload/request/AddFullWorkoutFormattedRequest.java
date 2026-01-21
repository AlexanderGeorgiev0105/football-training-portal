package io.hattrick.backend.payload.request;

import io.hattrick.backend.payload.data.*;
import java.util.List;

public record AddFullWorkoutFormattedRequest(List<WorkoutFullExerciseFormattedPayload> exercises, String name, String description) {

}
