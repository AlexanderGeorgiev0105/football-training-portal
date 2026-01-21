package io.hattrick.backend.payload.response;

import java.util.List;

public record GetWorkoutsResponse(List<GetWorkoutResponse> workouts) {
}
