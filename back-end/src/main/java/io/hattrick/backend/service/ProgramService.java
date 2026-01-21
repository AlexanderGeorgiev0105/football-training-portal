package io.hattrick.backend.service;

import io.hattrick.backend.entity.*;
import io.hattrick.backend.payload.request.*;
import io.hattrick.backend.payload.response.*;

public interface ProgramService {
    AddProgramResponse addProgram(AddProgramRequest request, UserEntity user);

    GetProgramsResponse getPrograms(UserEntity user);

    GetProgramResponse getProgram(GetProgramRequest request, UserEntity user);

    Void updateProgram(UpdateProgramRequest request, UserEntity user);

    void removeWorkout(WorkoutEntity workoutEntity);
}
