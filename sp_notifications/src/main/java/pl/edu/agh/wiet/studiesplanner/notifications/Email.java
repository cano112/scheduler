package pl.edu.agh.wiet.studiesplanner.notifications;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Email {

    private final List<String> to;

    private final List<String> cc;

    private final String subject;

    private final String message;

    private final List<File> attachments;

    private final boolean isHtml;

    public Email(String to, String subject, String message, boolean isHtml, List<File> attachments) {
        this.to = new ArrayList<String>();
        this.cc = new ArrayList<String>();
        this.subject = subject;
        this.message = message;
        this.isHtml = isHtml;
        this.to.add(to);
        this.attachments = attachments;
    }

    public Email(String to, String subject, String message, boolean isHtml) {
        this.to = new ArrayList<String>();
        this.cc = new ArrayList<String>();
        this.subject = subject;
        this.message = message;
        this.isHtml = isHtml;
        this.to.add(to);
        this.attachments = new ArrayList<>();
    }

    public Email(List<String> toList, List<String> ccList, String subject, String message, boolean isHtml) {
        this.to = new ArrayList<String>();
        this.cc = new ArrayList<String>();
        this.subject = subject;
        this.message = message;
        this.isHtml = isHtml;
        this.to.addAll(toList);
        this.cc.addAll(ccList);
        this.attachments = new ArrayList<>();
    }

    public List<String> getTo() {
        return to;
    }

    public List<String> getCc() {
        return cc;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public boolean isHtml() {
        return isHtml;
    }

    public List<File> getAttachments() {
        return attachments;
    }
}
