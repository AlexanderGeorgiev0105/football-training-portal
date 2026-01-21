package io.hattrick.backend.payload.request;

import java.util.List;

public record AddProgramRequest(String name, String description, List<String> workoutIds) {
}
