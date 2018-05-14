package pl.edu.agh.wiet.studiesplanner.parser;

import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.model.data.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Michał on 22.04.2018.
 */

@Service
public class ScheduleSheetParser {

    public Schedule parse(List<List<Object>> downloadedSheet) {
        Schedule model = new Schedule();
        List<Object> headerRow = null;
        List<TimeBlock> timeBlocksWorkingList = new ArrayList<>();
        int conventionNumber = -1;

        for (List<Object> row : downloadedSheet) {
            if(isHeader(row)) {
                headerRow = row;
                continue;
            }
            if(isNewConvention(row)) {
                if(!timeBlocksWorkingList.isEmpty() && conventionNumber != -1) {
                    model.addConvention(new Convention(conventionNumber, timeBlocksWorkingList));
                }
                conventionNumber = Integer.parseInt(row.get(0).toString());
                timeBlocksWorkingList = new ArrayList<>();
            }
            timeBlocksWorkingList.add(parseRow(row, headerRow, model));
        }
        if(!timeBlocksWorkingList.isEmpty() && conventionNumber != -1) {
            model.addConvention(new Convention(conventionNumber, timeBlocksWorkingList));
        }

        return model;
    }

    private TimeBlock parseRow(List<Object> row, List<Object> headerRow, Schedule model) {
        String[] hours = row.get(2).toString().replaceAll("\\s+", "").split("-");

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        LocalDate date = LocalDate.parse(row.get(1).toString().replaceAll("\\s+", ""), dateFormatter);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
        LocalDateTime startTime = LocalDateTime.of(date, LocalTime.parse(hours[0], timeFormatter));
        LocalDateTime endTime = LocalDateTime.of(date, LocalTime.parse(hours[1], timeFormatter));

        List<Activity> activityWorkingList = new ArrayList<>();
        for(int i = 3; i < row.size(); i += 3) {
            Activity activity = parseActivity(row, i, headerRow, model);
            if(activity != null) activityWorkingList.add(activity);
        }

        return new TimeBlock(startTime, endTime, activityWorkingList);
    }

    private Activity parseActivity(List<Object> row, int i, List<Object> headerRow, Schedule model) {
        if(!validateActivity(row, i)) return null;

        String[] subjectAndClassroom = row.get(i).toString().split(", ");
        String[] headerContent = headerRow.get(i).toString().split(" ");

        Subject subject = new Subject(subjectAndClassroom[0]);
        Classroom classroom = new Classroom(subjectAndClassroom[1]);
        ActivityType type = ActivityType.valueOf(row.get(i+1).toString().replaceAll("\\s+", ""));

        String[] name = row.get(i+2).toString().split(" ");
        Teacher teacher = new Teacher(name[0], name[1]);
        model.addTeacher(teacher);
        StudentsGroup group = new StudentsGroup(Integer.parseInt(headerContent[1]));
        model.addStudentsGroup(group);

        return new Activity(teacher, type, subject, classroom, group);
    }

    private boolean isHeader(List<Object> row) {
        return !(row.get(0).toString().equals("") || row.get(0).toString().matches("\\d+"));
    }

    private boolean isNewConvention(List<Object> row) {
        return row.get(0).toString().matches("\\d+");
    }

    private boolean validateActivity(List<Object> row, int i) {
        return !row.get(i).toString().equals("") && !row.get(i + 1).toString().equals("") &&
                !row.get(i + 2).toString().equals("") &&
                Arrays.asList("W", "L", "C").contains(row.get(i + 1).toString());
    }
}
