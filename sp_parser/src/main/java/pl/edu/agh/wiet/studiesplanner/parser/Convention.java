package pl.edu.agh.wiet.studiesplanner.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michał on 22.04.2018.
 */
public class Convention {
    private int conventionNumber;
    private List<TimeBlock> timeBlocks;

    public Convention(int number) {
        this.conventionNumber = number;
        timeBlocks = new ArrayList<>();
    }

    public int getConventionNumber() {
        return conventionNumber;
    }

    public void setConventionNumber(int conventionNumber) {
        this.conventionNumber = conventionNumber;
    }

    public List<TimeBlock> getTimeBlocks() {
        return timeBlocks;
    }

    public void setTimeBlocks(List<TimeBlock> timeBlocks) {
        this.timeBlocks = timeBlocks;
    }
}
