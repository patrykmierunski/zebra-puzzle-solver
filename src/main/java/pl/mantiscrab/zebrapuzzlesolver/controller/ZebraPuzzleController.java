package pl.mantiscrab.zebrapuzzlesolver.controller;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.mantiscrab.zebrapuzzlesolver.ZebraPuzzleSolverFacade;
import pl.mantiscrab.zebrapuzzlesolver.dto.SolutionDto;
import pl.zebrapuzzlesolver.Solutions;
import pl.zebrapuzzlesolver.SolveRequest;
import pl.zebrapuzzlesolver.ZebraPuzzleApi;

import java.util.List;

@RestController
@AllArgsConstructor
class ZebraPuzzleController implements ZebraPuzzleApi {
    private final ZebraPuzzleSolverFacade facade;
    private final RestMapper restMapper = Mappers.getMapper(RestMapper.class);

    @Override
    public ResponseEntity<Solutions> solveZebraPuzzle(SolveRequest solveRequest) {
        List<SolutionDto> solutions = facade.solve(restMapper.mapDimensions(solveRequest.getDimensions().getDimensions()), restMapper.mapConstraints(solveRequest.getConstraints()));
        return ResponseEntity.ok((restMapper.mapSolutiaon(solutions)));
    }
}