package pl.edu.agh.wiet.studiesplanner.model.data;

/**
 * Created by Michał on 07.05.2018.
 */
public class Student {
    private String name;
    private String surname;
    private String ID;
    private String email;
    private int groupNumber;

    public Student(String name, String surname, String ID, String email, int groupNumber) {
        this.name = name;
        this.surname = surname;
        this.ID = ID;
        this.email = email;
        this.groupNumber = groupNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }
}
