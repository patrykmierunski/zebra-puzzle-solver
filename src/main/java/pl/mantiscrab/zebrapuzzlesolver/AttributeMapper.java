package pl.mantiscrab.zebrapuzzlesolver;

import org.mapstruct.Mapper;
import pl.mantiscrab.zebrapuzzlesolver.dto.AttributeDto;

@Mapper(uses = {AttributeNameMapper.class, DimensionNameMapper.class})
public abstract class AttributeMapper {

    abstract AttributeDto map(Dimension.Attribute attribute);

    abstract Dimension.Attribute map(AttributeDto attributeDto);
}
