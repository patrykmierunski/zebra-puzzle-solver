package pl.mantiscrab.zebrapuzzlesolver;

class TestData {
    public static Dimension DIMENSION_1 = Dimension.builder().name("d1").attributes("d1a1", "d1a2").build();
    public static DimensionName DIMENSION_NAME_1 = DimensionName.of("d1");
    public static Dimension.Attribute DIMENSION_1_ATTRIBUTE_1 = DIMENSION_1.attribute("d1a1");
    public static Dimension.Attribute DIMENSION_1_ATTRIBUTE_2 = DIMENSION_1.attribute("d1a2");
    public static Dimension DIMENSION_2 = Dimension.builder().name("d2").attributes("d2a1", "d2a2").build();
    public static DimensionName DIMENSION_NAME_2 = DimensionName.of("d2");
    public static Dimension.Attribute DIMENSION_2_ATTRIBUTE_1 = DIMENSION_2.attribute("d2a1");
    public static Dimension.Attribute DIMENSION_2_ATTRIBUTE_2 = DIMENSION_2.attribute("d2a2");
    public static DimensionName DIMENSION_NAME_3 = DimensionName.of("d3");
}
