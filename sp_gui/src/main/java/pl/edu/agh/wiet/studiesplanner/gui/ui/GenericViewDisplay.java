package pl.edu.agh.wiet.studiesplanner.gui.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;

@SpringViewDisplay
public class GenericViewDisplay extends Panel implements ViewDisplay {

    public GenericViewDisplay() {
        setStyleName(ValoTheme.PANEL_BORDERLESS);
        setSizeFull();
    }

    @Override
    public void showView(View view) {
        setContent((Component) view);
    }
}
