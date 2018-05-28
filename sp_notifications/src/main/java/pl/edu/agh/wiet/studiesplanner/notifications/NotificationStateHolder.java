package pl.edu.agh.wiet.studiesplanner.notifications;

import org.springframework.stereotype.Component;

@Component
public class NotificationStateHolder {

    private boolean notificationsEnabled;

    public NotificationStateHolder() {
        this.notificationsEnabled = false;
    }

    public void setNotificationsOn() {
        this.notificationsEnabled = true;
    }

    public void setNotificationsOff() {
        this.notificationsEnabled = false;
    }

    public void switchNotifications() {
        this.notificationsEnabled = !this.notificationsEnabled;
    }

    public boolean areNotificationsEnabled() {
        return this.notificationsEnabled;
    }
}
