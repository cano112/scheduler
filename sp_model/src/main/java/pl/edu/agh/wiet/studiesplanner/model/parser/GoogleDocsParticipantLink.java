package pl.edu.agh.wiet.studiesplanner.model.parser;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("GP")
public class GoogleDocsParticipantLink extends GoogleDocsLink {
    public GoogleDocsParticipantLink() {}
    public GoogleDocsParticipantLink(@NotNull String url) {
        super(url);
    }
}
