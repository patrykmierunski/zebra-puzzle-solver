package pl.mantiscrab.zebrapuzzlesolver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CoordinateTest {
    @Test
    void shouldCreateCoordinate() {
        //given
        Dimension.Attribute d1a1 = TestData.DIMENSION_1_ATTRIBUTE_1;
        Dimension.Attribute d1a2 = TestData.DIMENSION_2_ATTRIBUTE_1;

        //when
        Coordinate coordinate = new Coordinate(d1a1, d1a2);

        //then
        Assertions.assertTrue(coordinate.contains(d1a1));
        Assertions.assertTrue(coordinate.contains(d1a2));
    }

    @Test
    void shouldThrowExceptionWhenCreateCoordinateAndDimensionIsSame() {
        //given
        Dimension.Attribute a1 = TestData.DIMENSION_1_ATTRIBUTE_1;
        Dimension.Attribute a2 = TestData.DIMENSION_1_ATTRIBUTE_2;

        //when
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Coordinate(a1, a2));
    }

    @Test
    void shouldReturnTrueWhenCheckIfCoordinateWasCreatedForDimensionsWithGivenNames() {
        //given
        Dimension.Attribute d1a1 = TestData.DIMENSION_1_ATTRIBUTE_1;
        Dimension.Attribute d1a2 = TestData.DIMENSION_2_ATTRIBUTE_1;

        Coordinate coordinate = new Coordinate(d1a1, d1a2);

        //when-then
        Assertions.assertTrue(coordinate.isForDimensions(TestData.DIMENSION_NAME_1, TestData.DIMENSION_NAME_2));
    }

    @Test
    void shouldReturnFalseWhenCheckIfCoordinateWasCreatedForDimensionsWithGivenNames() {
        //given
        Dimension.Attribute d1a1 = TestData.DIMENSION_1_ATTRIBUTE_1;
        Dimension.Attribute d1a2 = TestData.DIMENSION_2_ATTRIBUTE_1;

        Coordinate coordinate = new Coordinate(d1a1, d1a2);

        //when-then
        Assertions.assertFalse(coordinate.isForDimensions(TestData.DIMENSION_NAME_1, TestData.DIMENSION_NAME_3));

    }

    @Test
    void shouldThrowExceptionWhenIfCoordinateWasCreatedForDimensionsAndDimensionsAreSame() {
        //given
        Dimension.Attribute d1a1 = TestData.DIMENSION_1_ATTRIBUTE_1;
        Dimension.Attribute d1a2 = TestData.DIMENSION_2_ATTRIBUTE_1;

        Coordinate coordinate = new Coordinate(d1a1, d1a2);

        //when-then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> coordinate.isForDimensions(TestData.DIMENSION_NAME_1, TestData.DIMENSION_NAME_1));

    }
}