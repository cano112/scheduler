package pl.edu.agh.wiet.studiesplanner.model.data;

import java.util.*;

public class Schedule {
    private final List<Convention> conventions;
    private final Set<StudentsGroup> studentsGroups;
    private final Set<Teacher> teachers;

    public Schedule() {
        this.conventions = new ArrayList<>();
        this.studentsGroups = new LinkedHashSet<>();
        this.teachers = new LinkedHashSet<>();
    }

    public void addConvention(Convention convention) {
        conventions.add(convention);
    }

    public void addStudentsGroup(StudentsGroup group) {
        studentsGroups.add(group);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public Optional<StudentsGroup> getStudentsGroupById(int id) {
        return studentsGroups
                .stream()
                .filter(group -> group.getId() == id)
                .findFirst();
    }

    public List<Convention> getConventions() {
        return conventions;
    }

    public Set<StudentsGroup> getStudentsGroups() {
        return studentsGroups;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    /* TODO: rework and put it into notifications module
    public String generateStudentListToText(Activity activity) {
        List<Student> studentList = generateStudentList(activity);
        if (studentList == null) {
            return "";
        }
        else {
            StringBuilder studentListText = new StringBuilder();
            studentListText.append(activity.getSubject());
            studentListText.append(", grupa: ").append(activity.getStudentsGroup());
            studentListText.append(", ").append(activity.getClassroom()).append("\n");
            for (int i = 0; i < studentList.size(); i++) {
                studentListText
                        .append(i+1)
                        .append(". ")
                        .append(studentList.get(i).getName())
                        .append(" ")
                        .append(studentList.get(i).getSurname())
                        .append(" ")
                        .append(studentList.get(i).getID())
                        .append(" ")
                        .append(studentList.get(i).getEmail())
                        .append("\n");
            }
            return studentListText.toString();
        }
    }
     */
}

