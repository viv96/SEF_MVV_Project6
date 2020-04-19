import enumerations.Status;

import java.util.ArrayList;

public class Activity {
    private String actID;
    private ArrayList<String> actStaff = new ArrayList<String>();
    private Status actStatus;
    private String startDate;
    private String endDate;
    private int actDuration; //to be calculated in weeks, might change the data type later.

    public Activity(String actID, ArrayList<String> actStaff, Status actStatus, String startDate, String endDate, int actDuration) {
        this.actID = actID;
        this.actStaff = actStaff;
        this.actStatus = actStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.actDuration = actDuration;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getActDuration() {
        return actDuration;
    }

    public void setActDuration(int actDuration) {
        this.actDuration = actDuration;
    }

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

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Boolean assignStaff(String staffID){
        if (getActStaff().contains(staffID)){
            return false;
        }
        getActStaff().add(staffID);
        return true;
    }

    @Override
    public String toString() {
        return "Activity [id = " + this.actID + ", staff = " + this.actStaff + ", Status = " + this.actStatus.toString() + ", startDate = " + this.startDate + ", endDate = " + this.endDate +  ", duration = " + String.valueOf(this.actDuration) + "]";
    }
}
