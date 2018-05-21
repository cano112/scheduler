package pl.edu.agh.wiet.studiesplanner.gui.views.main;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import pl.edu.agh.wiet.studiesplanner.gui.service.ConflictSolverService;
import pl.edu.agh.wiet.studiesplanner.gui.service.NotificationService;

public class ConflictSolverComponent extends AppAbstractComponent {

    private ConflictSolverService conflictSolverService;
    private NotificationService notificationService;

    private Button checkConflictsButton;
    private TextArea conflicts;
    private CheckBox notificationCheckBox;

    public ConflictSolverComponent(ConflictSolverService conflictSolverService, NotificationService notificationSenderService, String width) {
        this.conflictSolverService = conflictSolverService;
        this.notificationService = notificationSenderService;
        this.checkConflictsButton = createCheckConflictsButton();
        this.conflicts = createConflictsTextArea();
        this.notificationCheckBox = createNotificationCheckBox();
        init("Check conflicts", width);
    }

    @Override
    protected Layout getContent() {
        VerticalLayout layout = new VerticalLayout();
        createForm(layout, checkConflictsButton, conflicts, notificationCheckBox);
        layout.setHeight("100%");
        return layout;
    }

    private void createForm(Layout layout, Button button, TextArea text, CheckBox checkBox) {
        VerticalLayout layoutWithCheckButton = new VerticalLayout();
        layoutWithCheckButton.setDefaultComponentAlignment(Alignment.BOTTOM_LEFT);
        layoutWithCheckButton.addComponent(button);
        layoutWithCheckButton.addComponent(text);
        layoutWithCheckButton.addComponent(checkBox);
        layout.addComponent(layoutWithCheckButton);
    }

    private Button createCheckConflictsButton() {
        Button button = new Button("Check");
        button.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button.addClickListener(e -> {
            String description = conflictSolverService.solveConflicts();
            if (description.isEmpty()) {
                notificationCheckBox.setEnabled(true);
                conflicts.setValue("No conflicts");
            }
            else {
                conflicts.setValue(description);
            }
        });
        return button;
    }

    private TextArea createConflictsTextArea() {
        TextArea textArea = new TextArea();
        textArea.setReadOnly(true);
        textArea.setWidth("100%");
        textArea.setHeight("400px");
        return textArea;
    }

    private CheckBox createNotificationCheckBox() {
        CheckBox checkBox = new CheckBox("notification");
        checkBox.setEnabled(false);
        checkBox.addStyleName(ValoTheme.BUTTON_PRIMARY);
        checkBox.addValueChangeListener(e -> {
            if (!checkBox.isEmpty()) {
                notificationService.showInfoMessage("Sending");
            }
        });
        return checkBox;
    }
}
