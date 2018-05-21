package pl.edu.agh.wiet.studiesplanner.model.parser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name="XLS_LINKS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE",
        discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("D")
public abstract class XlsLink extends DocumentLink {
    protected XlsLink() {}
    protected XlsLink(@NotNull String url) {
        super(url);
    }
}
