package pl.edu.agh.wiet.studiesplanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.edu.agh.wiet.studiesplanner.model.parser.GoogleDocsParticipantLink;
import pl.edu.agh.wiet.studiesplanner.model.parser.GoogleDocsScheduleLink;
import pl.edu.agh.wiet.studiesplanner.model.parser.GoogleDocsTeacherLink;
import pl.edu.agh.wiet.studiesplanner.model.repositories.LinksRepository;

import javax.transaction.Transactional;

/**
 * Temporary class for filling db with mockup data
 */
@Component
@Transactional
public class MockupDbCreator implements ApplicationListener<ContextRefreshedEvent> {

    private static Logger logger = LoggerFactory.getLogger(MockupDbCreator.class);
    private final LinksRepository linksRepository;



    @Autowired
    public MockupDbCreator(LinksRepository linksRepository) {
        this.linksRepository = linksRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("Creating mockup db");
        GoogleDocsScheduleLink link1 = new GoogleDocsScheduleLink("https://docs.google.com/spreadsheets/d/1ePzPMZ84OtHed5tsU3Y4oV09ekuMS3I7bi8ojYds2u0/edit?usp=sharing");
        GoogleDocsParticipantLink link2 = new GoogleDocsParticipantLink("https://docs.google.com/spreadsheets/d/1BpUiCHBonM9Ai6h0ME38z58webex5vFHPOc0QaY1aW0");
        GoogleDocsTeacherLink link3 = new GoogleDocsTeacherLink("https://docs.google.com/spreadsheets/d/1SVVOYJA5YUK6Zg3b-aZdWauA8ZtwV-rj-m5VkWK0DQ0/edit?usp=sharing");
        linksRepository.save(link1);
        linksRepository.save(link2);
        linksRepository.save(link3);
    }
}
