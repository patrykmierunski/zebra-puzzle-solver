package pl.mantiscrab.zebrapuzzlesolver;

import java.util.List;

class ZebraPuzzleSolverFacade {
    static List<Solution> solve(List<Dimension> dimensions, List<Constraint> constraints) {
        ZebraPuzzleMatrix matrix = ZebraPuzzleMatrix.ofDimensions(dimensions);
        matrix.initialize(constraints);
        matrix.solve();
        return matrix.getSolutions();
    }

}
