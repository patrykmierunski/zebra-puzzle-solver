package pl.mantiscrab.zebrapuzzlesolver;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.mantiscrab.zebrapuzzlesolver.dto.DimensionNameDto;

@Mapper
public abstract class DimensionNameMapper {
    @Mapping(target = "dimensionName", source = "name")
    abstract DimensionNameDto map(DimensionName dimensionName);

    @Mapping(target = "name", source = "dimensionName")
    abstract DimensionName map(DimensionNameDto dimensionNameDto);
}