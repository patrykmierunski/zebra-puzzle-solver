package pl.mantiscrab.zebrapuzzlesolver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConstraintTest {
    @Test
    void shouldReturnProperConstraintType() {
        Assertions.assertEquals(ConstraintType.IS, Constraint.is(TestData.DIMENSION_1_ATTRIBUTE_1, TestData.DIMENSION_2_ATTRIBUTE_1).getConstraintType());
        Assertions.assertEquals(ConstraintType.IS, Constraint.is(new Coordinate(TestData.DIMENSION_1_ATTRIBUTE_1, TestData.DIMENSION_2_ATTRIBUTE_1)).getConstraintType());
        Assertions.assertEquals(ConstraintType.IS_NOT, Constraint.isNot(TestData.DIMENSION_1_ATTRIBUTE_1, TestData.DIMENSION_2_ATTRIBUTE_1).getConstraintType());
        Assertions.assertEquals(ConstraintType.IS_NOT, Constraint.isNot(new Coordinate(TestData.DIMENSION_1_ATTRIBUTE_1, TestData.DIMENSION_2_ATTRIBUTE_1)).getConstraintType());
    }

}