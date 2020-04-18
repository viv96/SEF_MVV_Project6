import java.util.ArrayList;

public class project {
    private String project_name;
    private String project_status;
    private ArrayList<activity> activities = new ArrayList<activity>();

    public project(String project_name, String project_status, ArrayList<activity> activities) {
        this.project_name = project_name;
        this.project_status = project_status;
        this.activities = activities;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_status() {
        return project_status;
    }

    public void setProject_status(String project_status) {
        this.project_status = project_status;
    }
}
