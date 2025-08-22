package pl.mantiscrab.zebrapuzzlesolver;

import org.springframework.stereotype.Service;
import pl.mantiscrab.zebrapuzzlesolver.dto.ConstraintDto;
import pl.mantiscrab.zebrapuzzlesolver.dto.DimensionDto;
import pl.mantiscrab.zebrapuzzlesolver.dto.SolutionDto;

import java.util.List;

@Service
public class ZebraPuzzleSolverFacade {
    Mapper mapper = new Mapper();

    public List<SolutionDto> solve(List<DimensionDto> dimensions, List<ConstraintDto> constraints) {
        ZebraPuzzleMatrix matrix = ZebraPuzzleMatrix.ofDimensions(mapper.mapDimensions(dimensions));
        matrix.initialize(mapper.mapConstraints(constraints));
        matrix.solve();
        return mapper.mapSolutions(matrix.getSolutions());
    }

}
