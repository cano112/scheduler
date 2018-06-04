package pl.edu.agh.wiet.studiesplanner.notifications;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.model.data.Activity;
import pl.edu.agh.wiet.studiesplanner.model.data.Student;
import pl.edu.agh.wiet.studiesplanner.model.data.Teacher;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NotificationSender extends EmailSenderImpl{

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
                attachments.add(createStudentList(pair.getFirst(), pair.getSecond()));
            }
            Email email = new Email.EmailBuilder()
                    .to(teacher.getEmail())
                    .subject("Studies Planner notification")
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
        File temp = File.createTempFile(date.toString(), null);
        FileWriter fileWriter = new FileWriter(temp);
        Set<Student> studentList = activity.getStudentsGroup().getStudents();
        fileWriter.write("Student list for '" + activity.getSubject() + "' at " + date.toString() + "\n");
        for(Student student: studentList) {
            fileWriter.write(student.getId() + ";" + student.getFullName() + "\n");
        }
        return temp;
}
