package pl.edu.agh.wiet.studiesplanner.parser.services;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.model.data.*;
import pl.edu.agh.wiet.studiesplanner.model.parser.DocumentLink;
import pl.edu.agh.wiet.studiesplanner.model.parser.Warning;
import pl.edu.agh.wiet.studiesplanner.model.service.SheetParser;
import pl.edu.agh.wiet.studiesplanner.parser.exceptions.SheetParseException;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class SheetsParserFacade {
    private final ScheduleSheetParser scheduleParser;
    private final StudentsSheetParser studentsParser;
    private final TeachersSheetParser teachersParser;
    private final EventsSheetParser eventsParser;

    @Autowired
    public SheetsParserFacade(ScheduleSheetParser scheduleParser,
                              StudentsSheetParser studentsParser,
                              TeachersSheetParser teachersParser,
                              EventsSheetParser eventsParser) {
        this.scheduleParser = scheduleParser;
        this.studentsParser = studentsParser;
        this.teachersParser = teachersParser;
        this.eventsParser = eventsParser;
    }

    public Schedule parse(Stream<? extends DocumentLink> scheduleLinks,
                          Stream<? extends DocumentLink> studentsLinks,
                          Stream<? extends DocumentLink> teachersLinks,
                          Stream<? extends DocumentLink> eventLinks) {

        Schedule aggregatedSchedule = new Schedule();
        parseLinks(scheduleLinks, aggregatedSchedule, scheduleParser);
        parseLinks(studentsLinks, aggregatedSchedule, studentsParser);
        parseLinks(teachersLinks, aggregatedSchedule, teachersParser);
        parseLinks(eventLinks, aggregatedSchedule, eventsParser);
        return aggregatedSchedule;
    }

    public List<Warning> findWarnings(Schedule schedule) {
        List<Warning> warnings = new LinkedList<>();
        findConventionWarnings(schedule.getConventions(), warnings);
        findStudentGroupsWarnings(schedule.getStudentsGroups(), warnings);
        findTeacherWarnings(schedule.getTeachers(), warnings);
        return warnings;
    }

    private void findConventionWarnings(List<Convention> conventions, List<Warning> warnings) {
        if(conventions.isEmpty()) {
            warnings.add(new Warning("No conventions parsed"));
        }
    }

    private void findStudentGroupsWarnings(List<StudentsGroup> studentGroups, List<Warning> warnings) {
        EmailValidator emailValidator = EmailValidator.getInstance(false, true);
        if(studentGroups.isEmpty()) {
            warnings.add(new Warning("No student groups parsed"));
        } else {
            studentGroups.forEach(group -> {
                if(group.getStudents().isEmpty()) {
                    warnings.add(new Warning("Student group: " + group.getId() + " is empty"));
                } else {
                    Set<Student> students = group.getStudents();
                    students.forEach(student -> {
                        if(!emailValidator.isValid(student.getEmail())) {
                            warnings.add(new Warning("Student " + student.getFullName() + " has no valid email"));
                        }
                    });
                }
            });
        }
    }

    private void findTeacherWarnings(List<Teacher> teachers, List<Warning> warnings) {
        EmailValidator emailValidator = EmailValidator.getInstance(false, true);
        if(teachers.isEmpty()) {
            warnings.add(new Warning("No teachers parsed"));
        } else {
            teachers.forEach(teacher -> {
                if(!emailValidator.isValid(teacher.getEmail())) {
                    warnings.add(new Warning("Teacher " + teacher.getFullName() + " has no valid email"));
                }
            });
        }
    }

    private void parseLinks(Stream<? extends DocumentLink> links, Schedule model, SheetParser parser) {
        links.forEach(link -> {
            try {
                List<List<Object>> sheet = link.fetch();
                parser.parse(sheet, model);
            } catch (Exception e) {
                throw new SheetParseException("Cannot parse link", link);
            }
        });
    }
}
