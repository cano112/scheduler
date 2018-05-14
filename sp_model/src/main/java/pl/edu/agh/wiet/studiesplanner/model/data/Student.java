package pl.edu.agh.wiet.studiesplanner.model.data;

/**
 * Created by Michał on 07.05.2018.
 */
public class Student extends Person {
    private final String id;
    private final String email;
    private final StudentsGroup group;

    public Student(String firstName, String surname, String id, String email, StudentsGroup group) {
        super(firstName, surname);
        this.id = id;
        this.email = email;
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public StudentsGroup getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Student student = (Student) o;

        if (!id.equals(student.id)) return false;
        if (!email.equals(student.email)) return false;
        return group.equals(student.group);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + group.hashCode();
        return result;
    }
}
