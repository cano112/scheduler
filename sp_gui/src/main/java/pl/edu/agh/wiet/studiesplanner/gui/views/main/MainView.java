package pl.edu.agh.wiet.studiesplanner.gui.views.main;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.wiet.studiesplanner.gui.service.ConflictSolverService;
import pl.edu.agh.wiet.studiesplanner.gui.service.LinksFormService;
import pl.edu.agh.wiet.studiesplanner.gui.service.NotificationService;
import pl.edu.agh.wiet.studiesplanner.gui.ui.MainUI;
import pl.edu.agh.wiet.studiesplanner.notifications.NotificationStateHolder;

import javax.annotation.PostConstruct;

@SpringView(name = MainView.VIEW_NAME, ui = {MainUI.class})
public class MainView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "";

    private final LinksFormService linksFormService;
    private final ConflictSolverService conflictSolverService;
    private final NotificationService notificationService;
    private final NotificationStateHolder notificationStateHolder;

    @Autowired
    public MainView(LinksFormService linksFormService,
                    ConflictSolverService conflictSolverService,
                    NotificationService notificationService,
                    NotificationStateHolder notificationStateHolder) {
        this.linksFormService = linksFormService;
        this.conflictSolverService = conflictSolverService;
        this.notificationService = notificationService;
        this.notificationStateHolder = notificationStateHolder;
    }

    @PostConstruct
    void init() {
        HorizontalLayout horizontalLayout= new HorizontalLayout();
        horizontalLayout.setWidth("100%");
        Component googleDocsLinksComponent = new LinksFormComponent(linksFormService, "100%");
        Component conflictSolverComponent = new ConflictSolverComponent(conflictSolverService, notificationService, notificationStateHolder, "100%");
        horizontalLayout.addComponent(googleDocsLinksComponent);
        horizontalLayout.addComponent(conflictSolverComponent);
        addComponent(horizontalLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }
}
