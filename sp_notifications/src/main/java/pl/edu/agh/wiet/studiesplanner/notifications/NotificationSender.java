package pl.edu.agh.wiet.studiesplanner.notifications;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.model.data.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NotificationSender extends EmailSenderImpl {

    public void sendNotificationToTeacher(List<Pair<Activity, LocalDateTime>> activities){
        if (activities.size() == 0){
            return;
        }
        StringBuilder stringBuilder = new StringBuilder("Teacher Notification: ");
        Teacher teacher = activities.get(0).getFirst().getTeacher();
        List<File> attachments = new ArrayList<>();

        try{
            LocalDateTime beginDate = activities.get(0).getSecond();
            LocalDateTime endDate = beginDate;
            for (Pair<Activity, LocalDateTime> pair : activities) {
                stringBuilder.append(pair.getSecond().toString()).append(" ")
                        .append(pair.getFirst().getType().getName()).append(" ")
                        .append(pair.getFirst().getSubject().getName()).append("\n");
                attachments.add(createStudentList(pair.getFirst(), pair.getSecond()));
                if (pair.getSecond().isBefore(beginDate)) {
                    beginDate = pair.getSecond();
                }
                if (pair.getSecond().isAfter(endDate)) {
                    endDate = pair.getSecond();
                }
            }

            Email email = new Email.EmailBuilder()
                    .to(teacher.getEmail())
                    .subject("Studies Planner notification for weekend " + beginDate.toLocalDate().toString()
                            + " to " + endDate.toLocalDate().toString())
                    .message(stringBuilder.toString())
                    .isHtml(false)
                    .attachments(attachments)
                    .build();

            sendEmail(email);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNotificationToStudents(Activity activity) {
        Email email = new Email.EmailBuilder()
                .to(activity.getStudentsGroup().getStudents().stream().map(Student::getEmail).collect(Collectors.toList()))
                .subject("Studies Planner notification")
                .message("Student Notification: " + activity.getType().getName() + " " + activity.getSubject().getName())
                .isHtml(false)
                .build();
        sendEmail(email);
    }

    private File createStudentList(Activity activity, LocalDateTime date) throws IOException {
        File temp = File.createTempFile(date.toString()
                .replace("T", " ")
                .replace(":", "_")
                .replaceFirst("\\.[0-9]+", ""),
                ".txt");
        PrintWriter printWriter = new PrintWriter(temp);
        List<Student> studentList = activity.getStudentsGroup().getStudents();
        printWriter.println("Student list for '" + activity.getSubject().getName() + "' " +
                activity.getType().getName() + " at " +
                date.toString().replace("T", " ")
                .replaceFirst("\\.[0-9]+", ""));
        for(Student student: studentList) {
            printWriter.println(student.getId() + ";" + student.getFullName());
        }
        printWriter.close();
        return temp;
    }

}
