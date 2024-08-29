package pl.mantiscrab.zebrapuzzlesolver;

import java.util.Objects;

record DimensionName(String name) implements Comparable<DimensionName>{

    static DimensionName of(String dimensionName) {
        return new DimensionName(dimensionName);
    }

    @Override
    public int compareTo(DimensionName that) {
        return name.compareTo(that.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DimensionName that = (DimensionName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
