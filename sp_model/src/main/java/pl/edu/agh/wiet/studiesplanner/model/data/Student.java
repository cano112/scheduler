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

}
