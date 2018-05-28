package pl.edu.agh.wiet.studiesplanner.parser.services;

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

import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.model.service.SheetDownloader;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Service("googleSheetDownloader")
public class GoogleSheetDownloader implements SheetDownloader {
    private static final String APPLICATION_NAME = "studies-planner";
    private static final String AUTHORIZATION_KEY = "/google-sheet-b4da9677a9b6.json";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);

    public List<List<Object>> download(String url) throws GeneralSecurityException, IOException {
        final String spreadsheetId = spreadsheetIdFromURL(url);
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
        return response.getValues();
    }

    private String spreadsheetIdFromURL(String url) throws IllegalArgumentException{
        final String GOOGLE_DOCS_BASE = "https://docs.google.com/spreadsheets/d/";
        if(!url.contains(GOOGLE_DOCS_BASE))
            throw new IllegalArgumentException();
        url = url.replace(GOOGLE_DOCS_BASE, "");
        return url.split("/")[0];
    }

    @Override
    public List<List<Object>> getDocument(String url) throws GeneralSecurityException, IOException {
        return download(url);
    }
}
