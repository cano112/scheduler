package pl.edu.agh.wiet.studiesplanner.model.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface DocumentFetcher {
    List<List<Object>> getDocument(String url) throws GeneralSecurityException, IOException;
}
