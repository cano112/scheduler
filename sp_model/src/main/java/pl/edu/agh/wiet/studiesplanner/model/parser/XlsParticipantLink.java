package pl.edu.agh.wiet.studiesplanner.model.parser;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("P")
public class XlsParticipantLink extends XlsLink {
    public XlsParticipantLink() {}
    public XlsParticipantLink(@NotNull String path) {
        super(path);
    }
}
