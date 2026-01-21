package io.hattrick.backend.payload.data;

public record WorkoutFullExercisePayload(
		int sets,
		int drills,
		int repeats,
		String name,
		String description,
		String url,
		FootballGroup footballGroup,
		RoleType roleType
) {

}
