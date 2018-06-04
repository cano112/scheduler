package pl.edu.agh.wiet.studiesplanner.model.parser.xls;

import pl.edu.agh.wiet.studiesplanner.model.DirectoryConfig;
import pl.edu.agh.wiet.studiesplanner.model.service.LinksService;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Entity
@DiscriminatorValue("XP")
public class XlsParticipantLink extends XlsLink {
    public XlsParticipantLink() {}
    public XlsParticipantLink(@NotNull String path) {
        super(path);
    }
}
