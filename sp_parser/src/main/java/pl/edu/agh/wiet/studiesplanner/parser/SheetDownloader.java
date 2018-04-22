package pl.edu.agh.wiet.studiesplanner.parser;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface SheetDownloader {
    public SheetDownloader download() throws GeneralSecurityException, IOException;
    public List<List<Object>> getValues();
}
