package pl.edu.agh.wiet.studiesplanner.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;


@Service
public class EmailSenderImpl implements EmailSender{

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(Email email) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo((String[]) email.getTo().toArray());
            helper.setCc((String[]) email.getCc().toArray());
            helper.setFrom(email.getFrom());
            helper.setSubject(email.getSubject());
            helper.setText(email.getMessage(), email.isHtml());
            for(File attachment: email.getAttachments()){
                helper.addAttachment(attachment.getName(), attachment);
            }

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mail);
    }
}
