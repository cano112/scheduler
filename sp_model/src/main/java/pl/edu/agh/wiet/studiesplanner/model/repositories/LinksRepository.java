package pl.edu.agh.wiet.studiesplanner.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.agh.wiet.studiesplanner.model.parser.DocumentLink;

import java.util.stream.Stream;

@Repository
public interface LinksRepository extends JpaRepository<DocumentLink, Long> {

    @Query("SELECT l FROM DocumentLink l WHERE TYPE(l) = GoogleDocsTeacherLink OR TYPE(l) = XlsTeacherLink")
    Stream<DocumentLink> streamAllTeacherLinks();

    @Query("SELECT l FROM DocumentLink l WHERE TYPE(l) = GoogleDocsScheduleLink OR TYPE(l) = XlsScheduleLink")
    Stream<DocumentLink> streamAllScheduleLinks();

    @Query("SELECT l FROM DocumentLink l WHERE TYPE(l) = GoogleDocsParticipantLink OR TYPE(l) = XlsParticipantLink")
    Stream<DocumentLink> streamAllParticipantsLinks();
}
