package controller;

        import com.jfoenix.controls.*;
        import enumerations.Status;
        import enumerations.availability;
        import javafx.beans.property.*;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.input.*;
        import javafx.scene.layout.Pane;
        import javafx.scene.layout.StackPane;
        import javafx.util.Callback;
        import model.*;

        import java.io.IOException;
        import java.net.URL;
        import java.time.LocalDate;
        import java.util.*;

public class ProjectController implements Initializable {
    @FXML
    private StackPane stackPane;

    @FXML
    private Pane paneAddProject;

    @FXML
    private Pane paneAddActivity;

    @FXML
    private Pane paneTableView;

    @FXML
    private TableView<Project> projectTV;

    @FXML
    private TableColumn<Project, String> idTC;

    @FXML
    private TableColumn<Project, String> nameTC;

    @FXML
    private TableColumn<Project, String> descTC;

    @FXML
    private TableColumn<Project, String> statusTC;

    @FXML
    private TableColumn<Project, String> start_dateTC;

    @FXML
    private TableColumn<Project, String> end_dateTC;

    @FXML
    private TableColumn<Project, String> activitiesTC;

    @FXML
    private TableColumn<Project, String> actionsTC;

    @FXML
    private JFXButton submitAddProject;

    @FXML
    private JFXTextField nameProjectTF;

    @FXML
    private JFXTextArea descProjectTA;

    @FXML
    private DatePicker start_dateProjectDP;

    @FXML
    private DatePicker end_dateProjectDP;

    @FXML
    private JFXComboBox<Status> statusProjectCB;

    @FXML
    private JFXTextField nameActivityTF;

    @FXML
    private JFXTextArea descActivityTA;

    @FXML
    private DatePicker start_dateActivityDP;

    @FXML
    private DatePicker end_dateActivityDP;

    @FXML
    private JFXComboBox<Status> statusActivityCB;

    @FXML
    private JFXComboBox<availability> availabilityActivityCB;

    private Integer projectIdx;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idTC.setCellValueFactory(new PropertyValueFactory<>("projectID"));
        nameTC.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        descTC.setCellValueFactory(new PropertyValueFactory<>("projectDescription"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("projectStatus"));
        start_dateTC.setCellValueFactory(new PropertyValueFactory<>("projectStartDate"));
        end_dateTC.setCellValueFactory(new PropertyValueFactory<>("projectEndDate"));
        activitiesTC.setCellValueFactory(data -> new SimpleStringProperty(Arrays.toString(data.getValue().getListOfActivities().toArray())));
        // Set to null because we need to set a value in order to use the column
        actionsTC.setCellValueFactory(new PropertyValueFactory<>(null));

        // Create and handle Skills button
        Callback<TableColumn<Project, String>, TableCell<Project, String>> cellAction
                = //
                new Callback<TableColumn<Project, String>, TableCell<Project, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Project, String> param) {
                        final TableCell<Project, String> cell = new TableCell<Project, String>() {

                            final JFXButton btnActivity = new JFXButton("Add activity");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btnActivity.setStyle("-fx-background-color:  #4059a9; -fx-border-color: white; -fx-text-fill: white");
                                    btnActivity.setOnAction(event -> {
                                        if (UserSession.getInstance().isManager()) {
                                            paneAddProject.setVisible(false);
                                            paneAddActivity.setVisible(true);
                                            paneTableView.setVisible(false);
                                            projectIdx = getIndex();
                                        } else {
                                            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                                            errorAlert.setTitle("Project");
                                            errorAlert.setHeaderText(null);
                                            errorAlert.setContentText("You're not a Manager, access refused!");
                                            errorAlert.showAndWait();
                                        }
                                    });

                                    setGraphic(btnActivity);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        actionsTC.setCellFactory(cellAction);

        statusProjectCB.getItems().addAll(
                Status.OPEN
        );

        statusActivityCB.getItems().addAll(
                Status.OPEN
        );

        availabilityActivityCB.getItems().addAll(
                availability.twenty,
                availability.forty,
                availability.sixty,
                availability.eighty,
                availability.hundred
        );

        projectTV.setItems(getProjects());
        projectTV.getSelectionModel().setCellSelectionEnabled(true);
        projectTV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        copySelectionToClipboard(projectTV);
    }

    @SuppressWarnings("rawtypes")
    public void copySelectionToClipboard(final TableView<Project> table) {
        final Set<Integer> rows = new TreeSet<>();
        for (final TablePosition tablePosition : table.getSelectionModel().getSelectedCells()) {
            rows.add(tablePosition.getRow());
        }
        final StringBuilder strb = new StringBuilder();
        boolean firstRow = true;
        for (final Integer row : rows) {
            if (!firstRow) {
                strb.append('\n');
            }
            firstRow = false;
            boolean firstCol = true;
            for (final TableColumn<?, ?> column : table.getColumns()) {
                if (!firstCol) {
                    strb.append('\t');
                }
                firstCol = false;
                final Object cellData = column.getCellData(row);
                strb.append(cellData == null ? "" : cellData.toString());
                break;
            }
        }
        final ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(strb.toString());
        Clipboard.getSystemClipboard().setContent(clipboardContent);

        final KeyCodeCombination keyCodeCopy = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_ANY);
        table.setOnKeyPressed(event -> {
            if (keyCodeCopy.match(event)) {
                copySelectionToClipboard(table);
            }
        });
    }

    public ObservableList<Project> getProjects() {
        ObservableList<Project> projects = FXCollections.observableArrayList();

        for (Project project : DataManager.getInstance().getProjects()) {
            projects.add(project);
        }

        return projects;
    }

    // This button only switch Pane to show Pane to Add a Project
    @FXML
    private void handleAddProject(ActionEvent event) throws IOException {
        if (UserSession.getInstance().isManager()) {
            // Switch Pane
            paneAddProject.setVisible(true);
            paneAddActivity.setVisible(false);
            paneTableView.setVisible(false);
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
            errorAlert.setTitle("Project");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("You're not a Manager, access refused!");
            errorAlert.showAndWait();
        }
    }

    @FXML
    private void handleSubmitAddProject(ActionEvent event) throws IOException {
        String name = nameProjectTF.getText();
        String desc = descProjectTA.getText();
        Status status = statusProjectCB.getSelectionModel().getSelectedItem();
        LocalDate start_date = start_dateProjectDP.getValue();
        LocalDate end_date = end_dateProjectDP.getValue();

        if (!name.isEmpty() && !desc.isEmpty() && status != null && start_date != null && end_date != null) {
            // Set Project
            Project project = new Project(name, desc, status, null, start_date, end_date);

            //Add that new project in our Database
            DataManager.getInstance().addProjectsToDB(project);
        }

        //Refresh TableView
        projectTV.refresh();

        // Switch Pane
        paneAddProject.setVisible(false);
        paneAddActivity.setVisible(false);
        paneTableView.setVisible(true);
    }

    @FXML
    private void handleSubmitAddActivity(ActionEvent event) throws IOException {
        Project project = projectTV.getItems().get(projectIdx);

        String name = nameActivityTF.getText();
        String desc = descActivityTA.getText();
        Status status = statusActivityCB.getSelectionModel().getSelectedItem();
        availability timePerWeek = availabilityActivityCB.getSelectionModel().getSelectedItem();
        LocalDate start_date = start_dateActivityDP.getValue();
        LocalDate end_date = end_dateActivityDP.getValue();

        if (!name.isEmpty() && !desc.isEmpty() && status != null && timePerWeek != null && start_date != null && end_date != null) {
            // Set Project
            project.setListOfActivities(new Activity(name, desc, status, timePerWeek, start_date, end_date));

            //Update that project in our Database
            DataManager.getInstance().addProjectsToDB(project);
        }

        //Refresh TableView
        projectTV.refresh();

        // Switch Pane
        paneAddProject.setVisible(false);
        paneAddActivity.setVisible(false);
        paneTableView.setVisible(true);
    }
}

