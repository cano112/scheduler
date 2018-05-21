package pl.edu.agh.wiet.studiesplanner.model.repositories;
import org.springframework.data.jpa.repository.Query;
import pl.edu.agh.wiet.studiesplanner.model.parser.GoogleDocsLink;
import pl.edu.agh.wiet.studiesplanner.model.parser.GoogleDocsScheduleLink;

import java.util.stream.Stream;

public interface GoogleDocsLinksRepository extends LinksRepository<GoogleDocsLink> {

    @Query("SELECT l FROM GoogleDocsScheduleLink l WHERE TYPE(l) = GoogleDocsScheduleLink")
    Stream<GoogleDocsLink> streamScheduleLinks();

    @Query("SELECT l FROM GoogleDocsParticipantLink l WHERE TYPE(l) = GoogleDocsParticipantLink")
    Stream<GoogleDocsLink> streamParticipantLinks();

    @Query("SELECT l FROM GoogleDocsParticipantLink l WHERE TYPE(l) = GoogleDocsTeacherLink")
    Stream<GoogleDocsLink> streamTeacherLinks();
}
