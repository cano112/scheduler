package pl.edu.agh.wiet.studiesplanner.model.parser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name="GOOGLE_DOCS_LINKS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE",
        discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("D")
public abstract class GoogleDocsLink extends DocumentLink {

    @Column
    @NotNull
    private String url;

    protected GoogleDocsLink() {}
    protected GoogleDocsLink(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
