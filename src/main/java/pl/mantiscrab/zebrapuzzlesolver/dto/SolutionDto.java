package pl.mantiscrab.zebrapuzzlesolver.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collections;
import java.util.Set;

@EqualsAndHashCode
@ToString
public class SolutionDto {
    private final Set<AttributeDto> solution;

    public SolutionDto(Set<AttributeDto> solution) {
        this.solution = Collections.unmodifiableSet(solution);
    }
}
