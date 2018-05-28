package pl.edu.agh.wiet.studiesplanner.model.parser;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface Fetchable {
    List<List<Object>> fetch() throws GeneralSecurityException, IOException;
}
