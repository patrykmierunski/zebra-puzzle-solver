package pl.mantiscrab.zebrapuzzlesolver;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.mantiscrab.zebrapuzzlesolver.dto.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public abstract class Adapter {
    @Mapping(target = "attributeName", source = "name")
    abstract AttributeNameDto map(AttributeName attributeName);

    abstract AttributeName map(AttributeNameDto attributeNameDto);

    abstract ConstraintDto map(Constraint constraint);

    abstract Constraint map(ConstraintDto constraintDto);

    abstract CoordinateDto map(Coordinate coordinate);

    abstract Coordinate map(CoordinateDto coordinateDto);

    abstract DimensionDto map(Dimension dimension);

    abstract String mapToString(DimensionName dimensionName);

    abstract List<AttributeNameDto> map(Set<Dimension.Attribute> attributes);

    @Mapping(target = "name", source = "dimensionName")
    abstract Dimension map(DimensionDto dimensionDto);

    abstract DimensionNameDto map(DimensionName dimensionName);

    abstract DimensionName map(DimensionNameDto dimensionNameDto);

    abstract List<Dimension> mapDimensions(List<DimensionDto> dimensions);

    abstract List<Constraint> mapConstraints(List<ConstraintDto> constraints);

    abstract List<SolutionDto> mapSolutions(List<Solution> solutions);

    SolutionDto mapSolution(Solution solution) {
        Map<DimensionNameDto, AttributeNameDto> collect = solution.getAttributes().stream()
                .collect(Collectors.toMap(
                        a ->  this.map(a.getDimensionName()),
                        a ->  this.map(a.getAttributeName())));
        return new SolutionDto(collect);
    }
}
