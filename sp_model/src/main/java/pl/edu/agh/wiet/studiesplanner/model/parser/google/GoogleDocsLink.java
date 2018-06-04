package pl.edu.agh.wiet.studiesplanner.model.parser.google;

import pl.edu.agh.wiet.studiesplanner.model.parser.DocumentLink;
import pl.edu.agh.wiet.studiesplanner.model.parser.FetchStrategy;
import pl.edu.agh.wiet.studiesplanner.model.service.LinksService;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Entity
@DiscriminatorValue("G")
public abstract class GoogleDocsLink extends DocumentLink {
    protected GoogleDocsLink() {}
    protected GoogleDocsLink(@NotNull String url) {
        super(url);
    }

    @Override
    public List<List<Object>> fetch() throws GeneralSecurityException, IOException {
        return LinksService
                .getFetchStrategy(FetchStrategy.GOOGLE_DOCS)
                .getDocument(this.getUrl());
    }

    @Override
    public void onDelete() {}
}
