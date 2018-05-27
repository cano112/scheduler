package pl.edu.agh.wiet.studiesplanner.model.data;

/**
 * Created by Michał on 07.05.2018.
 */
public class Student extends Person {
    private final String id;
    private final StudentsGroup group;

    public Student(String firstName, String surname, String id, String email, StudentsGroup group) {
        super(firstName, surname);
        this.id = id;
        this.group = group;
        setEmail(email);
    }

    public String getId() {
        return id;
    }

    public StudentsGroup getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (this.getFirstName() != null ? !this.getFirstName().equals(student.getFirstName()) : student.getFirstName() != null) return false;
        if (this.getSurname() != null ? !this.getSurname().equals(student.getSurname()) : student.getSurname()!= null) return false;
        if (this.getId() != null ? !this.getId().equals(student.getId()) : student.getId() != null) return false;
        return this.getEmail() != null ? this.getEmail().equals(student.getEmail()) : student.getEmail() == null;
    }

    @Override
    public int hashCode() {
        int result = this.getFirstName() != null ? this.getFirstName().hashCode() : 0;
        result = 31 * result + (this.getSurname() != null ? this.getSurname().hashCode() : 0);
        result = 31 * result + (this.getEmail() != null ? this.getEmail().hashCode() : 0);
        return result;
    }
}
