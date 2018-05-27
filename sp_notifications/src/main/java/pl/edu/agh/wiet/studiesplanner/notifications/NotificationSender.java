package pl.edu.agh.wiet.studiesplanner.notifications;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.model.data.Activity;
import pl.edu.agh.wiet.studiesplanner.model.data.Student;
import pl.edu.agh.wiet.studiesplanner.model.data.Teacher;
import pl.edu.agh.wiet.studiesplanner.model.data.TimeBlock;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NotificationSender extends EmailSenderImpl{
    public void sendNotificationToTeacher(Activity activity) {
        try {
            File studentList = createStudentList(activity.getStudentsGroup().getStudents());
            Email email = new Email(
                    activity.getTeacher().getEmail(),
                    "Studies Planner notification",
                    "Teacher Notification: " + activity.getType().getName() + " " + activity.getSubject().getName(),
                    false,
                    Collections.singletonList(studentList));
            sendEmail(email);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNotificationToTeacher(List<Pair<Activity, LocalDateTime>> activities){
        if (activities.size() == 0){
            return;
        }
        StringBuilder stringBuilder = new StringBuilder("Teacher Notification: ");
        Teacher teacher = activities.get(0).getFirst().getTeacher();
        List<File> attachments = new ArrayList<>();
        try{
            for (Pair<Activity, LocalDateTime> pair : activities){
                stringBuilder.append(pair.getSecond().toString()).append(" ")
                        .append(pair.getFirst().getType().getName()).append(" ")
                        .append(pair.getFirst().getSubject().getName()).append("\n");
                attachments.add(createStudentList(pair.getFirst().getStudentsGroup().getStudents()));
            }
            Email email = new Email(
                    teacher.getEmail(),
                    "Studies Planner notification",
                    stringBuilder.toString(),
                    false,
                    attachments);
            sendEmail(email);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNotificationToStudents(Activity activity) {
        Email email = new Email(
                activity.getStudentsGroup().getStudents().stream().map(Student::getEmail).collect(Collectors.toList()),
                new ArrayList<>(),
                "Studies Planner notification",
                "Teacher Notification: " + activity.getType().getName() + " " + activity.getSubject().getName(),
                false);
        sendEmail(email);
    }

    private File createStudentList(List<Student> studentList) throws IOException {
        File temp = File.createTempFile("studies_planner", null);
        FileWriter fileWriter = new FileWriter(temp);

        for(Student student: studentList) {
            fileWriter.write(student.getId() + ";" + student.getFullName());
        }

        return temp;
    }
}
