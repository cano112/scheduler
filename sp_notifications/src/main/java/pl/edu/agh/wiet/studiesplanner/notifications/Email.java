package pl.edu.agh.wiet.studiesplanner.notifications;

import oracle.jrockit.jfr.StringConstantPool;

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

    public static class EmailBuilder{
        private List<String> to;
        private List<String> cc;
        private String subject;
        private StringBuilder messageBuilder;
        private List<File> attachments;
        private boolean isHtml;

        public EmailBuilder(){
            to = new ArrayList<>();
            cc = new ArrayList<>();
            messageBuilder = new StringBuilder();
            attachments = new ArrayList<>();
        }

        public EmailBuilder to(String to){
            this.to.add(to);
            return this;
        }

        public EmailBuilder to(List<String> to){
            this.to.addAll(to);
            return this;
        }

        public EmailBuilder cc(String cc){
            this.cc.add(cc);
            return this;
        }

        public EmailBuilder cc(List<String> cc){
            this.cc.addAll(cc);
            return this;
        }

        public EmailBuilder subject(String subject){
            this.subject = subject;
            return this;
        }

        public EmailBuilder message(String message){
            this.messageBuilder.append(message);
            return this;
        }

        public EmailBuilder attachments(List<File> attachments){
            this.attachments.addAll(attachments);
            return this;
        }

        public EmailBuilder isHtml(boolean isHtml){
            this.isHtml = isHtml;
            return this;
        }

        public Email build(){
            return new Email(to, cc, subject, messageBuilder.toString(), isHtml, attachments);
        }
    }

    private Email(List<String> to, List<String> cc, String subject, String message, boolean isHtml, List<File> attachments){
        this.to = to;
        this.cc = cc;
        this.subject = subject;
        this.message = message;
        this.isHtml = isHtml;
        this.attachments = attachments;
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
