package pl.edu.agh.wiet.studiesplanner.model.parser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity(name="GOOGLE_DOCS_LINKS")
public class GoogleDocsLink extends DocumentLink {

    @Column
    @NotNull
    private String url;

    public GoogleDocsLink(@NotNull String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
