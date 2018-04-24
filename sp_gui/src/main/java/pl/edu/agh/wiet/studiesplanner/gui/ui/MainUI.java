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

    private final String APP_HEIGHT = "100%";
    private final LoginService loginService;


    @Autowired
    public MainUI(GenericViewDisplay viewDisplay, LoginService loginService) {
        super(viewDisplay);
        this.loginService = loginService;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout root = new VerticalLayout();
        root.setHeight(APP_HEIGHT);
        setContent(root);

        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        navigationBar.addComponent(createLogoutButton());

        root.addComponent(navigationBar);
        root.addComponent(viewDisplay);
        root.setExpandRatio(viewDisplay, 1.0f);
    }

    private Button createLogoutButton() {
        Button button = new Button("Logout");
        button.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button.addClickListener(e -> logout());
        return button;
    }

    private void logout() {
        loginService.logout();
        UI.getCurrent().getPage().setLocation("/login");
    }

}
