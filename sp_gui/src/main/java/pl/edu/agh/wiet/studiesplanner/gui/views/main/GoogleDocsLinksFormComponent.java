package pl.edu.agh.wiet.studiesplanner.gui.views.main;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import pl.edu.agh.wiet.studiesplanner.gui.service.GoogleDocsLinksService;
import pl.edu.agh.wiet.studiesplanner.model.parser.GoogleDocsLink;

import java.util.Collection;

public class GoogleDocsLinksFormComponent extends AppAbstractComponent {


    private static final double GRID_HEIGHT = 3; // in rows visible
    private static final String GRID_WIDTH = "100%";


    private GoogleDocsLinksService linksService;

    private TextField newScheduleLinkField;
    private TextField newParticipantLinkField;
    private TextField newTeacherLinkField;
    private Button newScheduleLinkButton;
    private Button newParticipantLinkButton;
    private Button newTeacherLinkButton;
    private Button deleteScheduleLinkButton;
    private Button deleteParticipantLinkButton;
    private Button deleteTeacherLinkButton;
    private Grid<GoogleDocsLink> scheduleLinksGrid;
    private Grid<GoogleDocsLink> participantLinksGrid;
    private Grid<GoogleDocsLink> teacherLinksGrid;


    public GoogleDocsLinksFormComponent(GoogleDocsLinksService linksService, String width) {
        this.linksService = linksService;
        this.newScheduleLinkField = new TextField("Schedule sheet link");
        this.newParticipantLinkField =  new TextField("Participants sheet link");
        this.newTeacherLinkField = new TextField("Teacher sheet link");
        this.newScheduleLinkButton = createAddScheduleSheetButton();
        this.newParticipantLinkButton = createAddParticipantSheetButton();
        this.newTeacherLinkButton = createAddTeacherSheetButton();
        this.deleteScheduleLinkButton = createDeleteScheduleSheetButton();
        this.deleteParticipantLinkButton = createDeleteParticipantSheetButton();
        this.deleteTeacherLinkButton = createDeleteTeacherSheetButton();
        this.scheduleLinksGrid = createGoogleDocsGrid(linksService.getScheduleLinksSet());
        this.participantLinksGrid = createGoogleDocsGrid(linksService.getParticipantLinksSet());
        this.teacherLinksGrid = createGoogleDocsGrid(linksService.getTeacherLinksSet());

        init("Google docs links", width);
    }

    @Override
    protected Layout getContent() {
        VerticalLayout layout = new VerticalLayout();
        createForm(layout, newScheduleLinkField, newScheduleLinkButton, deleteScheduleLinkButton, scheduleLinksGrid);
        createForm(layout, newParticipantLinkField, newParticipantLinkButton, deleteParticipantLinkButton, participantLinksGrid);
        createForm(layout, newTeacherLinkField, newTeacherLinkButton, deleteTeacherLinkButton, teacherLinksGrid);
        return layout;
    }

    private void createForm(Layout layout, TextField textField, Button addButton, Button deleteButton,
                            Grid<GoogleDocsLink> grid) {
        HorizontalLayout headerWithAddButton = new HorizontalLayout();
        headerWithAddButton.addComponent(textField);
        headerWithAddButton.setDefaultComponentAlignment(Alignment.BOTTOM_RIGHT);
        headerWithAddButton.addComponent(addButton);
        headerWithAddButton.addComponent(deleteButton);
        layout.addComponent(headerWithAddButton);
        layout.addComponent(grid);
    }

    private Grid<GoogleDocsLink> createGoogleDocsGrid(Collection<GoogleDocsLink> items) {
        Grid<GoogleDocsLink> grid = new Grid<>(GoogleDocsLink.class);
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
