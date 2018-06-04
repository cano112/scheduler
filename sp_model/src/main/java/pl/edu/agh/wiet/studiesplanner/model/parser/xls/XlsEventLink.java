package pl.edu.agh.wiet.studiesplanner.model.parser.xls;

import pl.edu.agh.wiet.studiesplanner.model.DirectoryConfig;
import pl.edu.agh.wiet.studiesplanner.model.service.LinksService;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Entity
@DiscriminatorValue("XE")
public class XlsEventLink extends XlsLink {
    public XlsEventLink() {}
    public XlsEventLink(@NotNull String path) {
        super(path);
    }
}
