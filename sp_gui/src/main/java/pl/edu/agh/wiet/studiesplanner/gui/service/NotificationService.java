package pl.edu.agh.wiet.studiesplanner.gui.service;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    //TODO: move to config file
    private static final Position NOTIFICATION_POSITION = Position.TOP_LEFT;
    private static final String ERROR_HEADER = "Error occurred";

    public void showErrorMessage(String message) {
        Notification notification = new Notification(ERROR_HEADER, message, Notification.Type.ERROR_MESSAGE);
        notification.setPosition(NOTIFICATION_POSITION);
        notification.show(Page.getCurrent());
    }
}
