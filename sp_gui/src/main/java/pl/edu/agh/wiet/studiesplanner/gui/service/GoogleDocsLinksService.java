package pl.edu.agh.wiet.studiesplanner.gui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.gui.exceptions.EmptyValueException;
import pl.edu.agh.wiet.studiesplanner.model.parser.GoogleDocsLink;
import pl.edu.agh.wiet.studiesplanner.model.parser.GoogleDocsParticipantLink;
import pl.edu.agh.wiet.studiesplanner.model.parser.GoogleDocsScheduleLink;
import pl.edu.agh.wiet.studiesplanner.model.parser.GoogleDocsTeacherLink;
import pl.edu.agh.wiet.studiesplanner.model.repositories.GoogleDocsLinksRepository;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class GoogleDocsLinksService {

    private final GoogleDocsLinksRepository googleDocsLinksRepository;
    private final NotificationService notificationService;

    @Autowired
    public GoogleDocsLinksService(GoogleDocsLinksRepository googleDocsLinksRepository,
                                  NotificationService notificationService) {
        this.googleDocsLinksRepository = googleDocsLinksRepository;
        this.notificationService = notificationService;
    }

    public Set<GoogleDocsLink> getScheduleLinksSet() {
        return googleDocsLinksRepository.streamScheduleLinks().collect(Collectors.toSet());
    }

    public Set<GoogleDocsLink> getParticipantLinksSet() {
        return googleDocsLinksRepository.streamParticipantLinks().collect(Collectors.toSet());
    }

    public Set<GoogleDocsLink> getTeacherLinksSet() {
        return googleDocsLinksRepository.streamTeacherLinks().collect(Collectors.toSet());
    }

    public void addScheduleLink(String url) {
        checkEmptyLink(url);
        GoogleDocsLink link = new GoogleDocsScheduleLink(url);
        googleDocsLinksRepository.save(link);
        notificationService.showInfoMessage("Link added");
    }

    public void addParticipantLink(String url) {
        checkEmptyLink(url);
        GoogleDocsLink link = new GoogleDocsParticipantLink(url);
        googleDocsLinksRepository.save(link);
        notificationService.showInfoMessage("Link added");
    }

    private void checkEmptyLink(String url) {
        if(url == null || url.isEmpty()) {
            throw new EmptyValueException("Link is empty!");
        }
    }

    public void addTeacherLink(String url) {
        checkEmptyLink(url);
        GoogleDocsLink link = new GoogleDocsTeacherLink(url);
        googleDocsLinksRepository.save(link);
        notificationService.showInfoMessage("Link added");
    }

    public void deleteLink(Set<GoogleDocsLink> links) {
        googleDocsLinksRepository.deleteAll(links);
        notificationService.showInfoMessage("Link deleted");
    }
}
