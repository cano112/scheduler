package pl.edu.agh.wiet.studiesplanner.model.parser.xls;

import pl.edu.agh.wiet.studiesplanner.model.DirectoryConfig;
import pl.edu.agh.wiet.studiesplanner.model.service.LinksService;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Entity
@DiscriminatorValue("XS")
public class XlsScheduleLink extends XlsLink {
    public XlsScheduleLink() {}
    public XlsScheduleLink(@NotNull String path) {
        super(path);
    }
}
