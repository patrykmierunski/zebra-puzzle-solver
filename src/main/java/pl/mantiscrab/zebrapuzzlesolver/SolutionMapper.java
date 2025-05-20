package pl.mantiscrab.zebrapuzzlesolver;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.mantiscrab.zebrapuzzlesolver.dto.SolutionDto;

import java.util.List;

@Mapper(uses = {AttributeMapper.class})
public abstract class SolutionMapper {
    @Mapping(target = "solution", source = "attributes")
    abstract SolutionDto map(Solution solutionDto);

    abstract List<SolutionDto> map(List<Solution> solutionDtos);
}
