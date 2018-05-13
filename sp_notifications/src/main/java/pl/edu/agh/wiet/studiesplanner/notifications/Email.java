package pl.edu.agh.wiet.studiesplanner.notifications;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Email {

    private String from;

    private List<String> to;

    private List<String> cc;

    private String subject;

    private String message;

    private List<File> attachments;

    private boolean isHtml;

    public Email() {
        this.to = new ArrayList<String>();
        this.cc = new ArrayList<String>();
    }

    public Email(String from, String to, String subject, String message, boolean isHtml) {
        this();
        this.from = from;
        this.subject = subject;
        this.message = message;
        this.isHtml = isHtml;
        this.to.add(to);
    }


    public Email(String from, String to, String subject, String message, boolean isHtml, List<File> attachments) {
        this(from, to, subject, message, isHtml);
        this.attachments = attachments;
    }

    public Email(String from, List<String> toList, List<String> ccList, String subject, String message, boolean isHtml) {
        this();
        this.from = from;
        this.subject = subject;
        this.message = message;
        this.isHtml = isHtml;
        this.to.addAll(toList);
        this.cc.addAll(ccList);
    }

    public Email(String from, List<String> toList, List<String> ccList, String subject, String message, boolean isHtml, List<File> attachments) {
        this(from, toList, ccList, subject, message, isHtml);
        this.attachments = attachments;
    }

    public String getFrom() {
        return from;
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
