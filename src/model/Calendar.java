package model;

import java.util.ArrayList;

public class Calendar {
    private ArrayList<WorkDays> week = new ArrayList<WorkDays>();

    public Calendar(ArrayList<WorkDays> week) {
        this.week = week;
    }

    public ArrayList<WorkDays> getWeek() {
        return week;
    }

    public void setWeek(ArrayList<WorkDays> week) {
        this.week = week;
    }
}
