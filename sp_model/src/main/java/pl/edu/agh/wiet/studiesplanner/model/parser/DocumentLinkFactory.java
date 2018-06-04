package pl.edu.agh.wiet.studiesplanner.model.parser;

import org.apache.commons.validator.routines.UrlValidator;
import pl.edu.agh.wiet.studiesplanner.model.parser.google.GoogleDocsLinkFactory;
import pl.edu.agh.wiet.studiesplanner.model.parser.xls.XlsLinkFactory;

public interface DocumentLinkFactory {
    DocumentLink createTeacherLink(String link);
    DocumentLink createParticipantLink(String link);
    DocumentLink createScheduleLink(String link);
    DocumentLink createEventLink(String link);

    static DocumentLinkFactory getFactoryByLink(String link) {
        UrlValidator urlValidator = new UrlValidator();
        if(urlValidator.isValid(link)) {
            return new GoogleDocsLinkFactory();
        }
        return new XlsLinkFactory();
    }

}
