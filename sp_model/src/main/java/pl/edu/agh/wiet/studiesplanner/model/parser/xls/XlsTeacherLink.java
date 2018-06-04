package pl.edu.agh.wiet.studiesplanner.model.parser.xls;

import pl.edu.agh.wiet.studiesplanner.model.DirectoryConfig;
import pl.edu.agh.wiet.studiesplanner.model.parser.xls.XlsLink;
import pl.edu.agh.wiet.studiesplanner.model.service.LinksService;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Entity
@DiscriminatorValue("XT")
public class XlsTeacherLink extends XlsLink {
    public XlsTeacherLink() {}
    public XlsTeacherLink(@NotNull String path) {
        super(path);
    }
}
