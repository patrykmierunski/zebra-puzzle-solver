package pl.mantiscrab.zebrapuzzlesolver.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class DimensionDto {
    private final DimensionNameDto dimensionName;
    private final List<AttributeNameDto> attributeNames;

    @Builder
    private DimensionDto(String dimensionName, List<String> attributeNames) {
        this.dimensionName = new DimensionNameDto(dimensionName);
        this.attributeNames = attributeNames.stream().map(AttributeNameDto::new).toList();
    }

    public AttributeNameDto attribute(String name) {
        return attributeNames.stream().filter(aN -> aN.getAttributeName().equals(name)).findFirst().orElseThrow();
    }
}
