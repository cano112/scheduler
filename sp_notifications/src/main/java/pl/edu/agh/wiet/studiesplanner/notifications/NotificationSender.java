package pl.edu.agh.wiet.studiesplanner.notifications;

import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.model.data.Activity;
import pl.edu.agh.wiet.studiesplanner.model.data.Student;
import pl.edu.agh.wiet.studiesplanner.model.data.Teacher;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
                    Arrays.asList(studentList));
            sendEmail(email);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNotificationToStudents(Activity activity) {
        Email email = new Email(
                activity.getStudentsGroup().getStudents().stream().map(Student::getEmail).collect(Collectors.toList()),
                new ArrayList<String>(),
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
