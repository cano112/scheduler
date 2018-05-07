package pl.edu.agh.wiet.studiesplanner.model.data;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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

    public String generateStudentListToText(Activity activity) {
        List<Student> studentList = generateStudentList(activity);
        if (studentList == null) {
            return "";
        }
        else {
            StringBuilder studentListText = new StringBuilder();
            studentListText.append(activity.getSubject());
            studentListText.append(", grupa: ").append(activity.getStudentsGroup());
            studentListText.append(", ").append(activity.getClassroom()).append("\n");
            for (int i = 0; i < studentList.size(); i++) {
                studentListText
                        .append(i+1)
                        .append(". ")
                        .append(studentList.get(i).getName())
                        .append(" ")
                        .append(studentList.get(i).getSurname())
                        .append(" ")
                        .append(studentList.get(i).getID())
                        .append(" ")
                        .append(studentList.get(i).getEmail())
                        .append("\n");
            }
            return studentListText.toString();
        }
    }


}
