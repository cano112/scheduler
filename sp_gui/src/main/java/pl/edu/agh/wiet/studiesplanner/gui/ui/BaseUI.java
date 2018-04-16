package pl.edu.agh.wiet.studiesplanner.gui.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseUI extends UI {

    protected final GenericViewDisplay viewDisplay;

    @Autowired
    public BaseUI(GenericViewDisplay viewDisplay) {
        this.viewDisplay = viewDisplay;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        setContent(root);
        root.addComponent(viewDisplay);
        root.setExpandRatio(viewDisplay, 1.0f);
    }
}
