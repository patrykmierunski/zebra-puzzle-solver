package pl.mantiscrab.zebrapuzzlesolver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AttributeNameDto {
    private String attributeName;

    public ConstraintDto is(AttributeNameDto other) {
        CoordinateDto coordinate = new CoordinateDto(this, other);
        return new ConstraintDto(coordinate, true);
    }

    public ConstraintDto isNot(AttributeNameDto other) {
        CoordinateDto coordinate = new CoordinateDto(this, other);
        return new ConstraintDto(coordinate, false);
    }
}
