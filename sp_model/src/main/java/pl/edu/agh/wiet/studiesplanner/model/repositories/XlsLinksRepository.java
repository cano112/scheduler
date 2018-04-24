package pl.edu.agh.wiet.studiesplanner.model.repositories;

import org.springframework.data.jpa.repository.Query;
import pl.edu.agh.wiet.studiesplanner.model.parser.GoogleDocsLink;
import pl.edu.agh.wiet.studiesplanner.model.parser.XlsLink;

import java.util.stream.Stream;

public interface XlsLinksRepository extends LinksRepository<XlsLink> {

    @Query("SELECT l FROM XlsScheduleLink l WHERE TYPE(l) = XlsScheduleLink")
    Stream<GoogleDocsLink> streamScheduleLinks();

    @Query("SELECT l FROM XlsParticipantLink l WHERE TYPE(l) = XlsParticipantLink")
    Stream<GoogleDocsLink> streamParticipantLinks();
}
