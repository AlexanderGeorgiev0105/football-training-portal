package io.hattrick.backend.controller;

import io.hattrick.backend.annotation.User;
import io.hattrick.backend.entity.UserEntity;
import io.hattrick.backend.payload.data.WorkoutFullExercisePayload;
import io.hattrick.backend.payload.request.*;
import io.hattrick.backend.payload.response.AddWorkoutResponse;
import io.hattrick.backend.payload.response.GetWorkoutResponse;
import io.hattrick.backend.payload.response.GetWorkoutsResponse;
import io.hattrick.backend.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workout")
public class WorkoutController {
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/add")
    public ResponseEntity<AddWorkoutResponse> addWorkout(@RequestBody AddWorkoutRequest request, @User UserEntity user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.workoutService.addWorkout(request, user));
    }

    @PostMapping("/add_full")
    public ResponseEntity<AddWorkoutResponse> addFullWorkout(@RequestBody AddFullWorkoutFormattedRequest request, @User UserEntity user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.workoutService.addFullWorkout(this.mapToAddFullWorkoutRequest(request), user));
    }

    @PutMapping("/select")
    public ResponseEntity<?> selectWorkout(@RequestBody SelectWorkoutRequest request, @User UserEntity user) {
        this.workoutService.selectWorkout(request, user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/all")
    public ResponseEntity<GetWorkoutsResponse> getWorkouts(@User UserEntity user) {
        return ResponseEntity.status(HttpStatus.OK).body(this.workoutService.getWorkouts(user));
    }

    @PostMapping("/get")
    public ResponseEntity<GetWorkoutResponse> getWorkout(@RequestBody GetWorkoutRequest request, @User UserEntity user) {
        return ResponseEntity.status(HttpStatus.OK).body(this.workoutService.getWorkout(request, user));
    }

    @GetMapping("/selected")
    public ResponseEntity<GetWorkoutResponse> getSelectedWorkout(@User UserEntity user) {
        return ResponseEntity.status(HttpStatus.OK).body(this.workoutService.getSelectedWorkout(user));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateWorkout(@RequestBody UpdateWorkoutRequest request, @User UserEntity user) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.workoutService.updateWorkout(request, user));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteWorkout(@RequestBody DeleteWorkoutRequest request, @User UserEntity user) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.workoutService.deleteWorkout(request, user));
    }

    private AddFullWorkoutRequest mapToAddFullWorkoutRequest(AddFullWorkoutFormattedRequest request) {
        return new AddFullWorkoutRequest(
            request.exercises().stream().map(exercise -> new WorkoutFullExercisePayload(
                exercise.sets().size(),
                exercise.sets().get(0).drills(),
                exercise.sets().get(0).repeats(),
                exercise.name(),
                exercise.description(),
                exercise.url(),
                exercise.footballGroup(),
                exercise.roleType()
            )).toList(),
            request.name(),
            request.description()
        );
    }

}
