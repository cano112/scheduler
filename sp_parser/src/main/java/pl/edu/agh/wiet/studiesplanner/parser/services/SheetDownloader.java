package pl.edu.agh.wiet.studiesplanner.parser.services;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface SheetDownloader {
    List<List<Object>> download(String url) throws GeneralSecurityException, IOException;
}
