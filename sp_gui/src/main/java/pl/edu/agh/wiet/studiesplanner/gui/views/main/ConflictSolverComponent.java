package pl.edu.agh.wiet.studiesplanner.gui.views.main;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import pl.edu.agh.wiet.studiesplanner.gui.service.ConflictSolverService;
import pl.edu.agh.wiet.studiesplanner.gui.service.NotificationService;
import pl.edu.agh.wiet.studiesplanner.model.solver.SolverResult;
import pl.edu.agh.wiet.studiesplanner.notifications.NotificationStateHolder;

public class ConflictSolverComponent extends AppAbstractComponent {

    private final ConflictSolverService conflictSolverService;
    private final NotificationService notificationService;
    private final NotificationStateHolder notificationStateHolder;

    private Button checkConflictsButton;
    private TextArea conflicts;
    private CheckBox notificationCheckBox;

    public ConflictSolverComponent(ConflictSolverService conflictSolverService,
                                   NotificationService notificationSenderService,
                                   NotificationStateHolder notificationStateHolder,
                                   String width) {
        this.conflictSolverService = conflictSolverService;
        this.notificationService = notificationSenderService;
        this.notificationStateHolder = notificationStateHolder;
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
        HorizontalLayout buttonBar = new HorizontalLayout();
        buttonBar.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        buttonBar.addComponent(button);
        buttonBar.addComponent(checkBox);
        layoutWithCheckButton.addComponent(buttonBar);
        layoutWithCheckButton.addComponent(text);
        layout.addComponent(layoutWithCheckButton);
    }

    private Button createCheckConflictsButton() {
        Button button = new Button("Check");
        button.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button.addClickListener(e -> {
            SolverResult result = conflictSolverService.solveConflicts();
            if (result == null || result.getResultString().isEmpty()) {
                notificationCheckBox.setEnabled(true);
                conflicts.setValue("No conflicts");
            }
            else {
                conflicts.setValue(result.getResultString());
                if(result.getConflictsCount() == 0) {
                    notificationCheckBox.setEnabled(true);
                }
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
        CheckBox checkBox = new CheckBox("enable notifications");
        boolean enabled = notificationStateHolder.areNotificationsEnabled();
        checkBox.setEnabled(enabled);
        checkBox.setValue(enabled);
        checkBox.addStyleName(ValoTheme.BUTTON_PRIMARY);
        checkBox.addValueChangeListener(e -> {
            if (!checkBox.isEmpty()) {
                notificationStateHolder.setNotificationsOn();
                checkBox.setValue(true);
                notificationService.showInfoMessage("E-mail notifications enabled");
            } else {
                notificationStateHolder.setNotificationsOff();
                checkBox.setEnabled(false);
                checkBox.setValue(false);
                notificationService.showInfoMessage("E-mail notifications disabled");
            }
        });
        return checkBox;
    }
}
