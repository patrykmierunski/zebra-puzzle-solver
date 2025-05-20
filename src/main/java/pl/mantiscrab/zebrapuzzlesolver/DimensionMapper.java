package pl.mantiscrab.zebrapuzzlesolver;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.mantiscrab.zebrapuzzlesolver.dto.DimensionDto;

import java.util.List;

@Mapper(uses = {AttributeMapper.class, AttributeNameMapper.class, DimensionNameMapper.class})
public abstract class DimensionMapper {
    @Mapping(target = "name", source = "dimensionName")
    @Mapping(target = "attributes", source = "attributeNames")
    abstract Dimension map(DimensionDto dimensionNameDto);

    abstract List<Dimension> map(List<DimensionDto> dimensionNameDto);
}
