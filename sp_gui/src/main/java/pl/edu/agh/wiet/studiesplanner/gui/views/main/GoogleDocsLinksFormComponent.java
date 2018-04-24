package pl.edu.agh.wiet.studiesplanner.gui.views.main;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import pl.edu.agh.wiet.studiesplanner.gui.service.GoogleDocsLinksService;
import pl.edu.agh.wiet.studiesplanner.model.parser.GoogleDocsLink;

public class GoogleDocsLinksFormComponent extends AppAbstractComponent {


    private static final double GRID_HEIGHT = 3; // in rows visible
    private static final String GRID_WIDTH = "100%";


    private GoogleDocsLinksService linksService;

    private TextField newScheduleLinkField;
    private TextField newParticipantLinkField;
    private Button newScheduleLinkButton;
    private Button newParticipantLinkButton;
    private Grid<GoogleDocsLink> scheduleLinksGrid;
    private Grid<GoogleDocsLink> participantLinksGrid;


    public GoogleDocsLinksFormComponent(GoogleDocsLinksService linksService, String width) {
        this.linksService = linksService;
        this.newScheduleLinkField = new TextField("Schedule sheet link");
        this.newParticipantLinkField =  new TextField("Participants sheet link");
        this.newScheduleLinkButton = createAddScheduleSheetButton();
        this.newParticipantLinkButton = createAddParticipantSheetButton();
        this.scheduleLinksGrid = createScheduleGrid();
        this.participantLinksGrid = createParticipantGrid();

        init("Google docs links", width);
    }

    @Override
    protected Layout getContent() {
        VerticalLayout layout = new VerticalLayout();
        createForm(layout, newScheduleLinkField, newScheduleLinkButton, scheduleLinksGrid);
        createForm(layout, newParticipantLinkField, newParticipantLinkButton, participantLinksGrid);
        return layout;
    }

    private void createForm(Layout layout, TextField textField, Button button, Grid<GoogleDocsLink> grid) {
        HorizontalLayout headerWithAddButton = new HorizontalLayout();
        headerWithAddButton.addComponent(textField);
        headerWithAddButton.setDefaultComponentAlignment(Alignment.BOTTOM_RIGHT);
        headerWithAddButton.addComponent(button);
        layout.addComponent(headerWithAddButton);
        layout.addComponent(grid);
    }

    private Grid<GoogleDocsLink> createScheduleGrid() {
        Grid<GoogleDocsLink> grid = new Grid<>(GoogleDocsLink.class);
        grid.setHeightByRows(GRID_HEIGHT);
        grid.setWidth(GRID_WIDTH);
        grid.setItems(linksService.getScheduleLinksSet());
        return grid;
    }

    private Grid<GoogleDocsLink> createParticipantGrid() {
        Grid<GoogleDocsLink> grid = new Grid<>(GoogleDocsLink.class);
        grid.setHeightByRows(GRID_HEIGHT);
        grid.setWidth(GRID_WIDTH);
        grid.setItems(linksService.getParticipantLinksSet());
        return grid;
    }

    private Button createAddParticipantSheetButton() {
        Button button = new Button("+");
        button.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button.addClickListener(e -> {
            linksService.addParticipantLink(newParticipantLinkField.getValue());
            participantLinksGrid.setItems(linksService.getParticipantLinksSet());
        });
        return button;
    }

    private Button createAddScheduleSheetButton() {
        Button button = new Button("+");
        button.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button.addClickListener(e -> {
            linksService.addScheduleLink(newScheduleLinkField.getValue());
            scheduleLinksGrid.setItems(linksService.getScheduleLinksSet());
        });
        return button;
    }

}
