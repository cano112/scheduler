package pl.edu.agh.wiet.studiesplanner.parser;

import pl.edu.agh.wiet.studiesplanner.model.data.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Michał on 07.05.2018.
 */
public class StudentsSheetParser {

    public static List<Student> parse(List<List<Object>> downloadedSheet) {
        List<Student> studentsList = new ArrayList<>();

        HashMap<String, Integer> columnMap = new HashMap<>();
        columnMap.put("id", 0);
        columnMap.put("surname", 1);
        columnMap.put("name", 2);
        columnMap.put("groupNumber", 3);
        columnMap.put("email", 4);

        for(List<Object> row: downloadedSheet) {
            if(row.get(0).toString().equals("ID")) continue;

            studentsList.add(
                    new Student(
                        row.get(columnMap.get("name")).toString(),
                        row.get(columnMap.get("surname")).toString(),
                        row.get(columnMap.get("id")).toString(),
                        row.get(columnMap.get("email")).toString(),
                        Integer.parseInt(row.get(columnMap.get("groupNumber")).toString())
                    )
            );
        }

        return studentsList;
    }
}
