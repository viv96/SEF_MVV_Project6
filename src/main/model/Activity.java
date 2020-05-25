package model;

import enumerations.Status;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Activity {
    private String actID;
    private ArrayList<String> actStaff = new ArrayList<String>();
    private Status actStatus;
    private Date startDate = new Date();
    private Date endDate = new Date();
//    private int actDuration; //to be calculated in hours, progression of each activity is based on the hours worked.
//    private int actProgressDuration;

    public Activity(String actID, ArrayList<String> actStaff, Status actStatus, Date startDate, Date endDate) {
        this.actID = actID;
        this.actStaff = actStaff;
        this.actStatus = actStatus;
        this.startDate = startDate;
        this.endDate = endDate;
//        this.actDuration =  (int)( (startDate.getTime() - endDate.getTime())/(1000 * 60 * 60 * 24) );
//        this.actProgressDuration = 0;
    }

//    public int getActProgressDuration() {
//        return actProgressDuration;
//    }
//
//    public void setActProgressDuration(int actProgressDuration) {
//        this.actProgressDuration = actProgressDuration;
//    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

//    public int getActDuration() {
//        return actDuration;
//    }
//
//    public void setActDuration(int actDuration) {
//        this.actDuration = actDuration;
//    }

    public String getActID() {
        return actID;
    }

    public void setActID(String actID) {
        this.actID = actID;
    }

    public ArrayList<String> getActStaff() {
        return actStaff;
    }

    public void setActStaff(ArrayList<String> actStaff) {
        this.actStaff = actStaff;
    }

    public Status getActStatus() {
        return actStatus;
    }

    public void setActStatus(Status actStatus) {
        this.actStatus = actStatus;
    }

    public Boolean assignStaff(String staffID){
        if (getActStaff().contains(staffID)){
            return false;
        }
        getActStaff().add(staffID);
        return true;
    }

    public void progressCheck(Date date) {
        if (date.compareTo(endDate)>0){
            setActStatus(Status.OVER_DUE);
        }
    }

    @Override
    public String toString() {
        return "model.Activity [id = " + this.actID + ", staff = " + this.actStaff + ", Status = " + this.actStatus.toString() + ", startDate = " + this.startDate + ", endDate = " + this.endDate;
    }
}
