package controller;

import com.jfoenix.controls.*;
import enumerations.Status;
import enumerations.availability;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class ActivityController implements Initializable {
    @FXML
    private StackPane stackPane;

    @FXML
    private Pane paneEditActivity;

    @FXML
    private Pane paneTableView;

    @FXML
    private TableView<Activity> activityTV;

    @FXML
    private TableColumn<Activity, String> idTC;

    @FXML
    private TableColumn<Activity, String> nameTC;

    @FXML
    private TableColumn<Activity, String> descTC;

    @FXML
    private TableColumn<Activity, String> statusTC;

    @FXML
    private TableColumn<Activity, String> availabilityTC;

    @FXML
    private TableColumn<Activity, String> start_dateTC;

    @FXML
    private TableColumn<Activity, String> end_dateTC;

    @FXML
    private TableColumn<Activity, String> actionsTC;

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

    @FXML
    private JFXComboBox<String> employeeActivityCB;

    private Integer activityIdx;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idTC.setCellValueFactory(new PropertyValueFactory<>("activityID"));
        nameTC.setCellValueFactory(new PropertyValueFactory<>("activityName"));
        descTC.setCellValueFactory(new PropertyValueFactory<>("activityDescription"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("activityStatus"));
        availabilityTC.setCellValueFactory(new PropertyValueFactory<>("dayPerWeek"));
        start_dateTC.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        end_dateTC.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        // Set to null because we need to set a value in order to use the column
        actionsTC.setCellValueFactory(new PropertyValueFactory<>(null));

        // Create and handle Skills button
        Callback<TableColumn<Activity, String>, TableCell<Activity, String>> cellAction
                = //
                new Callback<TableColumn<Activity, String>, TableCell<Activity, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Activity, String> param) {
                        final TableCell<Activity, String> cell = new TableCell<Activity, String>() {

                            final JFXButton btnEditActivity = new JFXButton("Edit");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btnEditActivity.setStyle("-fx-background-color:  #4059a9; -fx-border-color: white; -fx-text-fill: white");
                                    btnEditActivity.setOnAction(event -> {
                                        paneEditActivity.setVisible(true);
                                        paneTableView.setVisible(false);
                                        activityIdx = getIndex();
                                        // Show only status for an Employee, othewise show everything for a Manager
                                        if (!UserSession.getInstance().isManager()) {
                                            nameActivityTF.setVisible(false);
                                            descActivityTA.setVisible(false);
                                            statusActivityCB.setVisible(true);
                                            availabilityActivityCB.setVisible(false);
                                            start_dateActivityDP.setVisible(false);
                                            end_dateActivityDP.setVisible(false);
                                        } else {
                                            nameActivityTF.setVisible(true);
                                            descActivityTA.setVisible(true);
                                            statusActivityCB.setVisible(true);
                                            availabilityActivityCB.setVisible(true);
                                            start_dateActivityDP.setVisible(true);
                                            end_dateActivityDP.setVisible(true);
                                        }
                                    });

                                    setGraphic(btnEditActivity);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        actionsTC.setCellFactory(cellAction);

        statusActivityCB.getItems().addAll(
                Status.OPEN,
                Status.IN_PROGRESS,
                Status.CLOSED
        );

        availabilityActivityCB.getItems().addAll(
                availability.twenty,
                availability.forty,
                availability.sixty,
                availability.eighty,
                availability.hundred
        );

        // Populate ComboBox with Employee ID
        for (User user : DataManager.getInstance().getUsers()) {
            if (user instanceof Employee) {
                employeeActivityCB.getItems().add(user.getId());
            }
        }

        activityTV.setItems(getActivities());
    }

    public ObservableList<Activity> getActivities() {
        ObservableList<Activity> activities = FXCollections.observableArrayList();

        for (Project project : DataManager.getInstance().getProjects()) {
            for (Activity activity : project.getListOfActivities()) {
                activities.add(activity);
            }
        }

        return activities;
    }

    @FXML
    private void handleSubmitEditActivity(ActionEvent event) throws IOException {
        Activity activityToEdit = activityTV.getItems().get(activityIdx);
        Integer projectIdx = 0;

        String name = nameActivityTF.getText();
        String desc = descActivityTA.getText();
        Status status = statusActivityCB.getSelectionModel().getSelectedItem();
        availability timePerWeek = availabilityActivityCB.getSelectionModel().getSelectedItem();
        String employeeId = employeeActivityCB.getSelectionModel().getSelectedItem();
        LocalDate start_date = start_dateActivityDP.getValue();
        LocalDate end_date = end_dateActivityDP.getValue();

        // Set Project
        ArrayList<Project> projects = DataManager.getInstance().getProjects();

        Integer i = 0;

        for (Project project : projects) {
            for (Activity activity : project.getListOfActivities()) {
                if (activity.getActivityID().equals(activityToEdit.getActivityID())) {
                    projectIdx = i;
                }
            }
            i++;
        }

        if (!name.isEmpty()) {
            activityToEdit.setActivityName(name);
        }
        if (!desc.isEmpty()) {
            activityToEdit.setActivityDescription(desc);
        }
        if (!employeeId.isEmpty()) {
            activityToEdit.setActStaff(employeeId);
        }
        if (status != null) {
            activityToEdit.setActStatus(status);
        }
        if (timePerWeek != null) {
            activityToEdit.setDayPerWeek(timePerWeek);
        }
        if (start_date != null) {
            activityToEdit.setStartDate(start_date);
        }
        if (end_date != null) {
            activityToEdit.setEndDate(end_date);
        }

        // Get project associated to activity
        Project project = projects.get(projectIdx);

        project.setListOfActivities(activityToEdit);

        //Update that project in our Database
        DataManager.getInstance().addProjectsToDB(project);

        //Refresh TableView
        activityTV.refresh();

        // Switch Pane
        paneEditActivity.setVisible(false);
        paneTableView.setVisible(true);
    }
}
