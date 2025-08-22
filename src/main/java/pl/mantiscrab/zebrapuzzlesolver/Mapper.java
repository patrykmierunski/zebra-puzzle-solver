package pl.mantiscrab.zebrapuzzlesolver;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.mantiscrab.zebrapuzzlesolver.dto.*;

import java.util.List;

class Mapper {
    ConstraintMapper constraintMapper = Mappers.getMapper(ConstraintMapper.class);
    DimensionMapper dimensionMapper = Mappers.getMapper(DimensionMapper.class);
    SolutionMapper solutionMapper = Mappers.getMapper(SolutionMapper.class);

    List<Dimension> mapDimensions(List<DimensionDto> dimensions) {
        return dimensionMapper.map(dimensions);
    }

    List<Constraint> mapConstraints(List<ConstraintDto> constraints) {
        return constraintMapper.map(constraints);
    }

    List<SolutionDto> mapSolutions(List<Solution> solutions) {
        return solutionMapper.map(solutions);
    }
}

@org.mapstruct.Mapper(uses = {AttributeNameMapper.class, DimensionNameMapper.class})
abstract class AttributeMapper {

    abstract AttributeDto map(Dimension.Attribute attribute);

    abstract Dimension.Attribute map(AttributeDto attributeDto);
}

@org.mapstruct.Mapper(uses = {pl.mantiscrab.zebrapuzzlesolver.AttributeMapper.class, AttributeNameMapper.class, CoordinateMapper.class, DimensionMapper.class, DimensionNameMapper.class})
abstract class CoordinateMapper {
    abstract Coordinate map(CoordinateDto coordinateDto);
}

@org.mapstruct.Mapper
abstract class AttributeNameMapper {
    @Mapping(target = "attributeName", source = "name")
    abstract AttributeNameDto map(AttributeName attributeName);

    @Mapping(target = "name", source = "attributeName")
    abstract AttributeName map(AttributeNameDto attributeNameDto);

}

@org.mapstruct.Mapper(uses = {CoordinateMapper.class})
abstract class ConstraintMapper {
    @Mapping(target = "constraintType", source = "constraint")
    abstract Constraint map(ConstraintDto constraintDto);

    abstract List<Constraint> map(List<ConstraintDto> constraintDto);

    ConstraintType map(boolean isConstraint) {
        return isConstraint ? ConstraintType.IS : ConstraintType.IS_NOT;
    }
}

@org.mapstruct.Mapper(uses = {pl.mantiscrab.zebrapuzzlesolver.AttributeMapper.class})
abstract class SolutionMapper {
    abstract SolutionDto map(Solution solutionDto);

    abstract List<SolutionDto> map(List<Solution> solutionDtos);
}

@org.mapstruct.Mapper(uses = {pl.mantiscrab.zebrapuzzlesolver.AttributeMapper.class, AttributeNameMapper.class, DimensionNameMapper.class})
abstract class DimensionMapper {
    @Mapping(target = "name", source = "dimensionName")
    @Mapping(target = "attributes", source = "attributeNames")
    abstract Dimension map(DimensionDto dimensionNameDto);

    abstract List<Dimension> map(List<DimensionDto> dimensionNameDto);
}

@org.mapstruct.Mapper
abstract class DimensionNameMapper {
    @Mapping(target = "dimensionName", source = "name")
    abstract DimensionNameDto map(DimensionName dimensionName);

    @Mapping(target = "name", source = "dimensionName")
    abstract DimensionName map(DimensionNameDto dimensionNameDto);
}