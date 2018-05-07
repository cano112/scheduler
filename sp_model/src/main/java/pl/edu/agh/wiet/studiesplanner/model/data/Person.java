package pl.edu.agh.wiet.studiesplanner.model.data;

public abstract class Person {
    private final String firstName;
    private final String surname;

    protected Person(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    public String getFullName() {
        return firstName + " " + surname;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!firstName.equals(person.firstName)) return false;
        return surname.equals(person.surname);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + surname.hashCode();
        return result;
    }
}
