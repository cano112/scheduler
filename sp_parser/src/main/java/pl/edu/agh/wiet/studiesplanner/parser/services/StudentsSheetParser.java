package pl.edu.agh.wiet.studiesplanner.parser.services;

import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.model.data.Schedule;
import pl.edu.agh.wiet.studiesplanner.model.data.Student;
import pl.edu.agh.wiet.studiesplanner.model.data.StudentsGroup;
import pl.edu.agh.wiet.studiesplanner.parser.exceptions.SheetParseException;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Michał on 07.05.2018.
 */

@Service
public class StudentsSheetParser implements SheetParser {

    public void parse(List<List<Object>> downloadedSheet, Schedule model) {
        HashMap<String, Integer> columnMap = new HashMap<>();
        columnMap.put("id", 0);
        columnMap.put("surname", 1);
        columnMap.put("name", 2);
        columnMap.put("groupNumber", 3);
        columnMap.put("email", 4);

        for(List<Object> row: downloadedSheet) {
            if(row.get(0).toString().equals("ID")) continue;

            int groupNumber = Integer.parseInt(row.get(columnMap.get("groupNumber")).toString());
            StudentsGroup group = model.getStudentsGroupById(groupNumber).orElseGet(() -> {
                StudentsGroup g = new StudentsGroup(groupNumber);
                model.addStudentsGroup(g);
                return g;
            });
            group.addStudent(new Student(
                    row.get(columnMap.get("name")).toString(),
                    row.get(columnMap.get("surname")).toString(),
                    row.get(columnMap.get("id")).toString(),
                    row.get(columnMap.get("email")).toString(),
                    group
            ));
        }
    }
}
