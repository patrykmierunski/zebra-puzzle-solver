package pl.mantiscrab.zebrapuzzlesolver;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.mantiscrab.zebrapuzzlesolver.dto.DimensionNameDto;

class DimensionNameMapperTest {
    private final DimensionNameMapper mapper = Mappers.getMapper(DimensionNameMapper.class);
    private final String name = "name";

    @Test
    void shouldMapToDimensionName() {
        //when
        DimensionName dimensionName = mapper.map(new DimensionNameDto(name));
        //then
        Assertions.assertThat(dimensionName.name()).isEqualTo(name);
    }

    @Test
    void shouldMapToDimension() {
        //when
        DimensionNameDto dimensionNameDto = mapper.map(new DimensionName(name));
        //then
        Assertions.assertThat(dimensionNameDto.dimensionName()).isEqualTo(name);
    }
}