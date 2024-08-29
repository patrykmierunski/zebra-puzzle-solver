package pl.mantiscrab.zebrapuzzlesolver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ConstraintDto {
    private final CoordinateDto coordinate;
    private final boolean isConstraint;
}
