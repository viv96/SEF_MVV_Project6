package model;

import enumerations.Competency;
import enumerations.availability;

import java.time.LocalDate;
import java.util.*;

public class Employee extends User {
    private ArrayList<Skill> skills = new ArrayList<Skill>();
    private final ArrayList<EmployeeCalendar> employeeCalendar = new ArrayList<EmployeeCalendar>();
    private enumerations.availability weekAvailability;
    //Scanner scan = new Scanner(System.in);

    public Employee(String id, String name, String password, ArrayList<Skill> skills, String projectID, enumerations.availability weekAvailability) {
        super(id, name, password, projectID);
        this.skills = skills;
        this.weekAvailability = weekAvailability;
    }

    public Employee(String id, String name, String password) {
        super(id, name, password, null);
        this.weekAvailability = null;
    }

    public ArrayList<EmployeeCalendar> getEmployeeCalendar() {
        return employeeCalendar;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public availability getWeekAvailability() {
        return weekAvailability;
    }

    public void setWeekAvailability(availability weekAvailability) {
        this.weekAvailability = weekAvailability;
    }

    public Boolean spotter(ArrayList<String> list, String element){
        for (String s : list) {
            if (s.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public void setCalendar() {
        ArrayList<Project> projects = new ArrayList<Project>();

        projects = DataManager.getInstance().getProjects();
        for (Project project : projects) {
            for (String projectID : this.getProjectsID()) {
            // Verify he is assigned to this project
            if (project.getProjectID().equals(projectID)) {
                // Get all activity associated to this project
                for (Activity activity : project.getListOfActivities()) {
                    // Verify he is assigned to that activity
                    for (String staffId : activity.getStaffs_id()) {
                        if (staffId.equals(this.getId())) {
                            // Verify if he as atleast one skill and his competency is equal or higher
                            for (Skill activitySkill : activity.getSkillRequired()) {
                                for (Skill userSkill : this.getSkills()) {
                                    if (activitySkill.getSkillName().equals(userSkill.getSkillName()) && activitySkill.getSkillLevel().ordinal() <= userSkill.getSkillLevel().ordinal()) {
                                        this.employeeCalendar.add(new EmployeeCalendar(activity.getId(), activity.getName(), activity.getStartDate(), activity.getEndDate(), this.weekAvailability));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            }
        }
        DataManager.getInstance().addUsersToDB(this);
    }

    public ArrayList<EmployeeCalendar> getCalendar() {
        return this.employeeCalendar;
    }

    /*public Status updateActivity(Activity act) {
        if (spotter(act.getActStaff(),getId())) {
            int chooseStatus;
            scan = new Scanner(System.in);
            System.out.print("Choose a enumerations.Status for the activity:\n1. To Do\n2. Pending\n3. In Progress\n4. Completed\nyour choice: "); // this code block is to be replaced with JavaFX front end.
            chooseStatus = scan.nextInt();
            switch (chooseStatus) {
                case 1: {
                    return Status.TO_DO;
                }
                case 2: {
                    return Status.PENDING;
                }
                case 3: {
                    return Status.IN_PROGRESS;
                }
                case 4: {
                    return Status.COMPLETED;
                }
                default:{
                    System.out.print("Choose a valid enumerations.Status");
                    updateActivity(act);
                    break;
                }
            }
        }
        return Status.NO_ACCESS;
    }*/

    public Boolean updateSkill(String skillName, Competency level){
        Skill newSkill = new Skill(skillName,level);

        if (getSkills().contains(newSkill)){
            for (Skill skill : skills) {
                if (skill.getSkillName().equals(skillName)) {
                    skill.setSkillLevel(level);
                    return true;
                }
            }
            return false;
        }
        skills.add(new Skill(skillName, level));

        return true;
    }

    @Override
    public String toString() {
        return "model.employee [id = " + this.getId() + ", skill = " + this.skills + ", calendar = " + this.employeeCalendar + "]";
    }
}
