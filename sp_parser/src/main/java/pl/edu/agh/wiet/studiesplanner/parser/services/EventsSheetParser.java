package pl.edu.agh.wiet.studiesplanner.parser.services;

import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.model.data.*;
import pl.edu.agh.wiet.studiesplanner.model.service.SheetParser;

import java.sql.Time;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class EventsSheetParser implements SheetParser {
    public void parse(List<List<Object>> downloadedSheet, Schedule model) {
        HashMap<String, Integer> columnMap = new HashMap<>();
        columnMap.put("date", 0);
        columnMap.put("hour", 1);
        columnMap.put("event", 2);
        columnMap.put("classroom", 3);

        for(List<Object> row: downloadedSheet) {
            if(row.get(columnMap.get("date")).toString().equals("Data")) continue;
            Classroom classroom = new Classroom(row.get(columnMap.get("classroom")).toString());
            String event = row.get(columnMap.get("event")).toString();
            Subject subject = new Subject(event);

            String[] hours = row.get(columnMap.get("hour")).toString().replaceAll("\\s+", "").split("-");

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.M.yyyy");
            LocalDate date = LocalDate.parse(row.get(columnMap.get("date")).toString().replaceAll("\\s+", ""), dateFormatter);

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
            LocalDateTime startTime = LocalDateTime.of(date, LocalTime.parse(hours[0], timeFormatter));
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.parse(hours[1], timeFormatter));

            Activity activity = new Activity(null, ActivityType.K, subject, classroom, null);

            List<Activity> activityList = new LinkedList<>();
            activityList.add(activity);
            TimeBlock timeBlock = new TimeBlock(startTime, endTime, activityList);

            int eventYear = timeBlock.getTimeStart().getYear();
            int eventMonth = timeBlock.getTimeStart().getMonthValue();
            int eventDay = timeBlock.getTimeStart().getDayOfMonth();
            boolean eventAdded = false;
            List<Convention> conventionsWorkingList = new LinkedList<>(model.getConventions());

            CONV_LOOP: for (Convention convention : conventionsWorkingList) {
                List<TimeBlock> timeBlocksWorkingList = new LinkedList<>(convention.getTimeBlocks());
                for (TimeBlock timeBlock1 : timeBlocksWorkingList) {
                    int year = timeBlock1.getTimeStart().getYear();
                    int month = timeBlock1.getTimeStart().getMonthValue();
                    int day = timeBlock1.getTimeStart().getDayOfMonth();
                    if (eventYear == year && eventMonth == month && eventDay == day) {
                        convention.getTimeBlocks().add(timeBlock);
                        eventAdded = true;
                        break CONV_LOOP;
                    }
                }
            }

            if(!eventAdded) {
                List<TimeBlock> timeBlocks = new LinkedList<>();
                timeBlocks.add(timeBlock);
                Convention convention1 = new Convention(0, timeBlocks);
                model.getConventions().add(convention1);
            }
        }
    }
}
