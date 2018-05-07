package pl.edu.agh.wiet.studiesplanner.model.solver;

import pl.edu.agh.wiet.studiesplanner.model.data.Activity;
import pl.edu.agh.wiet.studiesplanner.model.data.TimeBlock;

import java.util.Collections;
import java.util.Set;

public abstract class Conflict {
    protected final Set<TimeBlock> timeBlocks;

    protected Conflict(Set<TimeBlock> timeBlocks) {
        this.timeBlocks = Collections.unmodifiableSet(timeBlocks);
    }

    public abstract String getDescription();

    public Set<TimeBlock> getTimeBlocks() {
        return timeBlocks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conflict conflict = (Conflict) o;

        return timeBlocks.equals(conflict.timeBlocks);
    }

    @Override
    public int hashCode() {
        return timeBlocks.hashCode();
    }
}
