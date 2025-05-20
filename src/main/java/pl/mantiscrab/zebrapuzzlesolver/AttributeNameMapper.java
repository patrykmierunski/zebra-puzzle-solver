package pl.mantiscrab.zebrapuzzlesolver;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.mantiscrab.zebrapuzzlesolver.dto.AttributeNameDto;

@Mapper
public abstract class AttributeNameMapper {
    @Mapping(target = "attributeName", source = "name")
    abstract AttributeNameDto map(AttributeName attributeName);

    @Mapping(target = "name", source = "attributeName")
    abstract AttributeName map(AttributeNameDto attributeNameDto);

}
