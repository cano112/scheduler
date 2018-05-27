package pl.edu.agh.wiet.studiesplanner.parser.services;

import pl.edu.agh.wiet.studiesplanner.model.data.Schedule;
import pl.edu.agh.wiet.studiesplanner.model.data.Teacher;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Michał on 27.05.2018.
 */
public class TeachersSheetParser implements SheetParser {

    public void parse(List<List<Object>> downloadedSheet, Schedule model) {
        HashMap<String, Integer> columnMap = new HashMap<>();
        columnMap.put("surname", 0);
        columnMap.put("name", 1);
        columnMap.put("email", 2);

        for(List<Object> row: downloadedSheet) {
            if(row.get(0).toString().equals("Nazwisko")) continue;

            model.addTeacher(
                    new Teacher(
                            row.get(columnMap.get("name")).toString(),
                            row.get(columnMap.get("surname")).toString(),
                            row.get(columnMap.get("email")).toString()));
        }
    }
}
