package pl.edu.agh.wiet.studiesplanner.solver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.agh.wiet.studiesplanner.TestUtils;
import pl.edu.agh.wiet.studiesplanner.model.data.*;
import pl.edu.agh.wiet.studiesplanner.model.solver.ClassroomConflict;
import pl.edu.agh.wiet.studiesplanner.model.solver.Conflict;
import pl.edu.agh.wiet.studiesplanner.model.solver.StudentsGroupConflict;
import pl.edu.agh.wiet.studiesplanner.model.solver.TeacherConflict;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
public class ConflictSolverTest {

    @TestConfiguration
    static class ConflictSolverTestConfig {

        @Bean
        public ConflictSolver conflictSolver() {
            return new ConflictSolver();
        }
    }

    @Autowired
    private ConflictSolver solver;

    @Test
    public void testSolveWithOneClassroomConflict() throws Exception {
        // given
        Schedule schedule = createScheduleWithClassroomConflict();

        // when
        Set<Conflict> conflicts = solver.solve(schedule.getConventions());

        // then
        assertThat(conflicts)
                .hasSize(1)
                .hasOnlyElementsOfType(ClassroomConflict.class)
                .allMatch(conflict -> ((ClassroomConflict) conflict).getClassroom().getName().equals("P_123"));
    }

    @Test
    public void testSolveWithOneTeacherConflict() throws Exception {
        // given
        Schedule schedule = createScheduleWithTeacherConflict();

        // when
        Set<Conflict> conflicts = solver.solve(schedule.getConventions());

        // then
        assertThat(conflicts)
                .hasSize(1)
                .hasOnlyElementsOfType(TeacherConflict.class)
                .allMatch(conflict -> ((TeacherConflict) conflict).getTeacher().getFullName().equals("Jan Nowak"));
    }

    @Test
    public void testSolveWithOneStudentsGroupConflict() throws Exception {
        // given
        Schedule schedule = createScheduleWithStudentsGroupConflict();

        // when
        Set<Conflict> conflicts = solver.solve(schedule.getConventions());

        // then
        assertThat(conflicts)
                .hasSize(1)
                .hasOnlyElementsOfType(StudentsGroupConflict.class)
                .allMatch(conflict -> ((StudentsGroupConflict) conflict).getStudentsGroup().getId() == 1);
    }

    private Schedule createScheduleWithClassroomConflict() {
        Schedule schedule = new Schedule();

        Teacher teacher1 = new Teacher("Jan", "Nowak");
        Teacher teacher2 = new Teacher("Adam", "Schmidt");

        StudentsGroup group1 = TestUtils.createStudentsGroup(1, 4);
        StudentsGroup group2 = TestUtils.createStudentsGroup(2, 5);

        Classroom classroom1 = new Classroom("P_123");

        Subject subject1 = new Subject("Matematyka");

        Activity activity1 = new Activity(teacher1, ActivityType.C, subject1, classroom1, group1);
        Activity activity2 = new Activity(teacher2, ActivityType.C, subject1, classroom1, group2);

        TimeBlock block1 = new TimeBlock(
                LocalDateTime.of(2018, Month.MAY, 21, 12, 0),
                LocalDateTime.of(2018, Month.MAY, 21, 13, 0),
                Arrays.asList(activity1, activity2));

        Convention convention = new Convention(1, Collections.singletonList(block1));
        schedule.addConvention(convention);
        schedule.addStudentsGroup(group1);
        schedule.addStudentsGroup(group2);
        schedule.addTeacher(teacher1);
        schedule.addTeacher(teacher2);

        return schedule;
    }

    private Schedule createScheduleWithTeacherConflict() {
        Schedule schedule = new Schedule();

        Teacher teacher1 = new Teacher("Jan", "Nowak");

        StudentsGroup group1 = TestUtils.createStudentsGroup(1, 4);
        StudentsGroup group2 = TestUtils.createStudentsGroup(2, 5);

        Classroom classroom1 = new Classroom("P_123");
        Classroom classroom2 = new Classroom("P_321");

        Subject subject1 = new Subject("Matematyka");

        Activity activity1 = new Activity(teacher1, ActivityType.C, subject1, classroom1, group1);
        Activity activity2 = new Activity(teacher1, ActivityType.C, subject1, classroom2, group2);

        TimeBlock block1 = new TimeBlock(
                LocalDateTime.of(2018, Month.MAY, 21, 12, 0),
                LocalDateTime.of(2018, Month.MAY, 21, 13, 0),
                Arrays.asList(activity1, activity2));

        Convention convention = new Convention(1, Collections.singletonList(block1));
        schedule.addConvention(convention);
        schedule.addStudentsGroup(group1);
        schedule.addStudentsGroup(group2);
        schedule.addTeacher(teacher1);

        return schedule;
    }

    private Schedule createScheduleWithStudentsGroupConflict() {
        Schedule schedule = new Schedule();

        Teacher teacher1 = new Teacher("Jan", "Nowak");
        Teacher teacher2 = new Teacher("Adam", "Schmidt");

        StudentsGroup group1 = TestUtils.createStudentsGroup(1, 4);
        //StudentsGroup group2 = TestUtils.createStudentsGroup(1, 5);

        Classroom classroom1 = new Classroom("P_123");
        Classroom classroom2 = new Classroom("P_321");

        Subject subject1 = new Subject("Matematyka");

        Activity activity1 = new Activity(teacher1, ActivityType.C, subject1, classroom1, group1);
        Activity activity2 = new Activity(teacher2, ActivityType.C, subject1, classroom2, group1);

        TimeBlock block1 = new TimeBlock(
                LocalDateTime.of(2018, Month.MAY, 21, 12, 0),
                LocalDateTime.of(2018, Month.MAY, 21, 13, 0),
                Arrays.asList(activity1, activity2));

        Convention convention = new Convention(1, Collections.singletonList(block1));
        schedule.addConvention(convention);
        schedule.addStudentsGroup(group1);
        schedule.addStudentsGroup(group1);
        schedule.addTeacher(teacher1);
        schedule.addTeacher(teacher2);

        return schedule;
    }
}
