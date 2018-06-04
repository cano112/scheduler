package pl.edu.agh.wiet.studiesplanner.model.parser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE",
        discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("D")
public abstract class DocumentLink implements Fetchable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    private String url;

    public abstract void onDelete() throws IOException;

    public DocumentLink() {}

    public DocumentLink(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
