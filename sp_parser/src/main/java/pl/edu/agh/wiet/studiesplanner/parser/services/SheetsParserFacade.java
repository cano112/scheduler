package pl.edu.agh.wiet.studiesplanner.parser.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.model.data.Schedule;
import pl.edu.agh.wiet.studiesplanner.model.parser.DocumentLink;
import pl.edu.agh.wiet.studiesplanner.parser.exceptions.SheetParseException;

import java.util.List;
import java.util.stream.Stream;

@Service
public class SheetsParserFacade {

    private final SheetDownloader downloader;
    private final ScheduleSheetParser scheduleParser;
    private final StudentsSheetParser studentsParser;

    @Autowired
    public SheetsParserFacade(SheetDownloader downloader, ScheduleSheetParser scheduleParser, StudentsSheetParser studentsParser) {
        this.downloader = downloader;
        this.scheduleParser = scheduleParser;
        this.studentsParser = studentsParser;
    }

    public Schedule parse(Stream<? extends DocumentLink> scheduleLinks,
                          Stream<? extends DocumentLink> studentsLinks,
                          Stream<? extends DocumentLink> teachersLinks) {

        Schedule aggregatedSchedule = new Schedule();

        scheduleLinks.forEach(link -> {
            try {
                List<List<Object>> sheet = downloader.download(link.getUrl());
                Schedule schedule = new Schedule();
                scheduleParser.parse(sheet, schedule);
                aggregatedSchedule.addConventions(schedule.getConventions());
                aggregatedSchedule.addStudentGroups(schedule.getStudentsGroups());
                aggregatedSchedule.addTeachers(schedule.getTeachers());
            } catch (Exception e) {
                throw new SheetParseException("Cannot parse link", link);
            }
        });

        studentsLinks.forEach(link -> {
            try {
                List<List<Object>> sheet = downloader.download(link.getUrl());
                studentsParser.parse(sheet, aggregatedSchedule);
            } catch (Exception e) {
                throw new SheetParseException("Cannot parse link", link);
            }
        });
        return aggregatedSchedule;
    }
}
