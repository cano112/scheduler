package pl.edu.agh.wiet.studiesplanner.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michał on 22.04.2018.
 */
public class TimeBlock {
    private String date;
    private String hourStart;
    private String hourEnd;
    private List<Activity> activityList;

    public TimeBlock() {
        activityList = new ArrayList<>();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHourStart() {
        return hourStart;
    }

    public void setHourStart(String hourStart) {
        this.hourStart = hourStart;
    }

    public String getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(String hourEnd) {
        this.hourEnd = hourEnd;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }



}
