package io.hattrick.backend.payload.data;

public record WorkoutExerciseData(
	ExercisePayload exercise,
	int sets,
	int drills,
	int repeats
) {

}
