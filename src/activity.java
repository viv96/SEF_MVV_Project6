import java.util.ArrayList;

public class Activity {
    private String actID;
    private ArrayList<String> actStaff = new ArrayList<String>();
    private Status actStatus;
    private int actDuration; //to be calculated in weeks, might change the data type later.

    public Activity(String actID, Status actStatus, int actDuration) {
        this.actID = actID;
        this.actStatus = actStatus;
        this.actDuration = actDuration;
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
}
