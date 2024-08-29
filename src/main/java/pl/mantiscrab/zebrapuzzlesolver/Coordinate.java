package pl.mantiscrab.zebrapuzzlesolver;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

class Coordinate implements Comparable<Coordinate> {
    private final List<Dimension.Attribute> attributes;

    Coordinate(Dimension.Attribute attribute1, Dimension.Attribute attribute2) {
        if (attribute1 == null || attribute2 == null)
            throw new NullPointerException();
        if(attribute1.getDimensionName().equals(attribute2.getDimensionName()))
            throw new IllegalArgumentException(String.format("Attributes cannot within same dimension{%s}", attribute1.getDimensionName()));
        this.attributes = Stream.of(attribute1, attribute2).sorted().toList();
    }

    boolean contains(Dimension.Attribute attribute1) {
        return attributes.contains(attribute1);
    }

    boolean isForDimensions(DimensionName name1, DimensionName name2) {
        if (name1.equals(name2)) {
            throw new IllegalArgumentException("Dimensions cannot be the same");
        }
        List<DimensionName> dimensionNames = attributes.stream().map(Dimension.Attribute::getDimensionName).toList();
        return dimensionNames.containsAll(List.of(name1, name2));
    }

    List<Dimension.Attribute> getAttributes() {
        return attributes;
    }

    @Override
    public int compareTo(Coordinate coordinate) {
        for (int i = 0; i < attributes.size(); i++) {
            int compareTo = this.attributes.get(i).compareTo(coordinate.attributes.get(i));
            if (compareTo != 0)
                return compareTo;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Objects.equals(attributes, that.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(attributes);
    }

    @Override
    public String toString() {
        return "c{" + attributes.getFirst() + ":" + attributes.getLast() + '}';
    }
}
