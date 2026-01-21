package io.hattrick.backend.payload.request;

import io.hattrick.backend.payload.data.*;

public record UpdateExerciseRequest(
	String id,
	String name,
	String description,
	FootballGroup footballGroup,
	RoleType roleType,
	String url
) {

}
