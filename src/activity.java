import enumerations.status;

import java.util.ArrayList;

public class activity {
    private String actID;
    private ArrayList<String> actStaff = new ArrayList<String>();
    private status actStatus;
    private String startDate;
    private String endDate;
    private int actDuration; //to be calculated in weeks, might change the data type later.

    public activity(String actID, ArrayList<String> actStaff, status actStatus, String startDate, String endDate, int actDuration) {
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

    public status getActStatus() {
        return actStatus;
    }

    public void setActStatus(status actStatus) {
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
}
