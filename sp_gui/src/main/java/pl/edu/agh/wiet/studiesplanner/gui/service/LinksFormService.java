package pl.edu.agh.wiet.studiesplanner.gui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.gui.exceptions.EmptyValueException;
import pl.edu.agh.wiet.studiesplanner.model.parser.*;
import pl.edu.agh.wiet.studiesplanner.model.parser.google.GoogleDocsLink;
import pl.edu.agh.wiet.studiesplanner.model.parser.google.GoogleDocsParticipantLink;
import pl.edu.agh.wiet.studiesplanner.model.parser.google.GoogleDocsScheduleLink;
import pl.edu.agh.wiet.studiesplanner.model.parser.google.GoogleDocsTeacherLink;
import pl.edu.agh.wiet.studiesplanner.model.repositories.LinksRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URL;
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

    public Set<DocumentLink> getEventLinksSet() {
        return linksRepository.streamAllEventLinks().collect(Collectors.toSet());
    }

    public void addScheduleLink(String url) {
        checkEmptyLink(url);
        DocumentLinkFactory factory = DocumentLinkFactory.getFactoryByLink(url);
        DocumentLink link = factory.createScheduleLink(url);
        linksRepository.save(link);
        notificationService.showInfoMessage("Link added");
    }

    public void addParticipantLink(String url) {
        checkEmptyLink(url);
        DocumentLinkFactory factory = DocumentLinkFactory.getFactoryByLink(url);
        DocumentLink link = factory.createParticipantLink(url);
        linksRepository.save(link);
        notificationService.showInfoMessage("Link added");
    }

    public void addTeacherLink(String url) {
        checkEmptyLink(url);
        DocumentLinkFactory factory = DocumentLinkFactory.getFactoryByLink(url);
        DocumentLink link = factory.createTeacherLink(url);
        linksRepository.save(link);
        notificationService.showInfoMessage("Link added");
    }

    public void addEventLink(String url) {
        checkEmptyLink(url);
        DocumentLinkFactory factory = DocumentLinkFactory.getFactoryByLink(url);
        DocumentLink link = factory.createEventLink(url);
        linksRepository.save(link);
        notificationService.showInfoMessage("Link added");
    }

    public void deleteLinks(Set<DocumentLink> links) {
        links.forEach(link -> {
            try {
                link.onDelete();
            } catch (IOException e) {
                notificationService.showWarningMessage("Cannot delete file!");
            }
        });

        linksRepository.deleteAll(links);
        notificationService.showInfoMessage("Link deleted");
    }

    private void checkEmptyLink(String url) {
        if(url == null || url.isEmpty()) {
            throw new EmptyValueException("Link is empty!");
        }
    }
}
