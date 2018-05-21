package pl.edu.agh.wiet.studiesplanner.parser.services;

import pl.edu.agh.wiet.studiesplanner.model.data.Schedule;

import java.util.List;

public interface SheetParser {
    void parse(List<List<Object>> downloadedSheet, Schedule model);
}
