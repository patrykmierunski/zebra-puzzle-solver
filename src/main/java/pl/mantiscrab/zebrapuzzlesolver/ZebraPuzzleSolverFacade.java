package pl.mantiscrab.zebrapuzzlesolver;

import org.mapstruct.factory.Mappers;
import pl.mantiscrab.zebrapuzzlesolver.dto.ConstraintDto;
import pl.mantiscrab.zebrapuzzlesolver.dto.DimensionDto;
import pl.mantiscrab.zebrapuzzlesolver.dto.SolutionDto;

import java.util.List;

class ZebraPuzzleSolverFacade {
    Adapter adapter = Mappers.getMapper( Adapter.class );

    List<SolutionDto> solve(List<DimensionDto> dimensions, List<ConstraintDto> constraints) {
        ZebraPuzzleMatrix matrix = ZebraPuzzleMatrix.ofDimensions(adapter.mapDimensions(dimensions));
        matrix.initialize(adapter.mapConstraints(constraints));
        matrix.solve();
        return adapter.mapSolutions(matrix.getSolutions());
    }

}
