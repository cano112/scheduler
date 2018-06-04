package pl.edu.agh.wiet.studiesplanner.model.parser.google;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("GE")
public class GoogleDocsEventLink extends GoogleDocsLink {
    public GoogleDocsEventLink() {}
    public GoogleDocsEventLink(@NotNull String url) {
        super(url);
    }
}
