package pl.mantiscrab.zebrapuzzlesolver;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

class Solution {
    private final Set<Dimension.Attribute> attributes;

    private Solution(Set<Dimension.Attribute> attributes) {
        this.attributes = Collections.unmodifiableSet(attributes);
    }

    static Solution of(Set<Dimension.Attribute> attributes) {
        return new Solution(attributes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return Objects.equals(attributes, solution.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(attributes);
    }

    @Override
    public String toString() {
        return "Solution{" +
                "attributes=" + attributes +
                '}';
    }
}
