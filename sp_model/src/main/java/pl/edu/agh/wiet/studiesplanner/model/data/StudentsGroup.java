package pl.edu.agh.wiet.studiesplanner.model.data;

import java.util.LinkedList;
import java.util.List;

public final class StudentsGroup {
    private final int id;
    private final List<Student> students;

    public StudentsGroup(int id) {
        this.id = id;
        this.students = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentsGroup group = (StudentsGroup) o;

        return id == group.id && students.equals(group.students);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + students.hashCode();
        return result;
    }
}
