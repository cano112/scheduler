package pl.edu.agh.wiet.studiesplanner.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.model.data.*;
import pl.edu.agh.wiet.studiesplanner.model.repositories.LinksRepository;
import pl.edu.agh.wiet.studiesplanner.parser.services.SheetsParserFacade;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@Transactional
public class Notifier {
    private final NotificationSender notificationSender;
    private final SheetsParserFacade sheetsParser;
    private final LinksRepository linksRepository;
    private final NotificationStateHolder stateHolder;

    @Autowired
    public Notifier(NotificationSender notificationSender,
                    SheetsParserFacade sheetsParser,
                    LinksRepository linksRepository,
                    NotificationStateHolder stateHolder) {
        this.notificationSender = notificationSender;
        this.sheetsParser = sheetsParser;
        this.linksRepository = linksRepository;
        this.stateHolder = stateHolder;
    }

    @Scheduled(cron="0 0 8 * * *")
    public void sendNotifications() {
        if(stateHolder.areNotificationsEnabled()) {
            Schedule schedule = getSchedule();
            List<Teacher> teachers = schedule.getTeachers();
            List<Convention> closestConventions = new ArrayList<>();
            for (Convention convention : schedule.getConventions()){
                if (isInTheWeekFromNow(convention.getTimeBlocks().get(0).getTimeStart())){
                    closestConventions.add(convention);
                }
            }
            for (Convention convention : closestConventions){
                Map<Teacher, List<Pair<Activity, LocalDateTime>>> map = getTeacherActivitiesInAConvention(convention, teachers);
                for (List<Pair<Activity, LocalDateTime>> pairs : map.values()){
                    notificationSender.sendNotificationToTeacher(pairs);
                }
            }
        }
    }

    private Map<Teacher, List<Pair<Activity, LocalDateTime>>> getTeacherActivitiesInAConvention(Convention convention, List<Teacher> teachers){
        Map<Teacher, List<Pair<Activity, LocalDateTime>>> map = new HashMap<>();
        teachers.forEach(t -> map.put(t, new LinkedList<>()));
        convention.getTimeBlocks()
                .forEach(tb -> tb.getActivityList().
                        forEach(a -> map.get(a.getTeacher()).add(Pair.of(a, tb.getTimeStart()))));
        return map;
    }

    private boolean isInTheWeekFromNow(LocalDateTime date){
        return date.minus(7, ChronoUnit.DAYS).isBefore(LocalDateTime.now());
    }

    private Schedule getSchedule() {
        return sheetsParser.parse(
                linksRepository.streamAllScheduleLinks(),
                linksRepository.streamAllParticipantsLinks(),
                linksRepository.streamAllTeacherLinks());
    }

}
