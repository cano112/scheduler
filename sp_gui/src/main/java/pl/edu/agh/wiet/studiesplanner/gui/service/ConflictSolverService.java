package pl.edu.agh.wiet.studiesplanner.gui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.model.data.Convention;
import pl.edu.agh.wiet.studiesplanner.model.data.Schedule;
import pl.edu.agh.wiet.studiesplanner.model.parser.GoogleDocsLink;
import pl.edu.agh.wiet.studiesplanner.model.parser.GoogleDocsScheduleLink;
import pl.edu.agh.wiet.studiesplanner.model.repositories.GoogleDocsLinksRepository;
import pl.edu.agh.wiet.studiesplanner.model.solver.Conflict;
import pl.edu.agh.wiet.studiesplanner.parser.exceptions.SheetParseException;
import pl.edu.agh.wiet.studiesplanner.parser.services.GoogleSheetDownloader;
import pl.edu.agh.wiet.studiesplanner.parser.services.ScheduleSheetParser;
import pl.edu.agh.wiet.studiesplanner.parser.services.SheetsParserFacade;
import pl.edu.agh.wiet.studiesplanner.solver.ConflictSolver;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
@Transactional
public class ConflictSolverService {

    private final GoogleDocsLinksRepository googleDocsLinksRepository;
    private final NotificationService notificationService;
    private final SheetsParserFacade sheetsParser;
    @Autowired
    public ConflictSolverService(GoogleDocsLinksRepository googleDocsLinksRepository,
                                 NotificationService notificationService,
                                 SheetsParserFacade sheetsParser) {
        this.googleDocsLinksRepository = googleDocsLinksRepository;
        this.notificationService = notificationService;
        this.sheetsParser = sheetsParser;
    }

    public String solveConflicts() {

        Stream<GoogleDocsLink> scheduleLinks = googleDocsLinksRepository.streamScheduleLinks();
        Stream<GoogleDocsLink> participantLinks = googleDocsLinksRepository.streamParticipantLinks();
        Stream<GoogleDocsLink> teacherLinks = googleDocsLinksRepository.streamTeacherLinks();

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
