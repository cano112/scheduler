package pl.edu.agh.wiet.studiesplanner.model.data;

import pl.edu.agh.wiet.studiesplanner.model.solver.Conflict;
import pl.edu.agh.wiet.studiesplanner.model.solver.TeacherConflict;

import java.util.Set;

public class Teacher extends Person implements ConflictCriterion {
    public Teacher(String firstName, String surname) {
        super(firstName, surname);
    }

    @Override
    public Conflict createConflict(Set<TimeBlock> blocksWithConflict) {
        return new TeacherConflict(blocksWithConflict, this);
    }
}
