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
        final VerticalLayout root = new VerticalLayout(new Label("Studies planner"));
        setContent(root);

        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        navigationBar.addComponent(createLogoutButton());

        plans = Arrays.asList("plan0", "plan1", "plan2");

        HorizontalLayout schedulesAndPersonalInf = new HorizontalLayout();
        FormLayout scheduleForm = new FormLayout();
        HorizontalLayout addingSchedule = new HorizontalLayout();
        scheduleDatabaseNameField = new TextField("GOOGLE DOCS schedule ");
        addingSchedule.addComponent(scheduleDatabaseNameField);
        addingSchedule.addComponent(createAddScheduleButton());
        scheduleForm.addComponent(addingSchedule);

        Grid<String> ScheduleList = new Grid<>();
        ScheduleList.setItems(plans);
        ScheduleList.addColumn(String::toString).setCaption("Name");
        scheduleForm.addComponent(ScheduleList);
        schedulesAndPersonalInf.addComponent(scheduleForm);

        FormLayout PersonalInfForm = new FormLayout();
        HorizontalLayout addingPersonalInf = new HorizontalLayout();
        personalInfDatabaseNameField = new TextField("GOOGLE DOCS personal inf.");
        addingPersonalInf.addComponent(personalInfDatabaseNameField);
        addingPersonalInf.addComponent(createPersonalInfButton());
        PersonalInfForm.addComponent(addingPersonalInf);

        List<String> personalInf = Arrays.asList("person0", "person1", "person2");
        Grid<String> PersonalInfList = new Grid<>();
        PersonalInfList.setItems(personalInf);
        PersonalInfList.addColumn(String::toString).setCaption("Name");
        PersonalInfForm.addComponent(PersonalInfList);
        schedulesAndPersonalInf.addComponent(PersonalInfForm);

        root.addComponent(navigationBar);
        root.addComponent(schedulesAndPersonalInf);
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
        button.setHeight("100%");
        button.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button.addClickListener(e -> addSchedule());
        return button;
    }

    private Button createPersonalInfButton() {
        Button button = new Button("+");
        button.setHeight("100%");
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
