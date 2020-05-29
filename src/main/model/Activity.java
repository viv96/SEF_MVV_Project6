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
    private ArrayList<Activity> dependencies;
    private double earlyStart = -1;
    private double earlyFinish = -1;
    private double lateStart = -1;
    private double lateFinish = -1;
    private double totalSlack = -1;
    private status activityStatus;
    private enum status {OPEN, IN_PROGRESS, TESTING, DONE};
    private ArrayList<Skill> listOfSkillsNeeded;

    //Constructor
    public Activity(String name, String description, double duration, ArrayList<Activity> dependencies) {
        this.activityName = name;
        this.activityDescription = description;
        this.estimatedTimeInWeek = duration;
        this.activityStatus = status.OPEN;
        this.dependencies = dependencies;
        if(this.dependencies == null){
            this.earlyStart = 0;
            this.earlyFinish = this.earlyStart+this.estimatedTimeInWeek;
        }
        generateActivityId();
        activityNumID++;
    }

    //Getter methods
    public double getEstimatedTimeInWeek() {
        return estimatedTimeInWeek;
    }

    public String getActivityID(){
        return this.activityID;
    }

    public String getActivityName(){
        return this.activityName;
    }

    public ArrayList<Activity> getDependencies(){
        return this.dependencies;
    }

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
    public double getLateStart() {
        return lateStart;
    }

    public double getEarlyStart() {
        return earlyStart;
    }

    public double getEarlyFinish() {
        return earlyFinish;
    }

    public double getLateFinish() {
        return lateFinish;
    }

    public double getTotalSlack() {
        return totalSlack;
    }

    //Setter methods
    public void setEstimatedTimeInWeek(double estimatedTimeInWeek) {
        this.estimatedTimeInWeek = estimatedTimeInWeek;
    }

    public void setEarlyStart(double value){
        this.earlyStart = value;
    }

    public void setEarlyFinish(double value){
        this.earlyFinish = value;
    }

    public void setLateStart(double value) {
        this.lateStart = value;
    }

    public void setLateFinish(double value){
        this.lateFinish = value;
    }

    public Boolean assignStaff(String staffID){
        if (getActStaff().contains(staffID)){
            return false;
        }
        getActStaff().add(staffID);
    
        return true;
    }

    public void setTotalSlack(double value){
        this.totalSlack = value;
    }

    /*****************************************************************************************
     * Method name       : generateActivityId()
     * Return type       : void
     * Creator           : Vijit Kumar (s3799493)
     * Method description: This method is called in a constructor and then generates a unique
     *                     ID for an activity object.
     *****************************************************************************************/
    public void generateActivityId() {
        String s1 = "A";
        String str = String.format("%d", activityNumID);
        String s = s1+str;
        this.activityID = s;
    }

    public void progressCheck(Date date) {
        if (date.compareTo(endDate)>0){
            setActStatus(Status.OVER_DUE);
        }
    }

    @Override
    public String toString() {
        return "activityID: " + this.activityID + ", activityName: " + this.activityName +
                ", activityDuration: " + this.estimatedTimeInWeek +
                ", earlyStart: " + this.earlyStart + ", earlyFinish: " + this.earlyFinish +
                ", lateStart: " + this.lateStart + ", lateFinish: " + this.lateFinish +
                ", totalSlack: " + this.totalSlack;
    }
}