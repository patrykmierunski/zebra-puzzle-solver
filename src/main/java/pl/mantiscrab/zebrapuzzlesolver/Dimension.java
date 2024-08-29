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
                a -> a.getAttributeName().name().equals(attributeName)).findFirst().orElseThrow(() ->
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

    static Dimension.Builder builder() {
        return new Dimension.Builder();
    }

    static class Builder {
        private DimensionName name;
        private String[] attributeNames;

        public Builder name(String name) {
            this.name = DimensionName.of(name);
            return this;
        }

        public Builder attributes(String... attributeNames) {
            this.attributeNames = attributeNames;
            return this;
        }

        public Dimension build() {
            TreeSet<Attribute> attributes = Arrays.stream(attributeNames)
                    .map(s -> new Attribute(this.name, new AttributeName(s)))
                    .collect(Collectors.toCollection(TreeSet::new));
            Set<Attribute> attributeNamesUnmodifiableSet = Collections.unmodifiableSet(attributes);
            return new Dimension(this.name, attributeNamesUnmodifiableSet);
        }
    }

    static class Attribute implements Comparable<Attribute> {

        private final DimensionName dimensionName;
        private final AttributeName attributeName;

        Attribute(DimensionName dimensionName, AttributeName attributeName) {
            this.dimensionName = dimensionName;
            this.attributeName = attributeName;
        }

        DimensionName getDimensionName() {
            return dimensionName;
        }

        AttributeName getAttributeName() {
            return attributeName;
        }

        Constraint is(Attribute otherAttribute) {
            return Constraint.is(this, otherAttribute);
        }

        Constraint isNot(Attribute otherAttribute) {
            return Constraint.isNot(this, otherAttribute);
        }

        @Override
        public int compareTo(Attribute attribute) {
            int dimensionNameComparisonResult = this.getDimensionName().compareTo(attribute.getDimensionName());
            if (dimensionNameComparisonResult == 0) {
                return this.getAttributeName().name().compareTo(attribute.getAttributeName().name());
            }
            return dimensionNameComparisonResult;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Attribute attribute = (Attribute) o;
            return Objects.equals(dimensionName, attribute.dimensionName) && Objects.equals(attributeName, attribute.attributeName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(dimensionName, attributeName);
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
