package pl.edu.agh.wiet.studiesplanner.model.parser;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("GT")
public class GoogleDocsTeacherLink extends GoogleDocsLink {
    public GoogleDocsTeacherLink() {}
    public GoogleDocsTeacherLink(@NotNull String url) {
        super(url);
    }
}
