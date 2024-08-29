package pl.mantiscrab.zebrapuzzlesolver;

enum ConstraintType {
    IS("&"), IS_NOT("X");

    private final String visualisation;

    ConstraintType(String visualisation) {
        this.visualisation = visualisation;
    }

    @Override
    public String toString() {
        return visualisation;
    }
}
