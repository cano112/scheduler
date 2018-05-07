package pl.edu.agh.wiet.studiesplanner.model.data;

/**
 * Created by Michał on 22.04.2018.
 */
public final class Activity {
    private final Teacher teacher;
    private final ActivityType type;
    private final Subject subject;
    private final Classroom classroom;
    private final StudentsGroup studentsGroup;

    public Activity(Teacher teacher, ActivityType type, Subject subject, Classroom classroom, StudentsGroup studentsGroup) {
        this.teacher = teacher;
        this.type = type;
        this.subject = subject;
        this.classroom = classroom;
        this.studentsGroup = studentsGroup;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public ActivityType getType() {
        return type;
    }

    public Subject getSubject() {
        return subject;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public StudentsGroup getStudentsGroup() {
        return studentsGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity activity = (Activity) o;

        if (!teacher.equals(activity.teacher)) return false;
        if (type != activity.type) return false;
        if (!subject.equals(activity.subject)) return false;
        if (!classroom.equals(activity.classroom)) return false;
        return studentsGroup.equals(activity.studentsGroup);
    }

    @Override
    public int hashCode() {
        int result = teacher.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + subject.hashCode();
        result = 31 * result + classroom.hashCode();
        result = 31 * result + studentsGroup.hashCode();
        return result;
    }
}
