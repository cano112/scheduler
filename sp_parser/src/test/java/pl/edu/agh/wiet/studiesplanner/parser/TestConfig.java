package pl.edu.agh.wiet.studiesplanner.parser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.wiet.studiesplanner.model.data.Convention;
import pl.edu.agh.wiet.studiesplanner.model.data.Schedule;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Configuration
@ComponentScan(basePackages = {"pl.edu.agh.wiet.studiesplanner.parser"})
public class TestConfig {

    private String URL = "https://docs.google.com/spreadsheets/d/1lu5vacNbFj_5mwQR9MA1CrmSSnfvSnWDA2AhaO5gg9c";

    @Bean
    public Schedule schedule() throws GeneralSecurityException, IOException {
        GoogleSheetDownloader downloader = new GoogleSheetDownloader();
        ScheduleSheetParser parser = new ScheduleSheetParser();
        List<List<Object>> downloaded = downloader.download(URL);
        return parser.parse(downloaded);
    }
}
