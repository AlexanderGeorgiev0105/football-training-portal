package io.hattrick.backend.payload.data;

import java.util.List;

public record WorkoutFullExerciseFormattedPayload(
	List<FormattedSetsData> sets,
	String name,
	String description,
	String url,
	FootballGroup footballGroup,
	RoleType roleType) {

}
