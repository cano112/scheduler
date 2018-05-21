package pl.edu.agh.wiet.studiesplanner.model.parser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name="GOOGLE_DOCS_LINKS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE",
        discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("D")
public abstract class GoogleDocsLink extends DocumentLink {
    protected GoogleDocsLink() {}
    protected GoogleDocsLink(@NotNull String url) {
        super(url);
    }
}
