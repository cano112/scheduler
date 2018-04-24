package pl.edu.agh.wiet.studiesplanner.gui.views.main;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;


public abstract class AppAbstractComponent extends CustomComponent {
    protected void init(String componentName, String width) {
        Panel panel = new Panel(componentName);
        Layout layout = getContent();
        panel.setContent(layout);
        setCompositionRoot(panel);
        setWidth(width);
        panel.setWidth("100%");
        layout.setWidth("100%");
    }

    protected abstract Layout getContent();
}
