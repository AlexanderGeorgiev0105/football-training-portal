package io.hattrick.backend.payload.request;

import io.hattrick.backend.payload.data.WorkoutExercisePayload;
import java.util.List;

public record UpdateWorkoutRequest(String id, List<WorkoutExercisePayload> exercises, String name, String description) {

}
