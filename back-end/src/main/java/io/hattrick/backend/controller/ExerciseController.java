package io.hattrick.backend.controller;

import io.hattrick.backend.annotation.User;
import io.hattrick.backend.entity.UserEntity;
import io.hattrick.backend.payload.request.*;
import io.hattrick.backend.payload.response.*;
import io.hattrick.backend.service.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/all")
    public ResponseEntity<GetExercisesResponse> getExercises(@User UserEntity user) {
        return ResponseEntity.status(HttpStatus.OK).body(this.exerciseService.getExercises(user));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addExercise(@RequestBody AddExerciseRequest request, @User UserEntity user) {
        return ResponseEntity.status(HttpStatus.OK).body(this.exerciseService.addExercise(request, user));
    }

    @PutMapping("/update")
    public ResponseEntity<EmptyResponse> updateExercise(@RequestBody UpdateExerciseRequest request, @User UserEntity user) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.exerciseService.updateExercise(request, user));
    }
}
