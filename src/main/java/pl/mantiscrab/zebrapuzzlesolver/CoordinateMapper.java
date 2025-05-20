package pl.mantiscrab.zebrapuzzlesolver;

import org.mapstruct.Mapper;
import pl.mantiscrab.zebrapuzzlesolver.dto.CoordinateDto;

@Mapper(uses = {AttributeMapper.class, AttributeNameMapper.class, CoordinateMapper.class, DimensionMapper.class, DimensionNameMapper.class})
public abstract class CoordinateMapper {
    abstract Coordinate map(CoordinateDto coordinateDto);
}
