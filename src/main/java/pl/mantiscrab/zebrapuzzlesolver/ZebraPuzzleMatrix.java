package pl.mantiscrab.zebrapuzzlesolver;

import com.google.common.collect.Sets;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ZebraPuzzleMatrix {

    private final Set<Board> boards;
    private final List<Dimension> dimensions;

    private ZebraPuzzleMatrix(Set<Board> boards, List<Dimension> dimensions) {
        this.boards = Collections.unmodifiableSet(boards);
        this.dimensions = Collections.unmodifiableList(dimensions);
    }

    static ZebraPuzzleMatrix ofDimensions(List<Dimension> dimensions) {
        HashSet<Dimension> dimensionsSet = new HashSet<>(dimensions);
        Set<List<Dimension>> rawFields = Sets.cartesianProduct(dimensionsSet, dimensionsSet);

        Set<Board> boards = rawFields.stream().filter(dN -> dN.getFirst() != dN.getLast())
                .map(Board::create)
                .collect(Collectors.toCollection(TreeSet::new));

        return new ZebraPuzzleMatrix(boards, dimensions);
    }

    void initialize(List<Constraint> constraints) {
        constraints.forEach(constraint -> getFieldForConstraint(constraint).updateWithConstraint(constraint));
    }

    void solve() {
        boolean wasUpdated;
         do {
             int before = getTotalNumberOfConstraints();

             List<Constraint> isConstraints = boards.stream().flatMap(Board::getIsConstraints).toList();
             isConstraints.forEach(this::synchronize);

             int after = getTotalNumberOfConstraints();

             wasUpdated = before != after;
         } while (wasUpdated);
    }

    private Board getFieldForConstraint(Constraint constraint) {
        return boards.stream().filter(f -> f.isEligibleForConstraint(constraint)).findFirst().orElseThrow();
    }

    private int getTotalNumberOfConstraints() {
        return boards.stream().map(b -> b.getConstraints().size()).reduce(0, Integer::sum);
    }

    private void synchronize(Constraint constraint) {
        Dimension.Attribute first = constraint.getFirstAttribute();
        Dimension.Attribute second = constraint.getSecondAttribute();

        copyConstraints(first, second, constraint);
        copyConstraints(second, first, constraint);
    }

    private void copyConstraints(Dimension.Attribute attribute1, Dimension.Attribute attribute2, Constraint constraint) {
        List<Constraint> constraintsFromSameField = getConstraintsFromSameField(constraint);

        List<Constraint> firstAttributeConstraint = getConstraintsForAttribute(attribute1);
        firstAttributeConstraint.removeAll(constraintsFromSameField);
        List<Constraint> secondAttributeConstraintsMappedFromFirsts = firstAttributeConstraint.stream()
                .map(con -> con.replaceAttribute(attribute1).with(attribute2)).toList();
        secondAttributeConstraintsMappedFromFirsts.forEach(con -> getFieldForConstraint(con).updateWithConstraint(con));
    }

    private List<Constraint> getConstraintsFromSameField(Constraint constraint) {
        return getFieldForConstraint(constraint).getConstraints();
    }

    private List<Constraint> getConstraintsForAttribute(Dimension.Attribute attribute) {
        return new ArrayList<>(getConstraintStream(attribute)
                .toList());
    }

    private Stream<Constraint> getConstraintStream(Dimension.Attribute attribute) {
        return boards.stream()
                .filter(f -> f.containAttribute(attribute))
                .flatMap(b -> b.getConstraints(attribute).stream());
    }

    private List<Constraint> getIsConstraintsForAttribute(Dimension.Attribute attribute) {
        return new ArrayList<>(getConstraintStream(attribute)
                .filter(constraint -> constraint.getConstraintType().equals(ConstraintType.IS))
                .toList());
    }

    List<Solution> getSolutions() {
        List<Solution> solutions = new ArrayList<>();
        Set<Dimension.Attribute> attributes = dimensions.getFirst().getAttributes();
        for (Dimension.Attribute attribute : attributes) {
            Set<Dimension.Attribute> solutionAttributes = new HashSet<>();
            List<Constraint> constraints = getIsConstraintsForAttribute(attribute);
            for (Constraint constraint : constraints) {
                solutionAttributes.add(constraint.getFirstAttribute());
                solutionAttributes.add(constraint.getSecondAttribute());
            }
            solutions.add(Solution.of(solutionAttributes));
        }
        return solutions;
    }

    @Override
    public String toString() {
        String[] array = boards.stream().map(Board::toString).toArray(String[]::new);
        String join = String.join(System.lineSeparator(), array);
        return "ZebraPuzzleSolverMatrix{" + System.lineSeparator()
                + join + System.lineSeparator()
                + "}";
    }
}
