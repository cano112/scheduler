package pl.edu.agh.wiet.studiesplanner.gui.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.wiet.studiesplanner.gui.service.LoginService;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Theme("valo")
@SpringUI(path = "/app")
public class MainUI extends BaseUI {

    private final LoginService loginService;
    TextField scheduleDatabaseNameField;
    TextField personalInfDatabaseNameField;
    List<String> plans;

    @Autowired
    public MainUI(GenericViewDisplay viewDisplay, LoginService loginService) {
        super(viewDisplay);
        this.loginService = loginService;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout root = new VerticalLayout();
        setContent(root);

        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        navigationBar.addComponent(createLogoutButton());

//        final CssLayout addDatabase = new CssLayout();
        plans = Arrays.asList("plan0", "plan1", "plan2");

        FormLayout elem1 = new FormLayout();
        FormLayout form = new FormLayout();
        CssLayout add1 = new CssLayout();
        scheduleDatabaseNameField = new TextField("GOOGLE DOCS scheduler ");
        add1.addComponent(scheduleDatabaseNameField);
        add1.addComponent(createAddScheduleButton());
        form.addComponent(add1);



        Grid<String> grid = new Grid<>();
        grid.setItems(plans);
        grid.addColumn(String::toString).setCaption("Name");
        form.addComponent(grid);
        elem1.addComponent(form);

        FormLayout form2 = new FormLayout();
        CssLayout add2 = new CssLayout();
        personalInfDatabaseNameField = new TextField("GOOGLE DOCS personal inf.");
        add2.addComponent(personalInfDatabaseNameField);
        add2.addComponent(createPersonalInfButton());
        form2.addComponent(add2);

        List<String> personalInf = Arrays.asList("person0", "person1", "person2");
        Grid<String> grid2 = new Grid<>();
        grid2.setItems(personalInf);
        grid2.addColumn(String::toString).setCaption("Name");
        form2.addComponent(grid2);
        elem1.addComponent(form2);

        root.addComponent(navigationBar);
        root.addComponent(elem1);
        root.addComponent(viewDisplay);
        root.setExpandRatio(viewDisplay, 1.0f);


    }

    private Button createLogoutButton() {
        Button button = new Button("Logout");
        button.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button.addClickListener(e -> logout());
        return button;
    }

    private Button createAddScheduleButton() {
        Button button = new Button("+");
        button.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button.addClickListener(e -> addSchedule());
        return button;
    }

    private Button createPersonalInfButton() {
        Button button = new Button("+");
        button.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button.addClickListener(e -> addPersonalInf());
        return button;
    }

    private void addSchedule() {
        scheduleDatabaseNameField.getValue();
        UI.getCurrent().getPage().setLocation("/app");
    }

    private void addPersonalInf() {
        scheduleDatabaseNameField.getValue();
        UI.getCurrent().getPage().setLocation("/app");
    }


    private void logout() {
        loginService.logout();
        UI.getCurrent().getPage().setLocation("/login");
    }

}
