package pl.edu.agh.wiet.studiesplanner.model.data;

import java.util.*;

public class Schedule {
    private final List<Convention> conventions;
    private final List<StudentsGroup> studentsGroups;
    private final List<Teacher> teachers;

    public Schedule() {
        this.conventions = new LinkedList<>();
        this.studentsGroups = new LinkedList<>();
        this.teachers = new LinkedList<>();
    }

    public void addConvention(Convention convention) {
        if(conventions.contains(convention)) {
            conventions
                    .stream()
                    .filter(conv -> conv.equals(convention))
                    .forEach(conv -> conv.addTimeBlocks(convention.getTimeBlocks()));
        } else {
            conventions.add(convention);
        }
    }

    public void addStudentsGroup(StudentsGroup group) {
        if(studentsGroups.contains(group)) {
            studentsGroups
                    .stream()
                    .filter(g -> g.equals(group))
                    .forEach(g -> g.addStudents(group.getStudents()));
        } else {
            studentsGroups.add(group);
        }
    }

    public void addTeacher(Teacher teacher) {
        if(teachers.contains(teacher)) {
            teachers.stream()
                    .filter(t -> t.equals(teacher))
                    .forEach(t -> t.setEmail(teacher.getEmail()));
        } else {
            teachers.add(teacher);
        }
    }

    public Optional<StudentsGroup> getStudentsGroupById(String id) {
        return studentsGroups
                .stream()
                .filter(group -> group.getId().equals(id))
                .findFirst();
    }

    public List<Convention> getConventions() {
        return conventions;
    }

    public List<StudentsGroup> getStudentsGroups() {
        return studentsGroups;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void addConventions(Collection<? extends Convention> conventions) {
        conventions.forEach(this::addConvention);
    }

    public void addStudentGroups(Collection<? extends StudentsGroup> studentsGroups) {
        studentsGroups.forEach(this::addStudentsGroup);
    }

    public void addTeachers(Collection<? extends Teacher> teachers) {
        teachers.forEach(this::addTeacher);
    }
}

