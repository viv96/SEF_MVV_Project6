package model;

import enumerations.Competency;
import enumerations.Status;
import enumerations.availability;

import java.time.LocalDate;
import java.util.*;

public class Employee extends User {
    private ArrayList<Skill> skills = new ArrayList<Skill>();
    private ArrayList<String> projects_id = new ArrayList<String>();
    private ArrayList<EmployeeCalendar> employeeCalendars = new ArrayList<EmployeeCalendar>();
    private enumerations.availability weekAvailability;

    public Employee(String name, String password, ArrayList<Skill> skills, ArrayList<String> projects_id, enumerations.availability weekAvailability) {
        super(name, password);
        this.skills = skills;
        if (projects_id != null) {
            for (String project_id : projects_id)
                this.projects_id.add(project_id);
        }
        this.weekAvailability = weekAvailability;
    }

    public Employee(String name, String password) {
        super(name, password);
        this.weekAvailability = null;
    }

    public ArrayList<EmployeeCalendar> getEmployeeCalendar() {
        return employeeCalendars;
    }

    public void setEmployeeCalendar(ArrayList<EmployeeCalendar> newCalendar) {
        this.employeeCalendars = newCalendar;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Skill skill) {
        this.skills.add(skill);
    }

    public ArrayList<String> getProjectsID() {
        return projects_id;
    }

    public void setProjectsID(String project_id) {
        this.projects_id.add(project_id);
    }

    public availability getWeekAvailability() {
        return weekAvailability;
    }

    public void setWeekAvailability(availability weekAvailability) {
        this.weekAvailability = weekAvailability;
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
                                        this.employeeCalendars.add(new EmployeeCalendar(activity.getActivityID(), activity.getActivityName(), activity.getStartDate(), activity.getEndDate(), this.weekAvailability));
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
        return this.employeeCalendars;
    }

    public Boolean setActivityStatus(Activity activity, Status status) {
        for (EmployeeCalendar employeeCalendar : employeeCalendars) {
            if (activity.getActivityID().equals(employeeCalendar.getId())) {
                activity.setActStatus(status);
                return true;
            }
        }

        return false;
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
}
