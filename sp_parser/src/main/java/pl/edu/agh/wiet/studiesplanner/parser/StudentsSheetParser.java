package pl.edu.agh.wiet.studiesplanner.parser;

import pl.edu.agh.wiet.studiesplanner.model.data.Student;
import pl.edu.agh.wiet.studiesplanner.model.data.StudentsGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Michał on 07.05.2018.
 */
public class StudentsSheetParser {

    public static List<StudentsGroup> parse(List<List<Object>> downloadedSheet) {
        List<StudentsGroup> studentsGroups = new ArrayList<>();

        HashMap<String, Integer> columnMap = new HashMap<>();
        columnMap.put("id", 0);
        columnMap.put("surname", 1);
        columnMap.put("name", 2);
        columnMap.put("groupNumber", 3);
        columnMap.put("email", 4);



        for(List<Object> row: downloadedSheet) {
            if(row.get(0).toString().equals("ID")) continue;

            int groupNumber = Integer.parseInt(row.get(columnMap.get("groupNumber")).toString());
            StudentsGroup studentsGroup = getStudentsGroupIfExist(studentsGroups, groupNumber);
            if(studentsGroup == null) {
                studentsGroup = new StudentsGroup();
                studentsGroup.setGroupNumber(groupNumber);
                studentsGroups.add(studentsGroup);
            }

            studentsGroup.getStudentsList().add(
                    new Student(
                        row.get(columnMap.get("name")).toString(),
                        row.get(columnMap.get("surname")).toString(),
                        row.get(columnMap.get("id")).toString(),
                        row.get(columnMap.get("email")).toString(),
                        groupNumber
                    )
            );
        }

        return studentsGroups;
    }

    private static StudentsGroup getStudentsGroupIfExist(List<StudentsGroup> studentsGroups, int groupNumber) {
        for(StudentsGroup studentsGroup: studentsGroups) {
            if(studentsGroup.getGroupNumber() == groupNumber) return studentsGroup;
        }
        return null;
    }
}
