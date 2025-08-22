package pl.mantiscrab.zebrapuzzlesolver.dto;

import java.util.Collections;
import java.util.Set;

public record SolutionDto(Set<AttributeDto> attributes) {
    public SolutionDto(Set<AttributeDto> attributes) {
        this.attributes = Collections.unmodifiableSet(attributes);
    }
}
