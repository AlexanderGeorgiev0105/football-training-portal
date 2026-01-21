package io.hattrick.backend.exception;

public class WorkoutNotFoundException extends RuntimeException {
    public WorkoutNotFoundException(String id) {
        super("Workout with id " + id + " not found");
    }
}
