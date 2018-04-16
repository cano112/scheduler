package pl.edu.agh.wiet.studiesplanner.gui.views.login;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import pl.edu.agh.wiet.studiesplanner.gui.service.LoginService;
import pl.edu.agh.wiet.studiesplanner.gui.service.NotificationService;
import pl.edu.agh.wiet.studiesplanner.gui.ui.LoginUI;

import javax.annotation.PostConstruct;

@SpringView(name = LoginView.VIEW_NAME, ui = {LoginUI.class})
public class LoginView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "";

    private final LoginService loginService;
    private final NotificationService notificationService;

    @Autowired
    public LoginView(LoginService loginService, NotificationService notificationService) {
        this.loginService = loginService;
        this.notificationService = notificationService;
    }

    @PostConstruct
    void init() {
        addLoginForm();
    }

    private void addLoginForm() {
        LoginForm loginForm = new LoginForm();
        loginForm.addLoginListener(e -> {
            try {
                login(e.getLoginParameter("username"), e.getLoginParameter("password"));
            } catch (BadCredentialsException ex) {
                notificationService.showErrorMessage("Wrong username/password.");
            }
        });
        addComponent(loginForm);
    }

    private void login(String username, String password) {
        loginService.login(username, password);
        UI.getCurrent().getPage().setLocation("/app");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
