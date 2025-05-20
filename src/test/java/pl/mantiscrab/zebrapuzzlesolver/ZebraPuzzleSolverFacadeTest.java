package pl.mantiscrab.zebrapuzzlesolver;

import org.junit.jupiter.api.Test;
import pl.mantiscrab.zebrapuzzlesolver.dto.ConstraintDto;
import pl.mantiscrab.zebrapuzzlesolver.dto.DimensionDto;
import pl.mantiscrab.zebrapuzzlesolver.dto.SolutionDto;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ZebraPuzzleSolverFacadeTest {

    @Test
    void shouldSolvePuzzle() {
        //given
        DimensionDto names = DimensionDto.builder().dimensionName("names").attributeNames(List.of("Margaret", "Otis", "Bobbie", "Hersh", "Angie")).build();
        DimensionDto cities = DimensionDto.builder().dimensionName("cities").attributeNames(List.of("Vermont", "Ohio", "Oregon", "Louisiana", "Arizona")).build();
        DimensionDto food = DimensionDto.builder().dimensionName("food").attributeNames(List.of("cherry", "duck", "lamb", "chocolate", "bread")).build();
        DimensionDto job = DimensionDto.builder().dimensionName("job").attributeNames(List.of("engineer", "botanist", "scientist", "doctor", "farmer")).build();

        List<DimensionDto> dimensions = List.of(names, cities, food, job);
        List<ConstraintDto> constraints = List.of(
                cities.attribute("Vermont").is(food.attribute("cherry")),
                cities.attribute("Vermont").isNot(names.attribute("Margaret")),
                cities.attribute("Vermont").isNot(names.attribute("Otis")),
                food.attribute("cherry").isNot(names.attribute("Margaret")),
                food.attribute("cherry").isNot(names.attribute("Otis")),
                food.attribute("duck").is(job.attribute("engineer")),
                food.attribute("duck").isNot(names.attribute("Bobbie")),
                job.attribute("engineer").isNot(names.attribute("Bobbie")),
                names.attribute("Bobbie").is(cities.attribute("Ohio")),
                job.attribute("botanist").isNot(names.attribute("Hersh")),
                job.attribute("botanist").isNot(names.attribute("Angie")),
                food.attribute("lamb").is(job.attribute("scientist")),
                food.attribute("chocolate").isNot(cities.attribute("Louisiana")),
                names.attribute("Angie").is(cities.attribute("Arizona")),
                names.attribute("Angie").isNot(job.attribute("engineer")),
                names.attribute("Margaret").is(job.attribute("doctor")),
                names.attribute("Margaret").isNot(cities.attribute("Oregon")),
                job.attribute("farmer").isNot(food.attribute("bread")));

        //when
        List<SolutionDto> solutions = new ZebraPuzzleSolverFacade().solve(dimensions, constraints);

        //then
        assertThat(solutions).containsExactlyInAnyOrderElementsOf(List.of(
                new SolutionDto(Set.of(cities.attribute("Vermont"),
                        job.attribute("farmer"),
                        names.attribute("Hersh"),
                        food.attribute("cherry"))),
                new SolutionDto(Set.of(cities.attribute("Ohio"),
                        job.attribute("botanist"),
                        names.attribute("Bobbie"),
                        food.attribute("chocolate"))),
                new SolutionDto(Set.of(cities.attribute("Louisiana"),
                        job.attribute("doctor"),
                        names.attribute("Margaret"),
                        food.attribute("bread"))),
                new SolutionDto(Set.of(cities.attribute("Arizona"),
                        job.attribute("scientist"),
                        names.attribute("Angie"),
                        food.attribute("lamb"))),
                new SolutionDto(Set.of(cities.attribute("Oregon"),
                        job.attribute("engineer"),
                        names.attribute("Otis"),
                        food.attribute("duck")))));
    }
}