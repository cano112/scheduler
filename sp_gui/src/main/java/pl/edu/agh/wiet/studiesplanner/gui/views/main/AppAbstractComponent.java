package pl.edu.agh.wiet.studiesplanner.gui.views.main;

import com.vaadin.server.Page;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import pl.edu.agh.wiet.studiesplanner.gui.SizeVariables;

public abstract class AppAbstractComponent extends CustomComponent {
    protected void init(String componentName, String width) {
        Panel panel = new Panel(componentName);
        Layout layout = getContent();
        panel.setContent(layout);
        setCompositionRoot(panel);
        setWidth(width);
        panel.setWidth("100%");
        panel.setHeight(Page.getCurrent().getBrowserWindowHeight() - SizeVariables.NAV_BAR_HEIGHT.getValue() - 37*2 - 72,
                SizeVariables.NAV_BAR_HEIGHT.getUnit());
        layout.setWidth("100%");
        layout.setHeightUndefined();
    }

    protected abstract Layout getContent();
}
