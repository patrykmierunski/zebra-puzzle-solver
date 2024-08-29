package pl.mantiscrab.zebrapuzzlesolver;

import java.util.Objects;

record AttributeName(String name) implements Comparable<AttributeName>{

    @Override
    public int compareTo(AttributeName that) {
        return this.name.compareTo(that.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttributeName that = (AttributeName) o;
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
