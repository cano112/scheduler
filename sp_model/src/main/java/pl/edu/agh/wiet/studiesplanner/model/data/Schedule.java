package pl.edu.agh.wiet.studiesplanner.model.data;

import java.util.List;

public class Schedule {
    private List<Convention> conventions;
    private List<StudentsGroup> studentsGroups;

    public void setConventions(List<Convention> conventions) {
        this.conventions = conventions;
    }

    public void setStudentsGroups(List<StudentsGroup> studentsGroups) {
        this.studentsGroups = studentsGroups;
    }

    public List<Convention> getConventions() {
        return conventions;
    }

    public List<StudentsGroup> getStudentsGroups() {
        return studentsGroups;
    }

    public List<Student> generateStudentList(Activity activity) {
        int groupNr = activity.getStudentsGroup();
        for (StudentsGroup studentsGroup: studentsGroups) {
            if (studentsGroup.getGroupNumber() == groupNr) {
                return  studentsGroup.getStudentsList();
            }
        }
        return null;
    }


}
