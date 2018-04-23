package pl.edu.agh.wiet.studiesplanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.edu.agh.wiet.studiesplanner.model.parser.DocumentLink;
import pl.edu.agh.wiet.studiesplanner.model.parser.GoogleDocsLink;
import pl.edu.agh.wiet.studiesplanner.model.parser.XlsLink;
import pl.edu.agh.wiet.studiesplanner.model.repositories.GoogleDocsLinksRepository;
import pl.edu.agh.wiet.studiesplanner.model.repositories.XlsLinksRepository;

import javax.transaction.Transactional;
import java.util.Arrays;

@Component
@Transactional
public class MockupDbCreator implements ApplicationListener<ContextRefreshedEvent> {

    private static Logger logger = LoggerFactory.getLogger(MockupDbCreator.class);
    private final GoogleDocsLinksRepository googleDocsLinksRepository;
    private final XlsLinksRepository xlsLinksRepository;



    @Autowired
    public MockupDbCreator(GoogleDocsLinksRepository googleDocsLinksRepository, XlsLinksRepository xlsLinksRepository) {
        this.googleDocsLinksRepository = googleDocsLinksRepository;
        this.xlsLinksRepository = xlsLinksRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("Creating mockup db");
        GoogleDocsLink link1 = new GoogleDocsLink("https://docs.google.com/spreadsheets/d/1lu5vacNbFj_5mwQR9MA1CrmSSnfvSnWDA2AhaO5gg9c/edit#gid=0");
        XlsLink link2 = new XlsLink("sheet.xls");
        googleDocsLinksRepository.save(link1);
        xlsLinksRepository.save(link2);
    }
}
