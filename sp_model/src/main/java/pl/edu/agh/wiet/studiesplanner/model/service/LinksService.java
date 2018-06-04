package pl.edu.agh.wiet.studiesplanner.model.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.model.parser.FetchStrategy;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Transactional
public class LinksService implements ApplicationContextAware {

    private static ApplicationContext context;

    public static DocumentFetcher getFetchStrategy(FetchStrategy strategy) {
        switch(strategy) {
            case GOOGLE_DOCS:
                return (DocumentFetcher) context.getBean("googleSheetDownloader");
            case XLS:
                return (DocumentFetcher) context.getBean("excelSheetImporter");
            default:
                throw new RuntimeException("No such fetch strategy");
        }
    }

    public static void deleteFile(String path) throws IOException {
        Files.deleteIfExists(Paths.get(path));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
