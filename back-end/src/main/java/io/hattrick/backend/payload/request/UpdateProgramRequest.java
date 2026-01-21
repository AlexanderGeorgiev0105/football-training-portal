package io.hattrick.backend.payload.request;

import java.util.List;

public record UpdateProgramRequest(
	String id, String name, String description, List<String> workoutIds
) {

}
