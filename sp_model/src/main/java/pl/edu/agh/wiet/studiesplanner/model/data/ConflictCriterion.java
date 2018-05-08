package pl.edu.agh.wiet.studiesplanner.model.data;

import pl.edu.agh.wiet.studiesplanner.model.solver.Conflict;

import java.util.Set;

public interface ConflictCriterion {
    Conflict createConflict(Set<TimeBlock> blocksWithConflict);
}
