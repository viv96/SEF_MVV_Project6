package controller;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.DataManager;
import model.User;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {
    @FXML
    private Label usernameLabel;

    @FXML
    private CalendarView calendarView;

    private String username;

    private ArrayList<User> users;
    private ArrayList<Calendar> calendars = new ArrayList<Calendar>();

    public void setUsername(String username) {
        usernameLabel.setText("Hi " + username);
        this.username = username;
    }

    public CalendarController() {}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.users = DataManager.getInstance().getUsers();
        this.addCalendars();
        this.setCalendarsUpdateTime();
    }

    public void addCalendars() {
        System.out.print(users);
        for (User user :  users) {
            calendars.add(new Calendar(user.getName()));

        }
        CalendarSource calendarSource = new CalendarSource("Employee");

        for (Calendar calendar : calendars) {
            calendar.setStyle(Style.STYLE1);
            calendarSource.getCalendars().add(calendar);
        }

        //Change Default to Employee
        this.calendarView.getCalendarSources().set(0, calendarSource);
    }

    //Update Calendar Time every 10 secondes
    public void setCalendarsUpdateTime() {
        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendarView.setToday(LocalDate.now());
                        calendarView.setTime(LocalTime.now());
                    });

                    try {
                        // update every 10 seconds
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
        };

        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();
    }

}
