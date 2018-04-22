package pl.edu.agh.wiet.studiesplanner.parser;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Service
@Scope("prototype")
public class GoogleSheetDownloader implements  SheetDownloader{
    private static final String APPLICATION_NAME = "studies-planner";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private static final String AUTHORIZATION_KEY = "/google-sheet-b4da9677a9b6.json";

    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);

    private final String spreadsheetId;
    List<List<Object>> values;

    public static String spreadsheetIdFromURL(String url) throws IllegalArgumentException{
        final String GOOGLE_DOCS_BASE = "https://docs.google.com/spreadsheets/d/";
        if(!url.contains(GOOGLE_DOCS_BASE))
            throw new IllegalArgumentException();

        return url.replace(GOOGLE_DOCS_BASE, "");

    }

    public GoogleSheetDownloader(String spreadsheetId) {
        this.spreadsheetId = spreadsheetId;
    }

    public GoogleSheetDownloader download() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, GoogleCredential.fromStream(getClass().getResource(AUTHORIZATION_KEY).openStream())
                .createScoped(SCOPES))
                .setApplicationName(APPLICATION_NAME)
                .build();
        Spreadsheet sp = service.spreadsheets().get(spreadsheetId).execute();
        List<Sheet> sheets = sp.getSheets();
        System.out.println();
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, sheets.get(0).getProperties().getTitle())
                .execute();
        values = response.getValues();

        return this;
    }

    public List<List<Object>> getValues()
    {
        return values;
    }
}
