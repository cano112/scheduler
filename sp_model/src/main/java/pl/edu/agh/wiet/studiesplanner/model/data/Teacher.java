package pl.edu.agh.wiet.studiesplanner.model.data;

import pl.edu.agh.wiet.studiesplanner.model.solver.Conflict;
import pl.edu.agh.wiet.studiesplanner.model.solver.TeacherConflict;

import java.util.Set;

public class Teacher extends Person implements ConflictCriterion {
    public Teacher(String firstName, String surname) {
        super(firstName, surname);
    }

    public Teacher(String firstName, String surname, String email) {
        super(firstName, surname, email);
    }

    @Override
    public Conflict createConflict(Set<TimeBlock> blocksWithConflict) {
        return new TeacherConflict(blocksWithConflict, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (this.getFirstName() != null ? !this.getFirstName().equals(person.getFirstName()) : person.getFirstName() != null) return false;
        if (this.getSurname() != null ? !this.getSurname().equals(person.getSurname()) : person.getSurname()!= null) return false;
        return true;
    }
}
