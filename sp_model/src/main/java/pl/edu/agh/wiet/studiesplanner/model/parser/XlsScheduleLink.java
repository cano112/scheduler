package pl.edu.agh.wiet.studiesplanner.model.parser;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("S")
public class XlsScheduleLink extends XlsLink {
    public XlsScheduleLink() {}
    public XlsScheduleLink(@NotNull String path) {
        super(path);
    }
}
