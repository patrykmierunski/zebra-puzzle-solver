package pl.mantiscrab.zebrapuzzlesolver;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.mantiscrab.zebrapuzzlesolver.dto.AttributeDto;
import pl.mantiscrab.zebrapuzzlesolver.dto.AttributeNameDto;
import pl.mantiscrab.zebrapuzzlesolver.dto.DimensionNameDto;

class AttributeMapperTest {
    private final AttributeMapper mapper = Mappers.getMapper(AttributeMapper.class);
    private final String attributeName = "attribute";
    private final String dimensionName = "dimension";


    @Test
    void shouldMapToAttribute() {
        //given-when
        Dimension.Attribute attribute = mapper.map(new AttributeDto(new DimensionNameDto(dimensionName), new AttributeNameDto(this.attributeName)));
        //then
        Assertions.assertThat(attribute.getAttributeName().name()).isEqualTo(attributeName);
        Assertions.assertThat(attribute.getDimensionName().name()).isEqualTo(dimensionName);
    }

    @Test
    void shouldMapToDimension() {
        //given-when
        AttributeDto attributeDto = mapper.map(new Dimension.Attribute(new DimensionName(dimensionName), new AttributeName(attributeName)));
        //then
        Assertions.assertThat(attributeDto.attributeName().getAttributeName()).isEqualTo(attributeName);
        Assertions.assertThat(attributeDto.dimensionName().dimensionName()).isEqualTo(dimensionName);
    }

}