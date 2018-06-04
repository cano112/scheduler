package pl.edu.agh.wiet.studiesplanner.model.parser.xls;

import pl.edu.agh.wiet.studiesplanner.model.parser.DocumentLink;
import pl.edu.agh.wiet.studiesplanner.model.parser.DocumentLinkFactory;

public class XlsLinkFactory implements DocumentLinkFactory {

    @Override
    public DocumentLink createTeacherLink(String link) {
        return new XlsTeacherLink(link);
    }

    @Override
    public DocumentLink createParticipantLink(String link) {
        return new XlsParticipantLink(link);
    }

    @Override
    public DocumentLink createScheduleLink(String link) {
        return new XlsScheduleLink(link);
    }

    @Override
    public DocumentLink createEventLink(String link) {
        return new XlsEventLink(link);
    }
}
