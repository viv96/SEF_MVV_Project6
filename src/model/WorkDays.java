package model;

import enumerations.Days;
import java.util.ArrayList;

public class WorkDays {
    private Days day;
    private Time loginTime;
    private Time logoutTime;
    private Time availableHours;
    private Time sTime;
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public WorkDays(Days day, Time loginTime, Time logoutTime) {
        this.day = day;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.sTime = this.loginTime;
        this.tasks.add(null);
        this.availableHours = this.availableHours.timeCalculator(loginTime,logoutTime);
    }

    public Days getDay() {
        return day;
    }

    public void setDay(Days day) {
        this.day = day;
    }

    public Time getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Time loginTime) {
        this.loginTime = loginTime;
    }

    public Time getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Time logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Time getsTime() {
        return sTime;
    }

    public void setsTime(Time sTime) {
        this.sTime = sTime;
    }

    public Time getAvailableHours() {
        return availableHours;
    }

    public void setAvailableHours(Time availableHours) {
        this.availableHours = availableHours;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Boolean addTask(Task task){
        if (availableHours.getHour()<1){
            return false;
        }
        setsTime(sTime.timeCalculator(task.getDuration(),sTime));
        tasks.add(task);
        return true;
    }
}
