package pl.edu.agh.wiet.studiesplanner.gui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.model.data.Schedule;
import pl.edu.agh.wiet.studiesplanner.model.parser.DocumentLink;
import pl.edu.agh.wiet.studiesplanner.model.repositories.LinksRepository;
import pl.edu.agh.wiet.studiesplanner.model.solver.Conflict;
import pl.edu.agh.wiet.studiesplanner.parser.exceptions.SheetParseException;
import pl.edu.agh.wiet.studiesplanner.parser.services.SheetsParserFacade;
import pl.edu.agh.wiet.studiesplanner.solver.ConflictSolver;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Stream;

@Service
@Transactional
public class ConflictSolverService {
    private final LinksRepository linksRepository;
    private final NotificationService notificationService;
    private final SheetsParserFacade sheetsParser;
    @Autowired
    public ConflictSolverService(LinksRepository linksRepository,
                                 NotificationService notificationService,
                                 SheetsParserFacade sheetsParser) {
        this.linksRepository = linksRepository;
        this.notificationService = notificationService;
        this.sheetsParser = sheetsParser;
    }

    public String solveConflicts() {

        Stream<DocumentLink> scheduleLinks = linksRepository.streamAllScheduleLinks();
        Stream<DocumentLink> participantLinks = linksRepository.streamAllParticipantsLinks();
        Stream<DocumentLink> teacherLinks = linksRepository.streamAllTeacherLinks();

        try {
            Schedule schedule = sheetsParser.parse(scheduleLinks, participantLinks, teacherLinks);

            Set<Conflict> conflicts = new ConflictSolver().solve(schedule.getConventions());

            StringBuilder stringBuilder = new StringBuilder();
            conflicts.forEach(
                    conflict -> stringBuilder.append(conflict.getDescription()).append("\n")
            );

            notificationService.showInfoMessage("Done");
            return stringBuilder.toString();
        } catch (SheetParseException e) {
            notificationService.showErrorMessage("Cannot parse link: " + e.getLink().getUrl());
        }

        return "";
    }
}
