package pl.edu.agh.wiet.studiesplanner.model.parser;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("E")
public class XlsEventLink extends XlsLink {
    public XlsEventLink() {}
    public XlsEventLink(@NotNull String path) {
        super(path);
    }
}
