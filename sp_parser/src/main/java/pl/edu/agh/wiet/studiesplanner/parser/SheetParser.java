package pl.edu.agh.wiet.studiesplanner.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Michał on 22.04.2018.
 */
public class SheetParser {
    public static List<Convention> parse(List<List<Object>> downloadedSheet) {
        List<Convention> conventionList = new ArrayList<>();
        List<Object> headerRow = null;

        for (List<Object> row: downloadedSheet) {
            if(isHeader(row)) {
                headerRow = row;
                continue;
            }
            if(isNewConvention(row)) {
                conventionList.add(new Convention(Integer.parseInt(row.get(0).toString())));
            }
            conventionList
                    .get(conventionList.size() - 1)
                    .getTimeBlocks()
                    .add(parseRow(row, headerRow));
        }

        return conventionList;
    }

    private static TimeBlock parseRow(List<Object> row, List<Object> headerRow) {
        TimeBlock timeBlock = new TimeBlock();
        String[] hours = row.get(2).toString().replaceAll("\\s+", "").split("-");

        timeBlock.setDate(row.get(1).toString().replaceAll("\\s+", ""));
        timeBlock.setHourStart(hours[0]);
        timeBlock.setHourEnd(hours[1]);

        for(int i=3; i<row.size(); i+=3) {
            Activity activity = parseActivity(row, i, headerRow);
            if(activity != null) timeBlock.getActivityList().add(activity);
        }

        return timeBlock;
    }

    private static Activity parseActivity(List<Object> row, int i, List<Object> headerRow) {
        if(!validateActivity(row, i)) return null;

        Activity activity = new Activity();

        String[] subjectAndClassroom = row.get(i).toString().split(", ");
        String[] headerContent = headerRow.get(i).toString().split(" ");

        activity.setSubject(subjectAndClassroom[0]);
        activity.setClassroom(subjectAndClassroom[1]);
        activity.setType(ActivityType.valueOf(row.get(i+1).toString().replaceAll("\\s+", "")));
        activity.setTeacher(row.get(i+2).toString());
        activity.setStudentsGroup(Integer.parseInt(headerContent[1]));

        return activity;
    }

    private static boolean isHeader(List<Object> row) {
        return !(row.get(0).toString().equals("") || row.get(0).toString().matches("\\d+"));
    }

    private static boolean isNewConvention(List<Object> row) {
        return row.get(0).toString().matches("\\d+");
    }

    private static boolean validateActivity(List<Object> row, int i) {
        if(row.get(i).toString().equals("") || row.get(i+1).toString().equals("") || row.get(i+2).toString().equals("")) return false;
        if(!Arrays.asList("W", "L", "C").contains(row.get(i+1).toString())) return false;
        return true;
    }
}
