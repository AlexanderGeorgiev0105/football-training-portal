package io.hattrick.backend.payload.response;

import java.util.List;

public record GetProgramsResponse(List<GetProgramResponse> programs) {
}
