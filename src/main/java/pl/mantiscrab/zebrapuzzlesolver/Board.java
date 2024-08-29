package pl.mantiscrab.zebrapuzzlesolver;

import com.github.freva.asciitable.AsciiTable;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Board implements Comparable<Board> {
    private final Set<Coordinate> coordinates;
    private final Set<Constraint> constraints;
    private final Dimension axis1;
    private final Dimension axis2;

    private Board(Dimension axis1, Dimension axis2, Set<Coordinate> coordinates) {
        List<Dimension> list = Stream.of(axis1, axis2).sorted().toList();
        this.axis1 = list.getFirst();
        this.axis2 = list.getLast();
        this.coordinates = coordinates;
        this.constraints = new HashSet<>();
    }

    static Board create(List<Dimension> dimensions) {
        if (dimensions.size() != 2) throw new IllegalArgumentException("dimensions.size() != 2");

        Dimension dimension1 = dimensions.getFirst();
        Dimension dimension2 = dimensions.getLast();

        return create(dimension1, dimension2);
    }

    static Board create(Dimension dimension1, Dimension dimension2) {
        Set<List<Dimension.Attribute>> lists = Sets.cartesianProduct(dimension1.getAttributes(), dimension2.getAttributes());

        Set<Coordinate> coordinates = lists.stream()
                .filter(cs -> cs.getFirst() != cs.getLast())
                .map(l -> new Coordinate(l.getFirst(), l.getLast()))
                .collect(Collectors.toCollection(TreeSet::new));

        return new Board(dimension1, dimension2, coordinates);
    }

    boolean isEligibleForConstraint(Constraint constraint) {
        Coordinate coordinate = constraint.getCoordinate();
        return coordinates.contains(coordinate);
    }

    void updateWithConstraint(Constraint constraint) {
        addConstraint(constraint);
        boolean wasAnythingChanged;
        do {
            wasAnythingChanged = harmonise();
        } while (wasAnythingChanged);
    }

    private void addConstraint(Constraint constraint) {
        addConstraintRaw(constraint);
        if (constraint.getConstraintType().equals(ConstraintType.IS)) {
            updateForIsConstraint(constraint.getCoordinate());
        }
    }

    private void addConstraintRaw(Constraint constraint) {
        Coordinate constraintCoordinate = constraint.getCoordinate();
        Optional<Constraint> existingConstraint = getConstraint(constraintCoordinate);
        if (existingConstraint.isPresent() && !existingConstraint.get().getConstraintType().equals(constraint.getConstraintType())) {
            throw new IllegalArgumentException("constraint already exists");
        }
        constraints.add(constraint);
    }

    Optional<Constraint> getConstraint(Coordinate coordinate) {
        return constraints.stream().filter(constraint -> constraint.getCoordinate().equals(coordinate)).findFirst();
    }

    private void updateForIsConstraint(Coordinate coordinate) {
        List<Dimension.Attribute> attributes = coordinate.getAttributes();
        for (Dimension.Attribute attribute : attributes) {
            Set<Coordinate> coordinatesForAttribute = getCoordinatesFor(attribute);
            coordinatesForAttribute.remove(coordinate);
            coordinatesForAttribute.forEach(coord -> addConstraintRaw(Constraint.isNot(coord)));
        }
    }

    private boolean harmonise() {
        boolean wasAnythingChangedForAxis1 = harmoniseForAxis(axis1);
        boolean wasAnythingChangedForAxis2 = harmoniseForAxis(axis2);
        return wasAnythingChangedForAxis1 || wasAnythingChangedForAxis2;
    }

    private boolean harmoniseForAxis(Dimension axis1) {
        boolean wasAnythingChanged = false;
        for (Dimension.Attribute attribute : axis1.getAttributes()) {
            List<Coordinate> possibleCoordinatesForAttribute = this.coordinates.stream()
                    .filter(coord -> coord.contains(attribute))
                    .toList();
            List<Coordinate> coordinatesOfExistingConstraintsForAttribute = this.constraints.stream()
                    .map(Constraint::getCoordinate)
                    .filter(coord -> coord.contains(attribute))
                    .toList();
            List<Coordinate> coordinatesWithoutConstraint = new ArrayList<>(possibleCoordinatesForAttribute);
            coordinatesWithoutConstraint.removeAll(coordinatesOfExistingConstraintsForAttribute);
            if (coordinatesWithoutConstraint.size() == 1) {
                Coordinate first = coordinatesWithoutConstraint.getFirst();
                addConstraint(Constraint.is(first));
                wasAnythingChanged = true;
            }
        }
        return wasAnythingChanged;
    }

    private Set<Coordinate> getCoordinatesFor(Dimension.Attribute attribute1) {
        return coordinates.stream().filter(c -> c.contains(attribute1)).collect(Collectors.toSet());
    }

    ConstraintType getForCoordinate(Coordinate coordinate) {
        return getConstraint(coordinate).orElseThrow().getConstraintType();
    }

    @Override
    public int compareTo(Board board) {
        if (this.axis1 == board.axis1) {
            return this.axis2.compareTo(board.axis2);
        }
        return this.axis1.compareTo(board.axis1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(axis1, board.axis1) && Objects.equals(axis2, board.axis2) || Objects.equals(axis1, board.axis2) && Objects.equals(axis2, board.axis1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(axis1, axis2);
    }

    @Override
    public String toString() {

        int tableSize = axis1.size() + 1;
        String[][] table = new String[tableSize][tableSize];

        for (int i = 0; i < tableSize; i++) {
            if (i == 0) {
                table[0][i] = "Field[" + axis1.getName() + ":" + axis2.getName() + "]";
                continue;
            }
            table[0][i] = axis1.getAttribute(i - 1).toVerticalString();
        }

        for (int i = 1; i < tableSize; i++) {
            table[i][0] = axis2.getAttribute(i - 1).toString();
        }

        for (int i = 1; i < tableSize; i++) {
            for (int j = 1; j < tableSize; j++) {
                table[i][j] = getConstraint(new Coordinate(axis2.getAttribute(i - 1), axis1.getAttribute(j - 1))).map(con->con.getConstraintType().toString()).orElse("");
            }
        }
        return AsciiTable.getTable(table);
    }

    Stream<Constraint> getIsConstraints() {
        return constraints.stream().filter(con -> con.getConstraintType().equals(ConstraintType.IS));
    }

    List<Constraint> getConstraints(Dimension.Attribute attribute) {
        return constraints.stream().filter(con -> con.getCoordinate().contains(attribute)).collect(Collectors.toList());
    }

    List<Constraint> getConstraints() {
        return constraints.stream().toList();
    }

    boolean containAttribute(Dimension.Attribute attribute) {
        DimensionName attributeDimensionName = attribute.getDimensionName();
        return axis1.getName().equals(attributeDimensionName)
                || axis2.getName().equals(attributeDimensionName);
    }
}

