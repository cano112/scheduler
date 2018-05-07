package pl.edu.agh.wiet.studiesplanner.model.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michał on 07.05.2018.
 */
public class StudentsGroup {
    private List<Student> studentsList;
    private int groupNumber;

    public StudentsGroup() {
        studentsList = new ArrayList<>();
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(List<Student> studentsList) {
        this.studentsList = studentsList;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }
}
