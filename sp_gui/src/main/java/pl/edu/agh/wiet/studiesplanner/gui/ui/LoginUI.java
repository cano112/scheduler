package pl.edu.agh.wiet.studiesplanner.gui.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.spring.annotation.SpringUI;

@Theme("valo")
@SpringUI(path = "/login")
public class LoginUI extends BaseUI {

    public LoginUI(GenericViewDisplay viewDisplay) {
        super(viewDisplay);
    }
}
