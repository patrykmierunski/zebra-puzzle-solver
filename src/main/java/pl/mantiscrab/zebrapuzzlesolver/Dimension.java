package pl.mantiscrab.zebrapuzzlesolver;

import java.util.*;
import java.util.stream.Collectors;

class Dimension implements Comparable<Dimension> {

    private final DimensionName name;
    private final Set<Attribute> attributes;

    private Dimension(DimensionName name, Set<Attribute> attributes) {
        this.name = Objects.requireNonNull(name);
        this.attributes = Collections.unmodifiableSet(attributes);
    }

    DimensionName getName() {
        return name;
    }

    Set<Attribute> getAttributes() {
        return attributes;
    }

    Attribute attribute(String attributeName) {
        return attributes.stream().filter(
                a -> a.attributeName().name().equals(attributeName)).findFirst().orElseThrow(() ->
                new RuntimeException(String.format("Dimension %s doesn't contain attribute %s", this.name.name(), attributeName)));
    }

    int size() {
        return getAttributes().size();
    }

    Attribute getAttribute(int i) {
        return new ArrayList<>(getAttributes()).get(i);
    }

    @Override
    public int compareTo(Dimension that) {
        return this.getName().compareTo(that.getName());
    }

    public static Dimension.Builder builder() {
        return new Dimension.Builder();
    }

    public static class Builder {
        private DimensionName name;
        private AttributeName[] attributeNames;

        public Builder name(String name) {
            this.name = DimensionName.of(name);
            return this;
        }

        public Builder name(DimensionName name) {
            this.name = name;
            return this;
        }

        public Builder attributes(String... attributeNames) {
            this.attributeNames = Arrays.stream(attributeNames).map(AttributeName::new).toArray(AttributeName[]::new);
            return this;
        }

        public Builder attributes(List<AttributeName> attributeNames) {
            this.attributeNames = attributeNames.toArray(AttributeName[]::new);
            return this;
        }

        public Dimension build() {
            TreeSet<Attribute> attributes = Arrays.stream(attributeNames)
                    .map(s -> new Attribute(this.name, s))
                    .collect(Collectors.toCollection(TreeSet::new));
            Set<Attribute> attributeNamesUnmodifiableSet = Collections.unmodifiableSet(attributes);
            return new Dimension(this.name, attributeNamesUnmodifiableSet);
        }
    }

    record Attribute(DimensionName dimensionName, AttributeName attributeName) implements Comparable<Attribute> {

        Constraint is(Attribute otherAttribute) {
            return Constraint.is(this, otherAttribute);
        }

        Constraint isNot(Attribute otherAttribute) {
            return Constraint.isNot(this, otherAttribute);
        }

        @Override
        public int compareTo(Attribute attribute) {
            int dimensionNameComparisonResult = this.dimensionName().compareTo(attribute.dimensionName());
            if (dimensionNameComparisonResult == 0) {
                return this.attributeName().name().compareTo(attribute.attributeName().name());
            }
            return dimensionNameComparisonResult;
        }

        @Override
        public String toString() {
            return dimensionName + ":" + attributeName;
        }

        public String toVerticalString() {
            return String.join(System.lineSeparator(), (dimensionName + ":" + attributeName).split(""));
        }
    }
}
