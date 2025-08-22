package pl.mantiscrab.zebrapuzzlesolver.controller;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.mantiscrab.zebrapuzzlesolver.dto.*;
import pl.zebrapuzzlesolver.*;

import java.util.List;

@Mapper
abstract class RestMapper {
    abstract List<DimensionDto> mapDimensions(List<Dimension> dimensions);

    @Mapping(target = "dimensionName", source = "name")
    @Mapping(target = "attributeNames", source = "attributes")
    abstract DimensionDto map(Dimension dimension);

    abstract List<ConstraintDto> mapConstraints(List<Constraint> constraints);

    abstract ConstraintDto mapConstraint(Constraint constraint);

    @Mapping(target = "firstAttribute", source = "attribute1")
    @Mapping(target = "secondAttribute", source = "attribute2")
    abstract CoordinateDto mapCoordinate(Coordinate constraint);

    DimensionNameDto mapDimensionName(String value) {
        return new DimensionNameDto(value);
    }

    AttributeNameDto mapAttributeName(String string) {
        return new AttributeNameDto(string);
    }

    Solutions mapSolutiaon(List<SolutionDto> solutions) {
        return new Solutions().solutions(mapSolutions(solutions));
    }

    abstract List<Solution> mapSolutions(List<SolutionDto> solutions);

    abstract Solution mapSolution(SolutionDto solutions);

    String map(AttributeNameDto attributeNameDto) {
        return attributeNameDto.getAttributeName();
    }

    String map(DimensionNameDto value) {
        return value.dimensionName();
    }
}
