package pl.edu.agh.wiet.studiesplanner.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import pl.edu.agh.wiet.studiesplanner.model.parser.DocumentLink;

import java.util.stream.Stream;


@NoRepositoryBean
public interface LinksRepository<T extends DocumentLink> extends JpaRepository<T, Long> {

    /**
     * Have to be run in transaction - use @Transactional on your service class.
     * @return stream of all links of a given type
     * @see pl.edu.agh.wiet.studiesplanner.model.parser.GoogleDocsLink
     * @see pl.edu.agh.wiet.studiesplanner.model.parser.XlsLink
     */
    @Query("SELECT l FROM #{#entityName} l")
    Stream<T> streamLinks();
}
