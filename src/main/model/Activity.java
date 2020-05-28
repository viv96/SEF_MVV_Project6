package model;

import enumerations.Status;
import enumerations.availability;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Activity implements Serializable {
    private static int activityNumID = 0;
    private String activityID;
    private String activityName;
    private String activityDescription;
    private ArrayList<String> actStaff = new ArrayList<String>();
    private ArrayList<Activity> dependencies;
    private double estimatedTimeInWeek;
    private LocalDate startDate;
    private LocalDate endDate;
    private double earlyStart = -1;
    private double earlyFinish = -1;
    private double lateStart = -1;
    private double lateFinish = -1;
    private double totalSlack = -1;
    private Status activityStatus;
    private availability dayPerWeek;
    private ArrayList<Skill> listOfSkillsNeeded;

    public Activity(String id, String name, String description, double duration, ArrayList<String> staffs_id, LocalDate startDate, LocalDate endDate, availability dayPerWeek, ArrayList<Skill> skillRequired) {
        this.actStaff = staffs_id;
        this.activityStatus = Status.OPEN;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dayPerWeek = dayPerWeek;
        this.listOfSkillsNeeded = skillRequired;
        this.activityName = name;
        this.activityDescription = description;
        this.estimatedTimeInWeek = duration;
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
    
    public String getActivityName(){
        return this.activityName;
    }

    public ArrayList<Activity> getDependencies(){
        return this.dependencies;
    }

    public double getLateStart() {
        return lateStart;
    }

    public double getEarlyStart() {
        return earlyStart;
    }

    public double getEarlyFinish() {
        return earlyFinish;
    }

    public String getName() {
        return this.activityName;
    }

    public void setName(String name) {
        this.activityName = name;
    }

    public ArrayList<Skill> getSkillRequired() {
        return this.listOfSkillsNeeded;
    }

    public void setSkillRequired(ArrayList<Skill> skillRequired) {
        this.listOfSkillsNeeded = skillRequired;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

    public ArrayList<String> getActStaff() {
        return this.actStaff;
    }

    public void setActStatus(Status activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getId() {
        return this.activityID;
    }

    public void setId(String id) {
        this.activityID = id;
    }

    public ArrayList<String> getStaffs_id() {
        return this.actStaff;
    }

    public void setStaffs_id(ArrayList<String> staffs_id) {
        this.actStaff = staffs_id;
    }

    public Boolean assignStaff(String staffID){
        if (getStaffs_id().contains(staffID)) {
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

    public void progressCheck(LocalDate date) {
        if (date.compareTo(this.endDate) > 0) {
            setActStatus(Status.OVER_DUE);
        }
    }

    @Override
    public String toString() {
        return "model.Activity [id = " + this.activityID +
        ", activityName: " + this.activityName +
        ", activityDuration: " + this.estimatedTimeInWeek +
        ", earlyStart: " + this.earlyStart + 
        ", earlyFinish: " + this.earlyFinish +
        ", lateStart: " + this.lateStart + 
        ", lateFinish: " + this.lateFinish +
        ", totalSlack: " + this.totalSlack + 
        ", staff = " + this.actStaff +
        ", Status = " + this.status.toString() + 
        ", startDate = " + this.startDate + 
        ", endDate = " + this.endDate + "]";
    }
}