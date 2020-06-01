package controller;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.*;

import java.lang.reflect.Array;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {
    @FXML
    private CalendarView calendarView;

    private ArrayList<User> users;
    private ArrayList<Calendar> calendars = new ArrayList<Calendar>();

    public CalendarController() {}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.users = DataManager.getInstance().getUsers();
        for (User user : this.users) {
            if (user instanceof Employee) {
                Employee employee = (Employee) user;
                employee.setCalendar();
            }
        }
        this.addCalendars();
        this.addEntry();
        this.setCalendarsUpdateTime();
    }

    public void addCalendars() {
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

    public void addEntry() {

        for (User user : users) {
            if (user instanceof Employee) {
                Employee employee = (Employee) user;
                //employee.setCalendar();
                for (Calendar calendar : this.calendars) {
                    // Get correct employee calendar
                    if (calendar.getName().equals(employee.getName())) {
                        for (EmployeeCalendar employeeCalendar : employee.getCalendar()) {
                            System.out.print(employeeCalendar.getActivityName());
                            Entry<String> entry = new Entry<>(employeeCalendar.getActivityName());
                            entry.changeStartDate(employeeCalendar.getAvailStartDate());
                            entry.changeEndDate(employeeCalendar.getAvailEndDate());
                            //entry.setFullDay(true);
                            calendar.addEntry(entry);
                        }
                    }
                }

            }
        }
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
