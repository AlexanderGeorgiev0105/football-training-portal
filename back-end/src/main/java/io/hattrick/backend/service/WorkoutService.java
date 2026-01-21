package io.hattrick.backend.service;

import io.hattrick.backend.entity.*;
import io.hattrick.backend.payload.request.*;
import io.hattrick.backend.payload.response.*;

import java.util.List;

public interface WorkoutService {
    AddWorkoutResponse addWorkout(AddWorkoutRequest request, UserEntity user);

    GetWorkoutsResponse getWorkouts(UserEntity user);

    List<WorkoutEntity> getWorkouts(List<String> ids, UserEntity user);

    GetWorkoutResponse getWorkout(GetWorkoutRequest request, UserEntity user);

    List<ExerciseOptionEntity> getExerciseOptionEntitiesByWorkoutIds(
        List<String> ids,
        UserEntity user
    );

    void selectWorkout(SelectWorkoutRequest request, UserEntity user);

    GetWorkoutResponse getSelectedWorkout(UserEntity user);

	AddWorkoutResponse addFullWorkout(AddFullWorkoutRequest request, UserEntity user);

    EmptyResponse updateWorkout(UpdateWorkoutRequest request, UserEntity user);

	Void deleteWorkout(DeleteWorkoutRequest request, UserEntity user);
}
