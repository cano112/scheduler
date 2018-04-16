package pl.edu.agh.wiet.studiesplanner.gui.views.main;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import pl.edu.agh.wiet.studiesplanner.gui.ui.MainUI;

import javax.annotation.PostConstruct;

@SpringView(name = MainView.VIEW_NAME, ui = {MainUI.class})
public class MainView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "";

    @PostConstruct
    void init() {
        addComponent(new Label("Tu bedzie aplikacja"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }
}
