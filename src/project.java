import java.util.ArrayList;
import java.io.Serializable;

public class Project implements Serializable {
    private String projectName;
    private String projectStatus;
    private ArrayList<Activity> activities = new ArrayList<Activity>();

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectStatus() {
        return this.projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Project (String projectName, String projectStatus) {
        super();
        this.projectName = projectName;
        this.projectStatus = projectName;
    }

    @Override
    public String toString() {
        return "project [name = " + this.projectName + ", status = " + this.projectStatus + "]";
    }
}
