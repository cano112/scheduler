package pl.edu.agh.wiet.studiesplanner.model.data;

import pl.edu.agh.wiet.studiesplanner.model.solver.Conflict;
import pl.edu.agh.wiet.studiesplanner.model.solver.StudentsGroupConflict;

import java.util.Set;
import java.util.*;

public final class StudentsGroup implements ConflictCriterion {
    private final String id;
    private final Set<Student> students;

    public StudentsGroup(String id) {
        this.id = id;
        this.students = new LinkedHashSet<>();
    }

    public String getId() {
        return id;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addStudents(Collection<Student> students) {
        this.students.addAll(students);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentsGroup group = (StudentsGroup) o;

        return id.equals(group.id) && students.equals(group.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public Conflict createConflict(Set<TimeBlock> blocksWithConflict) {
        return new StudentsGroupConflict(blocksWithConflict, this);
    }
}
