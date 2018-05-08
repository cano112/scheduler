package pl.edu.agh.wiet.studiesplanner.model.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Michał on 22.04.2018.
 */
public class Convention {
    private final int conventionNumber;
    private final List<TimeBlock> timeBlocks;

    public Convention(int number, List<TimeBlock> timeBlocks) {
        this.conventionNumber = number;
        this.timeBlocks = Collections.unmodifiableList(timeBlocks);
    }

    public int getConventionNumber() {
        return conventionNumber;
    }

    public List<TimeBlock> getTimeBlocks() {
        return timeBlocks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Convention that = (Convention) o;

        return conventionNumber == that.conventionNumber;
    }

    @Override
    public int hashCode() {
        return conventionNumber;
    }
}
