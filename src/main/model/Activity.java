package model;

import enumerations.Status;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Activity {
    private static int activityNumID = 0;
    private String activityID;
    private String activityName;
    private String activityDescription;
    private double estimatedTimeInWeek;
    //private double earlyStart;
    //private double earlyFinish;
    //private double lateStart;
    //private double lateFinish;
    //private double totalSlack;
    private status activityStatus;
    private enum status {OPEN, IN_PROGRESS, TESTING, DONE};
    private ArrayList<Skill> listOfSkillsNeeded;

    //Constructor
    public Activity(String name, String description, double duration) {
        this.activityName = name;
        this.activityDescription = description;
        this.estimatedTimeInWeek = duration;
        this.activityStatus = status.OPEN;
        generateActivityId();
        activityNumID++;
    }

    //getter methods
    public String getActivityID(){return this.activityID;}


    /*****************************************************************************************
     * Method name       : generateActivityId()
     * Method description: This method is called in a constructor and then generates a unique
     *                     ID for an activity object.
     * Creator           : Vijit Kumar
     * Date              : 04/05/2020
     *****************************************************************************************/
    public void generateActivityId() {
        String s1 = "A";
        String str = String.format("%d", activityNumID);
        String s = s1+str;
        this.activityID = s;
    }


    /*
    public String getStartDate() {
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
     */

    public void progressCheck(Date date) {
        if (date.compareTo(endDate)>0){
            setActStatus(Status.OVER_DUE);
        }
    }

    @Override
    public String toString() {
        return "activityID: " + this.activityID + ", activityName: " + this.activityName + ", activityDescription: " + this.activityDescription + ", activityStatus: " + this.activityStatus + ", estimatedTime:  " + this.estimatedTimeInWeek + " days";
    }
}
