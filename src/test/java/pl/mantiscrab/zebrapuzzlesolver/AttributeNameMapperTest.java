package pl.mantiscrab.zebrapuzzlesolver;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.mantiscrab.zebrapuzzlesolver.dto.AttributeNameDto;

class AttributeNameMapperTest {
    private final AttributeNameMapper mapper = Mappers.getMapper(AttributeNameMapper.class);
    private final String name = "name";

    @Test
    void shouldMapToAttributeName() {
        //when
        AttributeName attributeName = mapper.map(new AttributeNameDto(name));
        //then
        Assertions.assertThat(attributeName.name()).isEqualTo(name);
    }

    @Test
    void shouldMapToAttribute() {
        //when
        AttributeNameDto attributeNameDto = mapper.map(new AttributeName(name));
        //then
        Assertions.assertThat(attributeNameDto.getAttributeName()).isEqualTo(name);
    }
}