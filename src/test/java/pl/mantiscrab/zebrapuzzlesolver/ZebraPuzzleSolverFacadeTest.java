package pl.mantiscrab.zebrapuzzlesolver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.mantiscrab.zebrapuzzlesolver.dto.ConstraintDto;
import pl.mantiscrab.zebrapuzzlesolver.dto.DimensionDto;
import pl.mantiscrab.zebrapuzzlesolver.dto.SolutionDto;

import java.util.List;
import java.util.Map;

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
                cities.attribute("Vermont").is(food.attribute("cherry")),        //+
                cities.attribute("Vermont").isNot(names.attribute("Margaret")),  //+
                cities.attribute("Vermont").isNot(names.attribute("Otis")),      //+
                food.attribute("cherry").isNot(names.attribute("Margaret")),     //+
                food.attribute("cherry").isNot(names.attribute("Otis")),         //+
                food.attribute("duck").is(job.attribute("engineer")),            //+
                food.attribute("duck").isNot(names.attribute("Bobbie")),         //+
                job.attribute("engineer").isNot(names.attribute("Bobbie")),      //+
                names.attribute("Bobbie").is(cities.attribute("Ohio")),          //+
                job.attribute("botanist").isNot(names.attribute("Hersh")),       //+
                job.attribute("botanist").isNot(names.attribute("Angie")),       //+
                food.attribute("lamb").is(job.attribute("scientist")),           //+
                food.attribute("chocolate").isNot(cities.attribute("Louisiana")),//+
                names.attribute("Angie").is(cities.attribute("Arizona")),        //+
                names.attribute("Angie").isNot(job.attribute("engineer")),       //+
                names.attribute("Margaret").is(job.attribute("doctor")),         //+
                names.attribute("Margaret").isNot(cities.attribute("Oregon")),   //+
                job.attribute("farmer").isNot(food.attribute("bread")));         //+

        //when
        List<SolutionDto> solutions = ZebraPuzzleSolverFacade.solve(dimensions, constraints);

        //then
        Assertions.assertTrue(solutions.containsAll(List.of(
                new SolutionDto(Map.of(cities.getDimensionName(), cities.attribute("Vermont"),
                        job.getDimensionName(), job.attribute("farmer"),
                        names.getDimensionName(), names.attribute("Hersh"),
                        food.getDimensionName(), food.attribute("cherry"))),
                new SolutionDto(Map.of(cities.getDimensionName(), cities.attribute("Ohio"),
                        job.getDimensionName(), job.attribute("botanist"),
                        names.getDimensionName(), names.attribute("Bobbie"),
                        food.getDimensionName(), food.attribute("chocolate"))),
                new SolutionDto(Map.of(cities.getDimensionName(), cities.attribute("Louisiana"),
                        job.getDimensionName(), job.attribute("doctor"),
                        names.getDimensionName(), names.attribute("Margaret"),
                        food.getDimensionName(), food.attribute("bread"))),
                new SolutionDto(Map.of(cities.getDimensionName(), cities.attribute("Arizona"),
                        job.getDimensionName(), job.attribute("scientist"),
                        names.getDimensionName(), names.attribute("Angie"),
                        food.getDimensionName(), food.attribute("lamb"))),
                new SolutionDto(Map.of(cities.getDimensionName(), cities.attribute("Oregon"),
                        job.getDimensionName(), job.attribute("engineer"),
                        names.getDimensionName(), names.attribute("Otis"),
                        food.getDimensionName(), food.attribute("duck"))))));
        Assertions.assertEquals(5, solutions.size());
    }
}