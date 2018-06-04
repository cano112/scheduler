package pl.edu.agh.wiet.studiesplanner.model.data;

/**
 * Created by Michał on 22.04.2018.
 */
public enum ActivityType {
    W("Wykład"),
    L("Laboratorium"),
    C("Ćwiczenia"),
    K("Konferencja");

    private final String name;

    ActivityType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
