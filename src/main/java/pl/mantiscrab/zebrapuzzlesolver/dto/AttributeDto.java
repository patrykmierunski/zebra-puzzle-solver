package pl.mantiscrab.zebrapuzzlesolver.dto;

public record AttributeDto(DimensionNameDto dimensionName, AttributeNameDto attributeName) {

    public ConstraintDto is(AttributeDto other) {
        CoordinateDto coordinate = new CoordinateDto(this, other);
        return new ConstraintDto(coordinate, true);
    }

    public ConstraintDto isNot(AttributeDto other) {
        CoordinateDto coordinate = new CoordinateDto(this, other);
        return new ConstraintDto(coordinate, false);
    }


}
