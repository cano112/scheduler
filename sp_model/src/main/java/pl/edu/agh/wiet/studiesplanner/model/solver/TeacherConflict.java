package pl.edu.agh.wiet.studiesplanner.model.solver;
import pl.edu.agh.wiet.studiesplanner.model.data.Teacher;
import pl.edu.agh.wiet.studiesplanner.model.data.TimeBlock;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Set;

public final class TeacherConflict extends Conflict {

    private final Teacher teacher;

    public TeacherConflict(Set<TimeBlock> timeBlocks, Teacher teacher) {
        super(timeBlocks);
        this.teacher = teacher;
    }

    @Override
    public String getDescription() {
        StringBuilder desc = new StringBuilder("Teacher " + teacher.getFullName()
                + "conflict in following time blocks: ");

        for(TimeBlock block : timeBlocks) {
            String startTime = block.getTimeStart().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
            String endTime = block.getTimeEnd().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
            desc.append(startTime).append(" - ").append(endTime).append(",");
        }

        return desc.toString();
    }

    public Teacher getTeacher() {
        return teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TeacherConflict that = (TeacherConflict) o;

        return teacher != null ? teacher.equals(that.teacher) : that.teacher == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        return result;
    }
}
