package pl.edu.agh.wiet.studiesplanner.gui.views.main;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import pl.edu.agh.wiet.studiesplanner.gui.service.DocumentLinksService;
import pl.edu.agh.wiet.studiesplanner.gui.service.NotificationService;
import pl.edu.agh.wiet.studiesplanner.gui.service.UploadReciver;
import pl.edu.agh.wiet.studiesplanner.model.parser.DocumentLink;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collection;

public class DocumentLinksFormComponent extends AppAbstractComponent {


    private static final double GRID_HEIGHT = 3; // in rows visible
    private static final String GRID_WIDTH = "100%";


    private DocumentLinksService linksService;

    private TextField newScheduleLinkField;
    private TextField newParticipantLinkField;
    private TextField newTeacherLinkField;
    private TextField newEventLinkField;
    private Button newScheduleLinkButton;
    private Button newParticipantLinkButton;
    private Button newTeacherLinkButton;
    private Button newEventLinkButton;
    private Button deleteScheduleLinkButton;
    private Button deleteParticipantLinkButton;
    private Button deleteTeacherLinkButton;
    private Button deleteEventLinkButton;
    private Upload uploadScheduleLinkButton;
    private Upload uploadParticipantLinkButton;
    private Upload uploadTeacherLinkButton;
    private Upload uploadEventLinkButton;
    private Grid<DocumentLink> scheduleLinksGrid;
    private Grid<DocumentLink> participantLinksGrid;
    private Grid<DocumentLink> teacherLinksGrid;
    private Grid<DocumentLink> eventLinksGrid;


    public DocumentLinksFormComponent(DocumentLinksService linksService, String width) {
        this.linksService = linksService;
        this.newScheduleLinkField = new TextField("Schedule sheet link");
        this.newParticipantLinkField =  new TextField("Participants sheet link");
        this.newTeacherLinkField = new TextField("Teacher sheet link");
        this.newEventLinkField = new TextField("Event sheet link");
        this.newScheduleLinkButton = createAddScheduleSheetButton();
        this.newParticipantLinkButton = createAddParticipantSheetButton();
        this.newTeacherLinkButton = createAddTeacherSheetButton();
        this.newEventLinkButton = createAddEventSheetButton();
        this.deleteScheduleLinkButton = createDeleteScheduleSheetButton();
        this.deleteParticipantLinkButton = createDeleteParticipantSheetButton();
        this.deleteTeacherLinkButton = createDeleteTeacherSheetButton();
        this.deleteEventLinkButton = createDeleteEventSheetButton();
        this.uploadScheduleLinkButton = createUploadScheduleLinkButton();
        this.uploadParticipantLinkButton = createUploadParticipantLinkButton();
        this.uploadTeacherLinkButton = createUploadTeacherLinkButton();
        this.uploadEventLinkButton = createUploadEventLinkButton();
        this.scheduleLinksGrid = createDocumentGrid(linksService.getScheduleLinksSet());
        this.participantLinksGrid = createDocumentGrid(linksService.getParticipantLinksSet());
        this.teacherLinksGrid = createDocumentGrid(linksService.getTeacherLinksSet());
        this.eventLinksGrid = createDocumentGrid(linksService.getEventLinksSet());

        init("Google docs links", width);
    }

    @Override
    protected Layout getContent() {
        VerticalLayout layout = new VerticalLayout();
        createForm(layout, newScheduleLinkField, newScheduleLinkButton, deleteScheduleLinkButton, uploadScheduleLinkButton, scheduleLinksGrid);
        createForm(layout, newParticipantLinkField, newParticipantLinkButton, deleteParticipantLinkButton, uploadParticipantLinkButton, participantLinksGrid);
        createForm(layout, newTeacherLinkField, newTeacherLinkButton, deleteTeacherLinkButton, uploadTeacherLinkButton, teacherLinksGrid);
        createForm(layout, newEventLinkField, newEventLinkButton, deleteEventLinkButton, uploadEventLinkButton, eventLinksGrid);
        return layout;
    }

    private void createForm(Layout layout, TextField textField, Button addButton, Button deleteButton, Upload upload,
                            Grid<DocumentLink> grid) {
        HorizontalLayout headerWithAddButton = new HorizontalLayout();
        headerWithAddButton.addComponent(textField);
        headerWithAddButton.setDefaultComponentAlignment(Alignment.BOTTOM_RIGHT);
        headerWithAddButton.addComponent(addButton);
        headerWithAddButton.addComponent(deleteButton);
        headerWithAddButton.addComponent(upload);
        layout.addComponent(headerWithAddButton);
        layout.addComponent(grid);
    }

    private Grid<DocumentLink> createDocumentGrid(Collection<DocumentLink> items) {
        Grid<DocumentLink> grid = new Grid<>(DocumentLink.class);
        grid.removeColumn("id");
        grid.setHeightByRows(GRID_HEIGHT);
        grid.setWidth(GRID_WIDTH);
        grid.setItems(items);
        return grid;
    }

    private Button createAddParticipantSheetButton() {
        return createAddButton(e -> {
            linksService.addParticipantLink(newParticipantLinkField.getValue());
            participantLinksGrid.setItems(linksService.getParticipantLinksSet());
        });
    }

    private Button createAddScheduleSheetButton() {
        return createAddButton(e -> {
            linksService.addScheduleLink(newScheduleLinkField.getValue());
            scheduleLinksGrid.setItems(linksService.getScheduleLinksSet());
        });
    }

    private Button createAddTeacherSheetButton() {
        return createAddButton(e -> {
            linksService.addTeacherLink(newTeacherLinkField.getValue());
            scheduleLinksGrid.setItems(linksService.getScheduleLinksSet());
        });
    }

    private Button createAddEventSheetButton() {
        return createAddButton(e -> {
            linksService.addEventLink(newEventLinkField.getValue());
            eventLinksGrid.setItems(linksService.getEventLinksSet());
        });
    }

    private Button createDeleteParticipantSheetButton() {
        return createDeleteButton(e -> {
            linksService.deleteLink(participantLinksGrid.getSelectedItems());
            participantLinksGrid.setItems(linksService.getParticipantLinksSet());
        });
    }

    private Button createDeleteScheduleSheetButton() {
        return createDeleteButton(e -> {
            linksService.deleteLink(scheduleLinksGrid.getSelectedItems());
            scheduleLinksGrid.setItems(linksService.getScheduleLinksSet());
        });
    }

    private Button createDeleteTeacherSheetButton() {
        return createDeleteButton(e -> {
            linksService.deleteLink(teacherLinksGrid.getSelectedItems());
            teacherLinksGrid.setItems(linksService.getTeacherLinksSet());
        });
    }

    private Button createDeleteEventSheetButton() {
        return createDeleteButton(e -> {
            linksService.deleteLink(eventLinksGrid.getSelectedItems());
            eventLinksGrid.setItems(linksService.getEventLinksSet());
        });
    }

    private Upload createUploadScheduleLinkButton() {
        return new Upload("", new UploadReciver() {
            private File file;
            public OutputStream receiveUpload(String filename, String MIMEType) {
                FileOutputStream fos = null;
                try {
                    file = new File("file_system/schedule/" + filename);
                    fos = new FileOutputStream(file);
                } catch (final java.io.FileNotFoundException e) {
                    new NotificationService().showInfoMessage("Could not open file");
                    return null;
                }
                linksService.addScheduleLink(file.getPath());
                scheduleLinksGrid.setItems(linksService.getScheduleLinksSet());
                return fos;
            }
        });
    }

    private Upload createUploadParticipantLinkButton() {
        return new Upload("", new UploadReciver() {
            private File file;
            public OutputStream receiveUpload(String filename, String MIMEType) {
                FileOutputStream fos = null;
                try {
                    file = new File("file_system/participant/" + filename);
                    fos = new FileOutputStream(file);
                } catch (final java.io.FileNotFoundException e) {
                    new NotificationService().showInfoMessage("Could not open file");
                    return null;
                }
                linksService.addParticipantLink(file.getPath());
                participantLinksGrid.setItems(linksService.getParticipantLinksSet());
                return fos;
            }
        });
    }

    private Upload createUploadTeacherLinkButton() {
        return new Upload("", new UploadReciver() {
            private File file;
            public OutputStream receiveUpload(String filename, String MIMEType) {
                FileOutputStream fos = null;
                try {
                    file = new File("file_system/teacher/" + filename);
                    fos = new FileOutputStream(file);
                } catch (final java.io.FileNotFoundException e) {
                    new NotificationService().showInfoMessage("Could not open file");
                    return null;
                }
                linksService.addTeacherLink(file.getPath());
                teacherLinksGrid.setItems(linksService.getTeacherLinksSet());
                return fos;
            }
        });
    }

    private Upload createUploadEventLinkButton() {
        return new Upload("", new UploadReciver() {
            private File file;
            public OutputStream receiveUpload(String filename, String MIMEType) {
                FileOutputStream fos = null;
                try {
                    file = new File("file_system/event/" + filename);
                    fos = new FileOutputStream(file);
                } catch (final java.io.FileNotFoundException e) {
                    new NotificationService().showInfoMessage("Could not open file");
                    return null;
                }
                linksService.addEventLink(file.getPath());
                eventLinksGrid.setItems(linksService.getEventLinksSet());
                return fos;
            }
        });
    }

    private Button createAddButton(Button.ClickListener listener) {
        return createButton("+", listener);
    }

    private Button createDeleteButton(Button.ClickListener listener) {
        return createButton("-", listener);
    }

    private Button createButton(String caption, Button.ClickListener listener) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button.addClickListener(listener);
        return button;
    }

}
