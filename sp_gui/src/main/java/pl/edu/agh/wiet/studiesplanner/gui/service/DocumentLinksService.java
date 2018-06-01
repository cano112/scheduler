package pl.edu.agh.wiet.studiesplanner.gui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.gui.exceptions.EmptyValueException;
import pl.edu.agh.wiet.studiesplanner.model.parser.*;
import pl.edu.agh.wiet.studiesplanner.model.repositories.GoogleDocsLinksRepository;
import pl.edu.agh.wiet.studiesplanner.model.repositories.XlsLinksRepository;

import javax.transaction.Transactional;
import java.net.URL;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class DocumentLinksService {

    private final GoogleDocsLinksRepository googleDocsLinksRepository;
    private final XlsLinksRepository xlsLinksRepository;
    private final NotificationService notificationService;

    @Autowired
    public DocumentLinksService(GoogleDocsLinksRepository googleDocsLinksRepository,
                                XlsLinksRepository xlsLinksRepository,
                                NotificationService notificationService) {
        this.googleDocsLinksRepository = googleDocsLinksRepository;
        this.xlsLinksRepository = xlsLinksRepository;
        this.notificationService = notificationService;
    }

    public Set<DocumentLink> getScheduleLinksSet() {
        Set<DocumentLink> documentLinkSet = googleDocsLinksRepository.streamScheduleLinks().collect(Collectors.toSet());
        documentLinkSet.addAll(xlsLinksRepository.streamScheduleLinks().collect(Collectors.toSet()));
        return documentLinkSet;
    }

    public Set<DocumentLink> getParticipantLinksSet() {
        Set<DocumentLink> documentLinkSet = googleDocsLinksRepository.streamParticipantLinks().collect(Collectors.toSet());
        documentLinkSet.addAll(xlsLinksRepository.streamParticipantLinks().collect(Collectors.toSet()));
        return documentLinkSet;
    }

    public Set<DocumentLink> getTeacherLinksSet() {
        Set<DocumentLink> documentLinkSet = googleDocsLinksRepository.streamTeacherLinks().collect(Collectors.toSet());
        documentLinkSet.addAll(xlsLinksRepository.streamTeacherLinks().collect(Collectors.toSet()));
        return documentLinkSet;
    }

    public Set<DocumentLink> getEventLinksSet() {
        Set<DocumentLink> documentLinkSet = googleDocsLinksRepository.streamEventLinks().collect(Collectors.toSet());
        documentLinkSet.addAll(xlsLinksRepository.streamEventLinks().collect(Collectors.toSet()));
        return documentLinkSet;
    }

    public void addScheduleLink(String url) {
        checkEmptyLink(url);
        if (isUrl(url)) {
            GoogleDocsLink link = new GoogleDocsScheduleLink(url);
            googleDocsLinksRepository.save(link);
        } else {
            XlsLink link = new XlsScheduleLink(url);
            xlsLinksRepository.save(link);
        }
        notificationService.showInfoMessage("Link added");
    }

    public void addParticipantLink(String url) {
        checkEmptyLink(url);
        if (isUrl(url)) {
            GoogleDocsLink link = new GoogleDocsParticipantLink(url);
            googleDocsLinksRepository.save(link);
        } else {
            XlsLink link = new XlsParticipantLink(url);
            xlsLinksRepository.save(link);
        }
        notificationService.showInfoMessage("Link added");
    }

    public void addTeacherLink(String url) {
        checkEmptyLink(url);
        if (isUrl(url)) {
            GoogleDocsLink link = new GoogleDocsTeacherLink(url);
            googleDocsLinksRepository.save(link);
        } else {
            XlsLink link = new XlsTeacherLink(url);
            xlsLinksRepository.save(link);
        }
        notificationService.showInfoMessage("Link added");
    }

    public void addEventLink(String url) { //to do
        checkEmptyLink(url);
        if (isUrl(url)) {
            GoogleDocsLink link = new GoogleDocsEventLink(url);
            googleDocsLinksRepository.save(link);
        } else {
            XlsLink link = new XlsEventLink(url);
            xlsLinksRepository.save(link);
        }
        notificationService.showInfoMessage("Link added");
    }

    public void deleteLink(Set<DocumentLink> links) {
        for (DocumentLink link: links) {
            if(link.getClass().equals(GoogleDocsLink.class)) googleDocsLinksRepository.delete((GoogleDocsLink) link);
            else xlsLinksRepository.delete((XlsLink) link);
        }
        notificationService.showInfoMessage("Links deleted");
    }

    private void checkEmptyLink(String url) {
        if(url == null || url.isEmpty()) {
            throw new EmptyValueException("Link is empty!");
        }
    }

    private static boolean isUrl(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
