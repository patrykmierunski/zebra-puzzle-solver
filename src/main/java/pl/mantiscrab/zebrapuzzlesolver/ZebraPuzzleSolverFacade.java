package pl.mantiscrab.zebrapuzzlesolver;

import org.mapstruct.factory.Mappers;
import pl.mantiscrab.zebrapuzzlesolver.dto.ConstraintDto;
import pl.mantiscrab.zebrapuzzlesolver.dto.DimensionDto;
import pl.mantiscrab.zebrapuzzlesolver.dto.SolutionDto;

import java.util.List;

class ZebraPuzzleSolverFacade {
    ConstraintMapper constraintMapper = Mappers.getMapper(ConstraintMapper.class);
    DimensionMapper dimensionMapper = Mappers.getMapper(DimensionMapper.class);
    SolutionMapper solutionMapper = Mappers.getMapper(SolutionMapper.class);

    List<SolutionDto> solve(List<DimensionDto> dimensions, List<ConstraintDto> constraints) {
        ZebraPuzzleMatrix matrix = ZebraPuzzleMatrix.ofDimensions(dimensionMapper.map(dimensions));
        matrix.initialize(constraintMapper.map(constraints));
        matrix.solve();
        return solutionMapper.map(matrix.getSolutions());
    }

}
