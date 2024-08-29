package pl.mantiscrab.zebrapuzzlesolver;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
class Constraint {
    private final Coordinate coordinate;
    private final ConstraintType constraintType;

    Constraint(Coordinate coordinate, ConstraintType constraintType) {
        this.coordinate = coordinate;
        this.constraintType = constraintType;
    }

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

    ConstraintType getConstraintType() {
        return constraintType;
    }

    Coordinate getCoordinate() {
        return coordinate;
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

    @Override
    public int hashCode() {
        return Objects.hash(coordinate, constraintType);
    }

    Builder replaceAttribute(Dimension.Attribute toBeReplaced) {
        List<Dimension.Attribute> attributes = new ArrayList<>(coordinate.getAttributes());
        attributes.remove(toBeReplaced);
        Dimension.Attribute attributeLeft = attributes.getFirst();
        return new Builder(attributeLeft, this.getConstraintType());
    }

    static class Builder  {
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
