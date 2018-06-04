package pl.edu.agh.wiet.studiesplanner.model.parser.google;

import pl.edu.agh.wiet.studiesplanner.model.parser.DocumentLink;
import pl.edu.agh.wiet.studiesplanner.model.parser.DocumentLinkFactory;

public class GoogleDocsLinkFactory implements DocumentLinkFactory {

    @Override
    public DocumentLink createTeacherLink(String link) {
        return new GoogleDocsTeacherLink(link);
    }

    @Override
    public DocumentLink createParticipantLink(String link) {
        return new GoogleDocsParticipantLink(link);
    }

    @Override
    public DocumentLink createScheduleLink(String link) {
        return new GoogleDocsScheduleLink(link);
    }

    @Override
    public DocumentLink createEventLink(String link) {
        return new GoogleDocsEventLink(link);
    }
}
