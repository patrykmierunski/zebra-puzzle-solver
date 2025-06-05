package pl.mantiscrab.zebrapuzzlesolver;

import pl.mantiscrab.zebrapuzzlesolver.dto.ConstraintDto;
import pl.mantiscrab.zebrapuzzlesolver.dto.DimensionDto;
import pl.mantiscrab.zebrapuzzlesolver.dto.SolutionDto;

import java.util.List;

class ZebraPuzzleSolverFacade {
    Mapper mapper = new Mapper();

    List<SolutionDto> solve(List<DimensionDto> dimensions, List<ConstraintDto> constraints) {
        ZebraPuzzleMatrix matrix = ZebraPuzzleMatrix.ofDimensions(mapper.mapDimensions(dimensions));
        matrix.initialize(mapper.mapConstraints(constraints));
        matrix.solve();
        return mapper.mapSolutions(matrix.getSolutions());
    }

}
