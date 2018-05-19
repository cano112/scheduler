package pl.edu.agh.wiet.studiesplanner.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.model.data.Activity;

import java.util.List;

@Service
public class Notifier {
    @Autowired
    NotificationSender notificationSender;

    @Scheduled(cron="0 0 8 * * *")
    public void sendNotifications() {
        for(Activity activity: getActivitiesToNotify()) {
            notificationSender.sendNotificationToTeacher(activity);
            notificationSender.sendNotificationToStudents(activity);
        }
    }

    private List<Activity> getActivitiesToNotify(){
        //TODO
        return null;
    }
}
