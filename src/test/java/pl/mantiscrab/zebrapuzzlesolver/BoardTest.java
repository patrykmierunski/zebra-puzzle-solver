package pl.mantiscrab.zebrapuzzlesolver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void shouldUpdateHorizontalAndVerticalFieldsWhenIsConstraintAdded() {
        //given
        Dimension dim1 = Dimension.builder().name("people").attributes("Adam", "Bobbie", "Celine").build();
        Dimension dim2 = Dimension.builder().name("places").attributes("Attic", "Boulevard", "Square").build();

        Board board = Board.create(dim1, dim2);

        Constraint adamIsAttic = dim1.attribute("Adam").is(dim2.attribute("Attic"));

        //when
        board.updateWithConstraint(adamIsAttic);

        //then
        Assertions.assertEquals(ConstraintType.IS, board.getConstraint(new Coordinate(dim1.attribute("Adam"), dim2.attribute("Attic"))).get().getConstraintType());
        Assertions.assertEquals(ConstraintType.IS_NOT, board.getConstraint(new Coordinate(dim1.attribute("Adam"), dim2.attribute("Boulevard"))).get().getConstraintType());
        Assertions.assertEquals(ConstraintType.IS_NOT, board.getConstraint(new Coordinate(dim1.attribute("Adam"), dim2.attribute("Square"))).get().getConstraintType());
        Assertions.assertEquals(ConstraintType.IS_NOT, board.getConstraint(new Coordinate(dim1.attribute("Bobbie"), dim2.attribute("Attic"))).get().getConstraintType());
        Assertions.assertTrue(board.getConstraint(new Coordinate(dim1.attribute("Bobbie"), dim2.attribute("Boulevard"))).isEmpty());
        Assertions.assertTrue(board.getConstraint(new Coordinate(dim1.attribute("Bobbie"), dim2.attribute("Square"))).isEmpty());
        Assertions.assertEquals(ConstraintType.IS_NOT, board.getConstraint(new Coordinate(dim1.attribute("Celine"), dim2.attribute("Attic"))).get().getConstraintType());
        Assertions.assertTrue(board.getConstraint(new Coordinate(dim1.attribute("Celine"), dim2.attribute("Boulevard"))).isEmpty());
        Assertions.assertTrue(board.getConstraint(new Coordinate(dim1.attribute("Celine"), dim2.attribute("Square"))).isEmpty());
    }



    @Test
    void shouldSolveBoardIfNecessaryInformationProvided() {
        //given
        Dimension dim1 = Dimension.builder().name("people").attributes("Adam", "Bobbie", "Celine").build();
        Dimension dim2 = Dimension.builder().name("places").attributes("Attic", "Boulevard", "Square").build();

        Board board = Board.create(dim1, dim2);

        Constraint adamIsAttic = dim1.attribute("Adam").is(dim2.attribute("Attic"));
        Constraint bobbieIsNotSquare = dim1.attribute("Bobbie").isNot(dim2.attribute("Square"));

        //when
        board.updateWithConstraint(adamIsAttic);
        board.updateWithConstraint(bobbieIsNotSquare);

        //then
        System.out.println(board);
        Assertions.assertEquals(ConstraintType.IS, board.getForCoordinate(new Coordinate(dim1.attribute("Adam"), dim2.attribute("Attic"))));
        Assertions.assertEquals(ConstraintType.IS_NOT, board.getForCoordinate(new Coordinate(dim1.attribute("Adam"), dim2.attribute("Boulevard"))));
        Assertions.assertEquals(ConstraintType.IS_NOT, board.getForCoordinate(new Coordinate(dim1.attribute("Adam"), dim2.attribute("Square"))));
        Assertions.assertEquals(ConstraintType.IS_NOT, board.getForCoordinate(new Coordinate(dim1.attribute("Bobbie"), dim2.attribute("Attic"))));
        Assertions.assertEquals(ConstraintType.IS, board.getForCoordinate(new Coordinate(dim1.attribute("Bobbie"), dim2.attribute("Boulevard"))));
        Assertions.assertEquals(ConstraintType.IS_NOT, board.getForCoordinate(new Coordinate(dim1.attribute("Bobbie"), dim2.attribute("Square"))));
        Assertions.assertEquals(ConstraintType.IS_NOT, board.getForCoordinate(new Coordinate(dim1.attribute("Celine"), dim2.attribute("Attic"))));
        Assertions.assertEquals(ConstraintType.IS_NOT, board.getForCoordinate(new Coordinate(dim1.attribute("Celine"), dim2.attribute("Boulevard"))));
        Assertions.assertEquals(ConstraintType.IS, board.getForCoordinate(new Coordinate(dim1.attribute("Celine"), dim2.attribute("Square"))));
    }

}