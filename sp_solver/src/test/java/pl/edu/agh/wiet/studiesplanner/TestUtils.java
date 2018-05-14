package pl.edu.agh.wiet.studiesplanner;

import pl.edu.agh.wiet.studiesplanner.model.data.Student;
import pl.edu.agh.wiet.studiesplanner.model.data.StudentsGroup;

import java.util.Random;
import java.util.stream.IntStream;

public class TestUtils {

    private static final String[] FIRST_NAMES = { "Jan", "Monika" };
    private static final String[] SURNAMES = { "Kowalski", "Nowak" };

    public static StudentsGroup createStudentsGroup(int id, int size) {
        StudentsGroup group = new StudentsGroup(id);
        IntStream
                .range(0, size)
                .forEach(i -> group.addStudent(getRandomStudent(String.valueOf(i), group)));
        return group;
    }

    private static Student getRandomStudent(String id, StudentsGroup group) {
        final String firstName = getRandomFirstName();
        final String surname = getRandomSurname();
        return new Student(firstName, surname, id, firstName + "." + surname + "@mail.com", group);
    }

    private static String getRandomFirstName() {
        Random random = new Random();
        return FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
    }

    private static String getRandomSurname() {
        Random random = new Random();
        return SURNAMES[random.nextInt(SURNAMES.length)];
    }



}
