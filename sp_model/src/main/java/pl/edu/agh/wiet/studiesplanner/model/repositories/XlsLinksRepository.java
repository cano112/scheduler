package pl.edu.agh.wiet.studiesplanner.model.repositories;

import org.springframework.data.jpa.repository.Query;
import pl.edu.agh.wiet.studiesplanner.model.parser.XlsLink;

import java.util.stream.Stream;

public interface XlsLinksRepository extends LinksRepository<XlsLink> {

    @Query("SELECT l FROM XlsScheduleLink l WHERE TYPE(l) = XlsScheduleLink")
    Stream<XlsLink> streamScheduleLinks();

    @Query("SELECT l FROM XlsParticipantLink l WHERE TYPE(l) = XlsParticipantLink")
    Stream<XlsLink> streamParticipantLinks();

    @Query("SELECT l FROM XlsTeacherLink l WHERE TYPE(l) = XlsTeacherLink")
    Stream<XlsLink> streamTeacherLinks();

    @Query("SELECT l FROM XlsEventLink l WHERE TYPE(l) = XlsEventLink")
    Stream<XlsLink> streamEventLinks();
}
