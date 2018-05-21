package pl.edu.agh.wiet.studiesplanner.gui.views.main;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.wiet.studiesplanner.gui.service.ConflictSolverService;
import pl.edu.agh.wiet.studiesplanner.gui.service.GoogleDocsLinksService;
import pl.edu.agh.wiet.studiesplanner.gui.service.NotificationService;
import pl.edu.agh.wiet.studiesplanner.gui.ui.MainUI;
import pl.edu.agh.wiet.studiesplanner.model.parser.GoogleDocsLink;

import javax.annotation.PostConstruct;

@SpringView(name = MainView.VIEW_NAME, ui = {MainUI.class})
public class MainView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "";

    private final GoogleDocsLinksService googleDocsLinksService;
    private final ConflictSolverService conflictSolverService;
    private final NotificationService notificationService;

    @Autowired
    public MainView(GoogleDocsLinksService googleDocsLinksService,
                    ConflictSolverService conflictSolverService,
                    NotificationService notificationService) {
        this.googleDocsLinksService = googleDocsLinksService;
        this.conflictSolverService = conflictSolverService;
        this.notificationService = notificationService;
    }

    @PostConstruct
    void init() {
        HorizontalLayout horizontalLayout= new HorizontalLayout();
        horizontalLayout.setWidth("100%");
        Component googleDocsLinksComponent = new GoogleDocsLinksFormComponent(googleDocsLinksService, "100%");
        Component conflictSolverComponent = new ConflictSolverComponent(conflictSolverService, notificationService, "100%");
        horizontalLayout.addComponent(googleDocsLinksComponent);
        horizontalLayout.addComponent(conflictSolverComponent);
        addComponent(horizontalLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }
}
