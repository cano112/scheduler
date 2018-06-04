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
    private static final String INFO_HEADER = "Success!";
    private static final String WARNING_HEADER = "Warning!";

    public void showErrorMessage(String message) {
        Notification notification = new Notification(ERROR_HEADER, message, Notification.Type.ERROR_MESSAGE);
        notification.setPosition(NOTIFICATION_POSITION);
        notification.show(Page.getCurrent());
    }

    public void showInfoMessage(String message) {
        Notification notification = new Notification(INFO_HEADER, message, Notification.Type.HUMANIZED_MESSAGE);
        notification.setPosition(NOTIFICATION_POSITION);
        notification.show(Page.getCurrent());
    }

    public void showWarningMessage(String message) {
        Notification notification = new Notification(WARNING_HEADER, message, Notification.Type.WARNING_MESSAGE);
        notification.setPosition(NOTIFICATION_POSITION);
        notification.show(Page.getCurrent());
    }

}
