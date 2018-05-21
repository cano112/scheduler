package pl.edu.agh.wiet.studiesplanner.model.solver;

import pl.edu.agh.wiet.studiesplanner.model.data.Classroom;
import pl.edu.agh.wiet.studiesplanner.model.data.TimeBlock;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Set;

public class ClassroomConflict extends Conflict {

    private final Classroom classroom;

    public ClassroomConflict(Set<TimeBlock> timeBlocks, Classroom classroom) {
        super(timeBlocks);
        this.classroom = classroom;
    }

    @Override
    public String getDescription() {
        StringBuilder desc = new StringBuilder("Classroom " + classroom.getName()
                + " conflict in following time blocks: ");

        for(TimeBlock block : timeBlocks) {
            String startTime = block.getTimeStart().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
            String endTime = block.getTimeEnd().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
            desc.append(startTime).append(" - ").append(endTime).append(",");
        }

        return desc.toString();
    }

    public Classroom getClassroom() {
        return classroom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ClassroomConflict that = (ClassroomConflict) o;

        return classroom.equals(that.classroom);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + classroom.hashCode();
        return result;
    }
}
