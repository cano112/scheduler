package pl.edu.agh.wiet.studiesplanner.model.parser;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("GS")
public class GoogleDocsScheduleLink extends GoogleDocsLink {
    public GoogleDocsScheduleLink() {}
    public GoogleDocsScheduleLink(@NotNull String url) {
        super(url);
    }
}
