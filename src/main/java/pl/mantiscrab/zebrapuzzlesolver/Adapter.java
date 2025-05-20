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

    boolean map(ConstraintType constraintType) {
        return constraintType.equals(ConstraintType.IS);
    }

    @Mapping(target = "constraintType", source = "constraint")
    abstract Constraint map(ConstraintDto constraintDto);

    ConstraintType map(boolean isConstraint) {
        return isConstraint ? ConstraintType.IS : ConstraintType.IS_NOT;
    }

    abstract Coordinate map(CoordinateDto coordinateDto);

    String mapToString(DimensionName dimensionName) {
        return dimensionName.name();
    }

    abstract List<AttributeNameDto> map(Set<Dimension.Attribute> attributes);

    @Mapping(target = "name", source = "dimensionName")
    @Mapping(target = "attributes", source = "attributeNames")
    abstract Dimension map(DimensionDto dimensionDto);

    abstract String[] map(List<AttributeNameDto> attributeNames);

    String mapToString(AttributeNameDto value) {
        return value.getAttributeName();
    }

    @Mapping(target = "dimensionName", source = "name")
    abstract DimensionNameDto map(DimensionName dimensionName);

    @Mapping(target = "name", source = "dimensionName")
    abstract DimensionName map(DimensionNameDto dimensionNameDto);

    abstract List<Dimension> mapDimensions(List<DimensionDto> dimensions);

    abstract List<SolutionDto> mapSolutions(List<Solution> solutions);

    SolutionDto mapSolution(Solution solution) {
        Map<DimensionNameDto, AttributeNameDto> collect = solution.getAttributes().stream()
                .collect(Collectors.toMap(
                        a -> this.map(a.getDimensionName()),
                        a -> this.map(a.getAttributeName())));
        return null;
    }

    public List<Constraint> mapConstraints(List<ConstraintDto> constraints) {
        return null;
    }
}
