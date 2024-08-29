package pl.mantiscrab.zebrapuzzlesolver.dto;

import java.util.Collections;
import java.util.Map;

public class SolutionDto {
    private final Map<DimensionNameDto, AttributeNameDto> solution;

    public SolutionDto(Map<DimensionNameDto, AttributeNameDto> solution) {
        this.solution = Collections.unmodifiableMap(solution);
    }
}
