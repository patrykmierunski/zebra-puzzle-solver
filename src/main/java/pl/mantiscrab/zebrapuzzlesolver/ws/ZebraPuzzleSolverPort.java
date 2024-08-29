package pl.mantiscrab.zebrapuzzlesolver.ws;

import pl.mantiscrab.zebra_puzzle_solver.api.ZebraPuzzleSolverPortType;
import pl.mantiscrab.zebra_puzzle_solver.types.SolveZebraPuzzleRequest;
import pl.mantiscrab.zebra_puzzle_solver.types.SolveZebraPuzzleResponse;

public class ZebraPuzzleSolverPort implements ZebraPuzzleSolverPortType {

    @Override
    public SolveZebraPuzzleResponse solveZebraPuzzle(SolveZebraPuzzleRequest solveZebraPuzzleRequest) {
        return new SolveZebraPuzzleResponse();
    }
}
