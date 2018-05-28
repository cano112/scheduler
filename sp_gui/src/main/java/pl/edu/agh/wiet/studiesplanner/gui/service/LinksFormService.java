package pl.edu.agh.wiet.studiesplanner.gui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.gui.exceptions.EmptyValueException;
import pl.edu.agh.wiet.studiesplanner.model.parser.*;
import pl.edu.agh.wiet.studiesplanner.model.repositories.LinksRepository;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class LinksFormService {

    private final LinksRepository linksRepository;
    private final NotificationService notificationService;

    @Autowired
    public LinksFormService(LinksRepository linksRepository,
                            NotificationService notificationService) {
        this.linksRepository = linksRepository;
        this.notificationService = notificationService;
    }

    public Set<DocumentLink> getScheduleLinksSet() {
        return linksRepository.streamAllScheduleLinks().collect(Collectors.toSet());
    }

    public Set<DocumentLink> getParticipantLinksSet() {
        return linksRepository.streamAllParticipantsLinks().collect(Collectors.toSet());
    }

    public Set<DocumentLink> getTeacherLinksSet() {
        return linksRepository.streamAllTeacherLinks().collect(Collectors.toSet());
    }

    public void addScheduleLink(String url) {
        checkEmptyLink(url);
        GoogleDocsLink link = new GoogleDocsScheduleLink(url);
        linksRepository.save(link);
        notificationService.showInfoMessage("Link added");
    }

    public void addParticipantLink(String url) {
        checkEmptyLink(url);
        GoogleDocsLink link = new GoogleDocsParticipantLink(url);
        linksRepository.save(link);
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
        linksRepository.save(link);
        notificationService.showInfoMessage("Link added");
    }

    public void deleteLink(Set<DocumentLink> links) {
        linksRepository.deleteAll(links);
        notificationService.showInfoMessage("Link deleted");
    }
}
