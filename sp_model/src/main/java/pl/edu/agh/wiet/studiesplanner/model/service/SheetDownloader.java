package pl.edu.agh.wiet.studiesplanner.model.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface SheetDownloader extends DocumentFetcher {
    List<List<Object>> download(String url) throws GeneralSecurityException, IOException;
}
