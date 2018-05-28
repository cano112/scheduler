package pl.edu.agh.wiet.studiesplanner.gui.views.main;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import pl.edu.agh.wiet.studiesplanner.gui.service.LinksFormService;
import pl.edu.agh.wiet.studiesplanner.model.parser.DocumentLink;

import java.util.Collection;

public class LinksFormComponent extends AppAbstractComponent {


    private static final double GRID_HEIGHT = 3; // in rows visible
    private static final String GRID_WIDTH = "100%";


    private LinksFormService linksFormService;

    private TextField newScheduleLinkField;
    private TextField newParticipantLinkField;
    private TextField newTeacherLinkField;
    private Button newScheduleLinkButton;
    private Button newParticipantLinkButton;
    private Button newTeacherLinkButton;
    private Button deleteScheduleLinkButton;
    private Button deleteParticipantLinkButton;
    private Button deleteTeacherLinkButton;
    private Grid<DocumentLink> scheduleLinksGrid;
    private Grid<DocumentLink> participantLinksGrid;
    private Grid<DocumentLink> teacherLinksGrid;


    public LinksFormComponent(LinksFormService linksFormService, String width) {
        this.linksFormService = linksFormService;
        this.newScheduleLinkField = new TextField("Schedule sheet link");
        this.newParticipantLinkField =  new TextField("Participants sheet link");
        this.newTeacherLinkField = new TextField("Teacher sheet link");
        this.newScheduleLinkButton = createAddScheduleSheetButton();
        this.newParticipantLinkButton = createAddParticipantSheetButton();
        this.newTeacherLinkButton = createAddTeacherSheetButton();
        this.deleteScheduleLinkButton = createDeleteScheduleSheetButton();
        this.deleteParticipantLinkButton = createDeleteParticipantSheetButton();
        this.deleteTeacherLinkButton = createDeleteTeacherSheetButton();
        this.scheduleLinksGrid = createGrid(linksFormService.getScheduleLinksSet());
        this.participantLinksGrid = createGrid(linksFormService.getParticipantLinksSet());
        this.teacherLinksGrid = createGrid(linksFormService.getTeacherLinksSet());

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
                            Grid<DocumentLink> grid) {
        HorizontalLayout headerWithAddButton = new HorizontalLayout();
        headerWithAddButton.addComponent(textField);
        headerWithAddButton.setDefaultComponentAlignment(Alignment.BOTTOM_RIGHT);
        headerWithAddButton.addComponent(addButton);
        headerWithAddButton.addComponent(deleteButton);
        layout.addComponent(headerWithAddButton);
        layout.addComponent(grid);
    }

    private Grid<DocumentLink> createGrid(Collection<DocumentLink> items) {
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

    private Button createDeleteParticipantSheetButton() {
        return createDeleteButton(e -> {
            linksFormService.deleteLink(participantLinksGrid.getSelectedItems());
            participantLinksGrid.setItems(linksFormService.getParticipantLinksSet());
        });
    }

    private Button createDeleteScheduleSheetButton() {
        return createDeleteButton(e -> {
            linksFormService.deleteLink(scheduleLinksGrid.getSelectedItems());
            scheduleLinksGrid.setItems(linksFormService.getScheduleLinksSet());
        });
    }

    private Button createDeleteTeacherSheetButton() {
        return createDeleteButton(e -> {
            linksFormService.deleteLink(teacherLinksGrid.getSelectedItems());
            teacherLinksGrid.setItems(linksFormService.getTeacherLinksSet());
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
