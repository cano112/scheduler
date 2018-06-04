package pl.edu.agh.wiet.studiesplanner.model.parser;

import java.util.Objects;

public class Warning {
    private final String message;

    public Warning(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warning warning = (Warning) o;
        return Objects.equals(message, warning.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }
}
