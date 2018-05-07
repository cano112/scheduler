package pl.edu.agh.wiet.studiesplanner.model.data;

/**
 * Created by Michał on 22.04.2018.
 */
public class Activity {
    private String teacher;
    private ActivityType type;
    private String subject;
    private String classroom;
    private int studentsGroup;

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public int getStudentsGroup() {
        return studentsGroup;
    }

    public void setStudentsGroup(int studentsGroup) {
        this.studentsGroup = studentsGroup;
    }

}
