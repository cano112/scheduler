package pl.edu.agh.wiet.studiesplanner.model.data;

import pl.edu.agh.wiet.studiesplanner.model.solver.ClassroomConflict;
import pl.edu.agh.wiet.studiesplanner.model.solver.Conflict;

import java.util.Set;

public final class Classroom implements ConflictCriterion {
    private String name;

    public Classroom(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Classroom classroom = (Classroom) o;

        return name.equals(classroom.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public Conflict createConflict(Set<TimeBlock> blocksWithConflict) {
        return new ClassroomConflict(blocksWithConflict, this);
    }
}
