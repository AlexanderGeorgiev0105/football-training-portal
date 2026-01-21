package io.hattrick.backend.service;

import io.hattrick.backend.entity.*;

public interface SelectedWorkoutService {

	void create(WorkoutEntity workoutEntity, UserEntity user);

	void edit(WorkoutEntity workoutEntity, UserEntity user);

	WorkoutEntity getSelectedWorkout(UserEntity user);

	void delete(WorkoutEntity workoutEntity);
}
