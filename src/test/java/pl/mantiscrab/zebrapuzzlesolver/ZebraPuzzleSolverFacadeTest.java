package pl.mantiscrab.zebrapuzzlesolver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static pl.mantiscrab.zebrapuzzlesolver.Constraint.is;
import static pl.mantiscrab.zebrapuzzlesolver.Constraint.isNot;

class ZebraPuzzleSolverFacadeTest {

    @Test
    void shouldSolvePuzzle() {
        //given
        Dimension names = Dimension.builder().name("names").attributes("Margaret", "Otis", "Bobbie", "Hersh", "Angie").build();
        Dimension cities = Dimension.builder().name("cities").attributes("Vermont", "Ohio", "Oregon", "Louisiana", "Arizona").build();
        Dimension food = Dimension.builder().name("food").attributes("cherry", "duck", "lamb", "chocolate", "bread").build();
        Dimension job = Dimension.builder().name("job").attributes("engineer", "botanist", "scientist", "doctor", "farmer").build();

        List<Dimension> dimensions = List.of(names, cities, food, job);
        List<Constraint> constraints = List.of(
                is(cities.attribute("Vermont"), food.attribute("cherry")),        //+
                isNot(cities.attribute("Vermont"), names.attribute("Margaret")),  //+
                isNot(cities.attribute("Vermont"), names.attribute("Otis")),      //+
                isNot(food.attribute("cherry"), names.attribute("Margaret")),     //+
                isNot(food.attribute("cherry"), names.attribute("Otis")),         //+
                is(food.attribute("duck"), job.attribute("engineer")),            //+
                isNot(food.attribute("duck"), names.attribute("Bobbie")),         //+
                isNot(job.attribute("engineer"), names.attribute("Bobbie")),      //+
                is(names.attribute("Bobbie"), cities.attribute("Ohio")),          //+
                isNot(job.attribute("botanist"), names.attribute("Hersh")),       //+
                isNot(job.attribute("botanist"), names.attribute("Angie")),       //+
                is(food.attribute("lamb"), job.attribute("scientist")),           //+
                isNot(food.attribute("chocolate"), cities.attribute("Louisiana")),//+
                is(names.attribute("Angie"), cities.attribute("Arizona")),        //+
                isNot(names.attribute("Angie"), job.attribute("engineer")),       //+
                is(names.attribute("Margaret"), job.attribute("doctor")),         //+
                isNot(names.attribute("Margaret"), cities.attribute("Oregon")),   //+
                isNot(job.attribute("farmer"), food.attribute("bread")));         //+

        //when
        List<Solution> solutions = ZebraPuzzleSolverFacade.solve(dimensions, constraints);

        //then
        Assertions.assertTrue(solutions.containsAll(List.of(
                Solution.of(Set.of(cities.attribute("Vermont"), job.attribute("farmer"), names.attribute("Hersh"), food.attribute("cherry"))),
                Solution.of(Set.of(cities.attribute("Ohio"), job.attribute("botanist"), names.attribute("Bobbie"), food.attribute("chocolate"))),
                Solution.of(Set.of(cities.attribute("Louisiana"), job.attribute("doctor"), names.attribute("Margaret"), food.attribute("bread"))),
                Solution.of(Set.of(cities.attribute("Arizona"), job.attribute("scientist"), names.attribute("Angie"), food.attribute("lamb"))),
                Solution.of(Set.of(cities.attribute("Oregon"), job.attribute("engineer"), names.attribute("Otis"), food.attribute("duck"))))));
        Assertions.assertEquals(5, solutions.size());
    }
}