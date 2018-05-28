package pl.edu.agh.wiet.studiesplanner.model.parser;

import pl.edu.agh.wiet.studiesplanner.model.service.LinksService;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Entity
@DiscriminatorValue("X")
public abstract class XlsLink extends DocumentLink {
    protected XlsLink() {}
    protected XlsLink(@NotNull String url) {
        super(url);
    }

    @Override
    public List<List<Object>> fetch() throws GeneralSecurityException, IOException {
        return LinksService
                .getFetchStrategy(FetchStrategy.XLS)
                .getDocument(this.getUrl());
    }
}
