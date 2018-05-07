package pl.edu.agh.wiet.studiesplanner.model.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Michał on 22.04.2018.
 */
public final class TimeBlock {
    private final LocalDateTime timeStart;
    private final LocalDateTime timeEnd;
    private final List<Activity> activityList;


    public TimeBlock(LocalDateTime timeStart, LocalDateTime timeEnd, List<Activity> activities) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.activityList = Collections.unmodifiableList(activities);
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public boolean isOverlappingWith(TimeBlock other) {
        return timeEnd.isAfter(other.timeStart) && timeStart.isBefore(other.timeEnd);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeBlock timeBlock = (TimeBlock) o;

        if (!timeStart.equals(timeBlock.timeStart)) return false;
        return timeEnd.equals(timeBlock.timeEnd);
    }

    @Override
    public int hashCode() {
        int result = timeStart.hashCode();
        result = 31 * result + timeEnd.hashCode();
        return result;
    }
}
