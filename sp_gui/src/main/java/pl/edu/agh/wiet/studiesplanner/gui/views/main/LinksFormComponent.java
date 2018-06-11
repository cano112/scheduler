package pl.edu.agh.wiet.studiesplanner.gui.views.main;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import pl.edu.agh.wiet.studiesplanner.model.DirectoryConfig;
import pl.edu.agh.wiet.studiesplanner.gui.service.LinksFormService;
import pl.edu.agh.wiet.studiesplanner.gui.service.NotificationService;
import pl.edu.agh.wiet.studiesplanner.gui.service.UploadReciver;
import pl.edu.agh.wiet.studiesplanner.model.parser.DocumentLink;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.function.Consumer;

public class LinksFormComponent extends AppAbstractComponent {


    private static final double GRID_HEIGHT = 3; // in rows visible
    private static final String GRID_WIDTH = "100%";


    private LinksFormService linksFormService;

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


    public LinksFormComponent(LinksFormService linksFormService, String width) {
        this.linksFormService = linksFormService;
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
        this.scheduleLinksGrid = createDocumentGrid(linksFormService.getScheduleLinksSet());
        this.participantLinksGrid = createDocumentGrid(linksFormService.getParticipantLinksSet());
        this.teacherLinksGrid = createDocumentGrid(linksFormService.getTeacherLinksSet());
        this.eventLinksGrid = createDocumentGrid(linksFormService.getEventLinksSet());

        init("Sheets", width);
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
            linksFormService.addParticipantLink(newParticipantLinkField.getValue());
            participantLinksGrid.setItems(linksFormService.getParticipantLinksSet());
        });
    }

    private Button createAddScheduleSheetButton() {
        return createAddButton(e -> {
            linksFormService.addScheduleLink(newScheduleLinkField.getValue());
            scheduleLinksGrid.setItems(linksFormService.getScheduleLinksSet());
        });
    }

    private Button createAddTeacherSheetButton() {
        return createAddButton(e -> {
            linksFormService.addTeacherLink(newTeacherLinkField.getValue());
            scheduleLinksGrid.setItems(linksFormService.getScheduleLinksSet());
        });
    }

    private Button createAddEventSheetButton() {
        return createAddButton(e -> {
            linksFormService.addEventLink(newEventLinkField.getValue());
            eventLinksGrid.setItems(linksFormService.getEventLinksSet());
        });
    }

    private Button createDeleteParticipantSheetButton() {
        return createDeleteButton(e -> {
            linksFormService.deleteLinks(participantLinksGrid.getSelectedItems());
            participantLinksGrid.setItems(linksFormService.getParticipantLinksSet());
        });
    }

    private Button createDeleteScheduleSheetButton() {
        return createDeleteButton(e -> {
            linksFormService.deleteLinks(scheduleLinksGrid.getSelectedItems());
            scheduleLinksGrid.setItems(linksFormService.getScheduleLinksSet());
        });
    }

    private Button createDeleteTeacherSheetButton() {
        return createDeleteButton(e -> {
            linksFormService.deleteLinks(teacherLinksGrid.getSelectedItems());
            teacherLinksGrid.setItems(linksFormService.getTeacherLinksSet());
        });
    }

    private Button createDeleteEventSheetButton() {
        return createDeleteButton(e -> {
            linksFormService.deleteLinks(eventLinksGrid.getSelectedItems());
            eventLinksGrid.setItems(linksFormService.getEventLinksSet());
        });
    }

    private Upload createUploadScheduleLinkButton() {
        return createUploadButton(DirectoryConfig.XLS_SCHEDULE_DIR, file -> {
            linksFormService.addScheduleLink(file.getPath());
            scheduleLinksGrid.setItems(linksFormService.getScheduleLinksSet());
        });
    }

    private Upload createUploadParticipantLinkButton() {
        return createUploadButton(DirectoryConfig.XLS_PARTICIPANTS_DIR, file -> {
            linksFormService.addParticipantLink(file.getPath());
            participantLinksGrid.setItems(linksFormService.getParticipantLinksSet());
        });
    }

    private Upload createUploadTeacherLinkButton() {
        return createUploadButton(DirectoryConfig.XLS_TEACHERS_DIR, file -> {
            linksFormService.addTeacherLink(file.getPath());
            teacherLinksGrid.setItems(linksFormService.getTeacherLinksSet());
        });
    }

    private Upload createUploadEventLinkButton() {
        return createUploadButton(DirectoryConfig.XLS_EVENTS_DIR, file -> {
            linksFormService.addEventLink(file.getPath());
            eventLinksGrid.setItems(linksFormService.getEventLinksSet());
        });
    }

    private Upload createUploadButton(String dir, Consumer<File> afterUploadListener) {
        return new Upload("", new UploadReciver() {
            private File file;
            public OutputStream receiveUpload(String filename, String MIMEType) {
                FileOutputStream fos;
                try {
                    createDirIfNotExists(dir);
                    file = new File(dir + filename);
                    if(file.exists()) {
                        throw new FileAlreadyExistsException("File exists: " + file.getName());
                    }
                    fos = new FileOutputStream(file);

                } catch (FileAlreadyExistsException e) {
                    new NotificationService().showErrorMessage("File already exists!");
                    return null;
                } catch (final IOException e) {
                    new NotificationService().showErrorMessage("Could not open file!");
                    return null;
                }

                afterUploadListener.accept(file);
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

    private void createDirIfNotExists(String dir) throws IOException {
        Path path = Paths.get(dir);
        if(Files.notExists(path)) {
            Files.createFile(Files.createDirectories(path)).toFile();
        }
    }

}
