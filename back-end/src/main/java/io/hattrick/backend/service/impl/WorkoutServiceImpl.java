package io.hattrick.backend.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.hattrick.backend.entity.ExerciseEntity;
import io.hattrick.backend.entity.ExerciseOptionEntity;
import io.hattrick.backend.entity.UserEntity;
import io.hattrick.backend.entity.WorkoutEntity;
import io.hattrick.backend.exception.WorkoutNotFoundException;
import io.hattrick.backend.mapper.ExerciseMapper;
import io.hattrick.backend.mapper.WorkoutMapper;
import io.hattrick.backend.payload.data.WorkoutExercisePayload;
import io.hattrick.backend.payload.data.WorkoutFullExercisePayload;
import io.hattrick.backend.payload.request.AddFullWorkoutRequest;
import io.hattrick.backend.payload.request.AddWorkoutRequest;
import io.hattrick.backend.payload.request.DeleteWorkoutRequest;
import io.hattrick.backend.payload.request.GetWorkoutRequest;
import io.hattrick.backend.payload.request.SelectWorkoutRequest;
import io.hattrick.backend.payload.request.UpdateWorkoutRequest;
import io.hattrick.backend.payload.response.AddWorkoutResponse;
import io.hattrick.backend.payload.response.EmptyResponse;
import io.hattrick.backend.payload.response.GetWorkoutResponse;
import io.hattrick.backend.payload.response.GetWorkoutsResponse;
import io.hattrick.backend.repository.WorkoutRepository;
import io.hattrick.backend.service.ExerciseOptionService;
import io.hattrick.backend.service.ExerciseService;
import io.hattrick.backend.service.ProgramService;
import io.hattrick.backend.service.SelectedWorkoutService;
import io.hattrick.backend.service.WorkoutService;
import jakarta.transaction.Transactional;

@Service
public class WorkoutServiceImpl implements WorkoutService {

	private final WorkoutRepository workoutRepository;
	private final ExerciseService exerciseService;
	private final ExerciseMapper exerciseMapper;
	private final ExerciseOptionService exerciseOptionService;
	private final SelectedWorkoutService selectedWorkoutService;
	private ProgramService programService;
	private final WorkoutMapper workoutMapper;

	public WorkoutServiceImpl(
		WorkoutRepository workoutRepository,
		ExerciseService exerciseService,
		ExerciseMapper exerciseMapper,
		ExerciseOptionService exerciseOptionService,
		SelectedWorkoutService selectedWorkoutService,
		WorkoutMapper workoutMapper
	) {
		this.workoutRepository = workoutRepository;
		this.exerciseService = exerciseService;
		this.exerciseMapper = exerciseMapper;
		this.exerciseOptionService = exerciseOptionService;
		this.selectedWorkoutService = selectedWorkoutService;
		this.workoutMapper = workoutMapper;
	}

	@Autowired
	private void setProgram(ProgramService programService) {
		this.programService = programService;
	}

	@Override
	public AddWorkoutResponse addWorkout(AddWorkoutRequest request, UserEntity user) {
		List<ExerciseEntity> exercises = this.exerciseService.findByIds(request.exercises()
			.stream()
			.map(
				WorkoutExercisePayload::exerciseId)
			.toList(), user);
		WorkoutEntity workoutEntity = this.workoutMapper.mapToEntity(request, user, exercises);
		workoutEntity = this.workoutRepository.save(workoutEntity);
		this.exerciseOptionService.save(request.exercises(), exercises, workoutEntity);
		this.selectedWorkoutService.create(workoutEntity, user);
		return this.workoutMapper.mapToResponse(workoutEntity);
	}

	@Override
	public GetWorkoutsResponse getWorkouts(UserEntity user) {
		List<WorkoutEntity> workoutEntities = this.workoutRepository.findAllByUser(user);
		List<ExerciseOptionEntity> exerciseOptionEntities = this.exerciseOptionService.findAllByWorkoutIds(
			workoutEntities.stream()
				.map(WorkoutEntity::getId)
				.toList(), user);
		return this.workoutMapper.mapToGetWorkouts(workoutEntities, exerciseOptionEntities);
	}

	@Override
	public List<WorkoutEntity> getWorkouts(List<String> ids, UserEntity user) {
		return this.workoutRepository.findAllByIdsAndUser(ids, user);
	}

	@Override
	public GetWorkoutResponse getWorkout(GetWorkoutRequest request, UserEntity user) {
		WorkoutEntity workoutEntity = this.workoutRepository.findById(request.id())
			.orElseThrow(() -> new WorkoutNotFoundException(request.id()));
		List<ExerciseOptionEntity> exerciseOptionEntities = this.exerciseOptionService.findAllByWorkoutIds(
			List.of(workoutEntity.getId()), user);
		return this.workoutMapper.mapToGetWorkout(workoutEntity, exerciseOptionEntities);
	}

	@Override
	public List<ExerciseOptionEntity> getExerciseOptionEntitiesByWorkoutIds(
		List<String> ids,
		UserEntity user
	) {
		return this.exerciseOptionService.findAllByWorkoutIds(ids, user);
	}

	@Override
	public void selectWorkout(SelectWorkoutRequest request, UserEntity user) {
		WorkoutEntity workoutEntity = this.workoutRepository.findById(request.workoutId())
			.orElseThrow(() -> new WorkoutNotFoundException(request.workoutId()));
		this.selectedWorkoutService.create(workoutEntity, user);
	}

	@Override
	public GetWorkoutResponse getSelectedWorkout(UserEntity user) {
		return this.getWorkout(new GetWorkoutRequest(this.selectedWorkoutService.getSelectedWorkout(
				user)
			.getId()), user);
	}

	@Override
	public AddWorkoutResponse addFullWorkout(AddFullWorkoutRequest request, UserEntity user) {
		Map<String, WorkoutFullExercisePayload> exerciseNameMap = new HashMap<>();
		for (WorkoutFullExercisePayload exercise : request.exercises()) {
			exerciseNameMap.put(exercise.name(), exercise);
		}
		List<ExerciseEntity> exercises = this.exerciseMapper.mapFromWorkoutExerciseFullPayload(
			request.exercises(),
			user
		);
		List<ExerciseEntity> existingExercises = new ArrayList<>();
		List<ExerciseEntity> newExercises = new ArrayList<>();
		for (ExerciseEntity exercise : exercises) {
			Optional<ExerciseEntity> existingExercise = this.exerciseService.findByName(
				exercise.getName(),
				user
			);
			if (existingExercise.isPresent()) {
				existingExercises.add(existingExercise.get());
			} else {
				newExercises.add(exercise);
			}
		}
		newExercises = this.exerciseService.save(newExercises);
		exercises = new ArrayList<>();
		exercises.addAll(existingExercises);
		exercises.addAll(newExercises);
		WorkoutEntity workoutEntity = this.workoutMapper.mapToEntity(request, user, exercises);
		workoutEntity = this.workoutRepository.save(workoutEntity);
		final WorkoutEntity finalWorkoutEntity = workoutEntity;
		List<ExerciseOptionEntity> exerciseOptionEntities = exercises.stream()
			.map(exercise -> {
				WorkoutFullExercisePayload exercisePayload = exerciseNameMap.get(exercise.getName());
				return this.exerciseOptionService.mapToEntity(
					exercisePayload,
					exercise,
					finalWorkoutEntity
				);
			})
			.toList();
		this.exerciseOptionService.save(exerciseOptionEntities);
		this.selectedWorkoutService.create(workoutEntity, user);
		return this.workoutMapper.mapToResponse(workoutEntity);
	}

	@Override
	public EmptyResponse updateWorkout(UpdateWorkoutRequest request, UserEntity user) {
		Optional<WorkoutEntity> optional = this.workoutRepository.findByIdAndUser(request.id(), user);
		if (optional.isEmpty()) {
			throw new WorkoutNotFoundException(request.id());
		}
		WorkoutEntity workoutEntity = optional.get();
		workoutEntity.setName(request.name());
		workoutEntity.setDescription(request.description());
		workoutEntity.setExercises(this.exerciseService.findByIds(request.exercises()
			.stream()
			.map(
				WorkoutExercisePayload::exerciseId)
			.toList(), user));
		this.workoutRepository.save(workoutEntity);
		this.exerciseOptionService.update(request.exercises(), workoutEntity.getExercises(), workoutEntity);
		return new EmptyResponse();
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public Void deleteWorkout(DeleteWorkoutRequest request, UserEntity user) {
		Optional<WorkoutEntity> optional = this.workoutRepository.findByIdAndUser(request.id(), user);
		WorkoutEntity workoutEntity = optional.orElseThrow(() -> new WorkoutNotFoundException(request.id()));
		this.exerciseService.removeWorkoutFromExercises(workoutEntity);
		this.exerciseOptionService.removeExerciseOptionsByWorkout(workoutEntity);
		this.selectedWorkoutService.delete(workoutEntity);
		this.programService.removeWorkout(workoutEntity);
		this.workoutRepository.delete(workoutEntity);
		return null;
	}
}
