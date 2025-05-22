package pl.mantiscrab.zebrapuzzlesolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

record Constraint(Coordinate coordinate, ConstraintType constraintType) {

    static Constraint is(Dimension.Attribute attribute1, Dimension.Attribute attribute2) {
        return Constraint.is(new Coordinate(attribute1, attribute2));
    }

    static Constraint is(Coordinate coordinate) {
        return new Constraint(coordinate, ConstraintType.IS);
    }

    static Constraint isNot(Dimension.Attribute attribute1, Dimension.Attribute attribute2) {
        return Constraint.isNot(new Coordinate(attribute1, attribute2));
    }

    static Constraint isNot(Coordinate coordinate) {
        return new Constraint(coordinate, ConstraintType.IS_NOT);
    }

    Dimension.Attribute getFirstAttribute() {
        return coordinate.getAttributes().getFirst();
    }

    Dimension.Attribute getSecondAttribute() {
        return coordinate.getAttributes().getLast();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Constraint that = (Constraint) o;
        return Objects.equals(coordinate, that.coordinate) && constraintType == that.constraintType;
    }

    Builder replaceAttribute(Dimension.Attribute toBeReplaced) {
        List<Dimension.Attribute> attributes = new ArrayList<>(coordinate.getAttributes());
        attributes.remove(toBeReplaced);
        Dimension.Attribute attributeLeft = attributes.getFirst();
        return new Builder(attributeLeft, this.constraintType());
    }

    static class Builder {
        private final Dimension.Attribute attribute;
        private final ConstraintType constraintType;

        public Builder(Dimension.Attribute attribute, ConstraintType constraintType) {
            this.attribute = attribute;
            this.constraintType = constraintType;
        }

        Constraint with(Dimension.Attribute newAttribute) {
            return new Constraint(new Coordinate(attribute, newAttribute), constraintType);
        }
    }
}
