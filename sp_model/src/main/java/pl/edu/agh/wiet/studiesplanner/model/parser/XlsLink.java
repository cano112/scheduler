package pl.edu.agh.wiet.studiesplanner.model.parser;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity(name="XLS_LINKS")
public class XlsLink extends DocumentLink {

    @Column
    @NotNull
    private String path;

    public XlsLink(@NotNull String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
