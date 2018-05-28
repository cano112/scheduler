package pl.edu.agh.wiet.studiesplanner.parser.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.model.data.Schedule;
import pl.edu.agh.wiet.studiesplanner.model.parser.DocumentLink;
import pl.edu.agh.wiet.studiesplanner.model.service.SheetParser;
import pl.edu.agh.wiet.studiesplanner.parser.exceptions.SheetParseException;

import java.util.List;
import java.util.stream.Stream;

@Service
public class SheetsParserFacade {
    private final ScheduleSheetParser scheduleParser;
    private final StudentsSheetParser studentsParser;
    private final TeachersSheetParser teachersParser;

    @Autowired
    public SheetsParserFacade(ScheduleSheetParser scheduleParser,
                              StudentsSheetParser studentsParser,
                              TeachersSheetParser teachersParser) {
        this.scheduleParser = scheduleParser;
        this.studentsParser = studentsParser;
        this.teachersParser = teachersParser;
    }

    public Schedule parse(Stream<? extends DocumentLink> scheduleLinks,
                          Stream<? extends DocumentLink> studentsLinks,
                          Stream<? extends DocumentLink> teachersLinks) {

        Schedule aggregatedSchedule = new Schedule();
        parseLinks(scheduleLinks, aggregatedSchedule, scheduleParser);
        parseLinks(studentsLinks, aggregatedSchedule, studentsParser);
        parseLinks(teachersLinks, aggregatedSchedule, teachersParser);
        return aggregatedSchedule;
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
