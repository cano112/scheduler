package pl.edu.agh.wiet.studiesplanner.model.solver;

import pl.edu.agh.wiet.studiesplanner.model.data.StudentsGroup;
import pl.edu.agh.wiet.studiesplanner.model.data.TimeBlock;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Set;

public class StudentsGroupConflict extends Conflict {

    private final StudentsGroup studentsGroup;

    public StudentsGroupConflict(Set<TimeBlock> timeBlocks, StudentsGroup studentsGroup) {
        super(timeBlocks);
        this.studentsGroup = studentsGroup;
    }

    @Override
    public String getDescription() {
        StringBuilder desc = new StringBuilder("Students' group " + studentsGroup.getId()
                + "conflict in following time blocks: ");

        for(TimeBlock block : timeBlocks) {
            String startTime = block.getTimeStart().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
            String endTime = block.getTimeEnd().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
            desc.append(startTime).append(" - ").append(endTime).append(",");
        }

        return desc.toString();
    }

    public StudentsGroup getStudentsGroup() {
        return studentsGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StudentsGroupConflict that = (StudentsGroupConflict) o;

        return studentsGroup != null ? studentsGroup.equals(that.studentsGroup) : that.studentsGroup == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (studentsGroup != null ? studentsGroup.hashCode() : 0);
        return result;
    }
}
