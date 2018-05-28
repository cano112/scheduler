package pl.edu.agh.wiet.studiesplanner.model.parser;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("XT")
public class XlsTeacherLink extends XlsLink {
    public XlsTeacherLink() {}
    public XlsTeacherLink(@NotNull String path) {
        super(path);
    }
}
