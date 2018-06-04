package pl.edu.agh.wiet.studiesplanner.gui;

import com.vaadin.server.Sizeable;

public enum SizeVariables {
    NAV_BAR_HEIGHT(60, Sizeable.Unit.PIXELS);

    private int value;
    private Sizeable.Unit unit;

    SizeVariables(int value, Sizeable.Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public int getValue() {
        return value;
    }

    public Sizeable.Unit getUnit() {
        return unit;
    }
}
