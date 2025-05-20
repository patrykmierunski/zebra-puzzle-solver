package pl.mantiscrab.zebrapuzzlesolver;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.mantiscrab.zebrapuzzlesolver.dto.ConstraintDto;

import java.util.List;

@Mapper(uses = {CoordinateMapper.class})
public abstract class ConstraintMapper {
    @Mapping(target = "constraintType", source = "constraint")
    abstract Constraint map(ConstraintDto constraintDto);

    abstract List<Constraint> map(List<ConstraintDto> constraintDto);

    ConstraintType map(boolean isConstraint) {
        return isConstraint ? ConstraintType.IS : ConstraintType.IS_NOT;
    }
}
