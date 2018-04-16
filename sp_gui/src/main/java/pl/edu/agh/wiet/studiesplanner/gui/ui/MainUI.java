package pl.edu.agh.wiet.studiesplanner.gui.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.wiet.studiesplanner.gui.service.LoginService;

@Theme("valo")
@SpringUI(path="/app")
public class MainUI extends BaseUI {

    private final LoginService loginService;

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
