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
import java.util.*;

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
        Schedule schedule = createScheduleWithOneClassroomConflict();

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
        Schedule schedule = createScheduleWithOneTeacherConflict();

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
        Schedule schedule = createScheduleWithOneStudentsGroupConflict();

        // when
        Set<Conflict> conflicts = solver.solve(schedule.getConventions());

        // then
        assertThat(conflicts)
                .hasSize(1)
                .hasOnlyElementsOfType(StudentsGroupConflict.class)
                .allMatch(conflict -> ((StudentsGroupConflict) conflict).getStudentsGroup().getId() == 1);
    }

    @Test
    public void testSolveWithNoConflict() throws Exception {
        // given
        Schedule schedule = createScheduleWithNoConflict();

        // when
        Set<Conflict> conflicts = solver.solve(schedule.getConventions());

        // then
        assertThat(conflicts)
                .hasSize(0);
    }

    @Test
    public void testSolveWithMoreThenOneClassroomConflict() throws Exception {
        // given
        Schedule schedule = createScheduleWithMoreThanOneClassroomConflict();

        // when
        Set<Conflict> conflicts = solver.solve(schedule.getConventions());

        // then
        assertThat(conflicts)
                .hasSize(2)
                .hasOnlyElementsOfType(ClassroomConflict.class)
                .allMatch(conflict -> ((ClassroomConflict) conflict).getClassroom().getName().equals("P_123"));
    }

    @Test
    public void testSolveWithMoreThenOneTeacherConflict() throws Exception {
        // given
        Schedule schedule = createScheduleWithMoreThanOneTeacherConflict();

        // when
        Set<Conflict> conflicts = solver.solve(schedule.getConventions());

        // then
        assertThat(conflicts)
                .hasSize(2)
                .hasOnlyElementsOfType(TeacherConflict.class)
                .allMatch(conflict -> ((TeacherConflict) conflict).getTeacher().getFullName().equals("Jan Nowak"));
    }

    @Test
    public void testSolveWithMoreThenOneStudentsGroupConflict() throws Exception {
        // given
        Schedule schedule = createScheduleWithMoreThanOneStudentsGroupConflict();

        // when
        Set<Conflict> conflicts = solver.solve(schedule.getConventions());

        // then
        assertThat(conflicts)
                .hasSize(2)
                .hasOnlyElementsOfType(StudentsGroupConflict.class)
                .allMatch(conflict -> ((StudentsGroupConflict) conflict).getStudentsGroup().getId() == 1);
    }

    @Test
    public void testSolveWithEveryConflict() throws Exception {
        // given
        Schedule schedule = createScheduleWithEveryConflict();

        // when
        Set<Conflict> conflicts = solver.solve(schedule.getConventions());

        // then
        assertThat(conflicts)
                .hasAtLeastOneElementOfType(ClassroomConflict.class)
                .hasAtLeastOneElementOfType(TeacherConflict.class)
                .hasAtLeastOneElementOfType(StudentsGroupConflict.class);
    }

    private Schedule createScheduleWithOneClassroomConflict() {
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

    private Schedule createScheduleWithOneTeacherConflict() {
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

    private Schedule createScheduleWithOneStudentsGroupConflict() {
        Schedule schedule = new Schedule();

        Teacher teacher1 = new Teacher("Jan", "Nowak");
        Teacher teacher2 = new Teacher("Adam", "Schmidt");

        StudentsGroup group1 = TestUtils.createStudentsGroup(1, 4);

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

    private Schedule createScheduleWithNoConflict() {
        Schedule schedule = new Schedule();

        Teacher teacher1 = new Teacher("Jan", "Nowak");
        Teacher teacher2 = new Teacher("Adam", "Schmidt");

        StudentsGroup group1 = TestUtils.createStudentsGroup(1, 4);
        StudentsGroup group2 = TestUtils.createStudentsGroup(2, 5);

        Classroom classroom1 = new Classroom("P_123");
        Classroom classroom2 = new Classroom("P_321");

        Subject subject1 = new Subject("Matematyka");

        Activity activity1 = new Activity(teacher1, ActivityType.C, subject1, classroom1, group1);
        Activity activity2 = new Activity(teacher2, ActivityType.C, subject1, classroom2, group2);

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

    private Schedule createScheduleWithMoreThanOneClassroomConflict() {
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

        TimeBlock block2 = new TimeBlock(
                LocalDateTime.of(2018, Month.MAY, 22, 12, 0),
                LocalDateTime.of(2018, Month.MAY, 22, 13, 0),
                Arrays.asList(activity1, activity2));

        Convention convention = new Convention(1, Arrays.asList(block1,block2));
        schedule.addConvention(convention);
        schedule.addStudentsGroup(group1);
        schedule.addStudentsGroup(group2);
        schedule.addTeacher(teacher1);
        schedule.addTeacher(teacher2);

        return schedule;
    }

    private Schedule createScheduleWithMoreThanOneTeacherConflict() {
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

        TimeBlock block2 = new TimeBlock(
                LocalDateTime.of(2018, Month.MAY, 22, 12, 0),
                LocalDateTime.of(2018, Month.MAY, 22, 13, 0),
                Arrays.asList(activity1, activity2));

        Convention convention = new Convention(1, Arrays.asList(block1,block2));
        schedule.addConvention(convention);
        schedule.addStudentsGroup(group1);
        schedule.addStudentsGroup(group2);
        schedule.addTeacher(teacher1);

        return schedule;
    }

    private Schedule createScheduleWithMoreThanOneStudentsGroupConflict() {
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

        TimeBlock block2 = new TimeBlock(
                LocalDateTime.of(2018, Month.MAY, 22, 12, 0),
                LocalDateTime.of(2018, Month.MAY, 22, 13, 0),
                Arrays.asList(activity1, activity2));

        Convention convention = new Convention(1, Arrays.asList(block1,block2));
        schedule.addConvention(convention);
        schedule.addStudentsGroup(group1);
        schedule.addStudentsGroup(group1);
        schedule.addTeacher(teacher1);
        schedule.addTeacher(teacher2);

        return schedule;
    }

    private Schedule createScheduleWithEveryConflict() {
        Schedule schedule = new Schedule();

        Teacher teacher1 = new Teacher("Jan", "Nowak");
        Teacher teacher2 = new Teacher("Adam", "Schmidt");

        StudentsGroup group1 = TestUtils.createStudentsGroup(1, 4);
        StudentsGroup group2 = TestUtils.createStudentsGroup(2, 5);

        Classroom classroom1 = new Classroom("P_123");
        Classroom classroom2 = new Classroom("P_321");

        Subject subject1 = new Subject("Matematyka");

        Activity activity1 = new Activity(teacher1, ActivityType.C, subject1, classroom1, group1);
        Activity activity2 = new Activity(teacher2, ActivityType.C, subject1, classroom1, group2);
        Activity activity3 = new Activity(teacher2, ActivityType.C, subject1, classroom2, group1);

        TimeBlock block1 = new TimeBlock(
                LocalDateTime.of(2018, Month.MAY, 21, 12, 0),
                LocalDateTime.of(2018, Month.MAY, 21, 13, 0),
                Arrays.asList(activity1, activity2));

        TimeBlock block2 = new TimeBlock(
                LocalDateTime.of(2018, Month.MAY, 22, 12, 0),
                LocalDateTime.of(2018, Month.MAY, 22, 13, 0),
                Arrays.asList(activity2, activity3));

        TimeBlock block3 = new TimeBlock(
                LocalDateTime.of(2018, Month.MAY, 22, 12, 0),
                LocalDateTime.of(2018, Month.MAY, 22, 13, 0),
                Arrays.asList(activity1, activity3));

        Convention convention = new Convention(1, Arrays.asList(block1,block2,block3));
        schedule.addConvention(convention);
        schedule.addStudentsGroup(group1);
        schedule.addStudentsGroup(group2);
        schedule.addTeacher(teacher1);
        schedule.addTeacher(teacher2);

        return schedule;
    }

}
