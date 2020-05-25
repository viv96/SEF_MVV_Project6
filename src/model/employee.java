package model;

import enumerations.Competency;
import enumerations.Status;
import enumerations.availability;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class employee extends User {
    private String role;
    private String experienceLevel;
    private HashSet<Skill> skills = new HashSet<Skill>();
    private ArrayList<LocalDate> occupiedDates;
    private final ArrayList<ProjCalendar> employeeCalendar;
    private enumerations.availability weekAvailability;
    Scanner scan = new Scanner(System.in);
    
    public employee(String id, String name, String password, String role, Project project, String experienceLevel, enumerations.availability weekAvailability) {
        super(id, name, password, project);
        this.experienceLevel = experienceLevel;
        this.role = role;
        this.occupiedDates = null;
        this.weekAvailability = weekAvailability;
        this.employeeCalendar = null;
    }

    public ArrayList<LocalDate> getOccupiedDates() {
        return occupiedDates;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public HashSet<Skill> getSkills() {
        return skills;
    }

    public void setSkills(HashSet<Skill> skills) {
        this.skills = skills;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public void setOccupiedDates(ArrayList<LocalDate> occupiedDates) {
        this.occupiedDates = occupiedDates;
    }

    public availability getWeekAvailability() {
        return weekAvailability;
    }

    public void setWeekAvailability(availability weekAvailability) {
        this.weekAvailability = weekAvailability;
    }

    public Boolean spotter(ArrayList<String> list, String element){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == element) {
                return true;
            }
        }
        return false;
    }

    public void checkAvailability(Project project){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd"); //the date stored in the project class must be stored in yyyy-MMM-dd format.
            formatter = formatter.withLocale(Locale.ENGLISH);  // using standard GMT time.

            LocalDate startDate = LocalDate.parse(project.getProjectStartDate(), formatter);
            LocalDate endDate = LocalDate.parse(project.getProjectEndDate(), formatter);

//            int dayDiff1 = Math.abs((int) ChronoUnit.DAYS.between(startDate, endDate));
//
//            LocalDate firstSunday = startDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
//            LocalDate lastSunday = endDate.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
//            long totalSunday = ChronoUnit.WEEKS.between(firstSunday, lastSunday) + 1;
//
//            LocalDate firstSaturday = startDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
//            LocalDate lastSaturday = endDate.with(TemporalAdjusters.previous(DayOfWeek.SATURDAY));
//            long totalSaturday = ChronoUnit.WEEKS.between(firstSaturday, lastSaturday) + 1;
//
//            int totalHolidays = (int) (totalSaturday + totalSunday);int totalWorkDays;
//
//            if (occupiedDates.isEmpty()) {
//                totalWorkDays = dayDiff1 - totalHolidays;
//            } else {
//                totalWorkDays = dayDiff1 - totalHolidays - occupiedDates.size();
//            }

            availability empAvailability;
            if (project.getProjectDaysPerWeek() == 1){
                empAvailability = availability.eighty;
            } else if (project.getProjectDaysPerWeek() == 2){
                empAvailability = availability.sixty;
            } else if (project.getProjectDaysPerWeek() == 3){
                empAvailability = availability.forty;
            } else if (project.getProjectDaysPerWeek() == 4){
                empAvailability = availability.twenty;
            } else {
                empAvailability = availability.zero;
            }

            employeeCalendar.add(new ProjCalendar(project.getProjectID(),startDate,endDate,empAvailability));

            for (ProjCalendar projCalendar : employeeCalendar) {
                System.out.print(projCalendar.toString());
            }

            System.out.print("Not Available in these dates: ");
            for (LocalDate localDate: occupiedDates){
                System.out.println(localDate.format(formatter));
            }
            
        } catch (Exception e){
            System.out.print("exception have been thrown.");
        }
    }

    public Status updateActivity(Activity act) {
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
    }

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
        return "model.employee [role = " + this.role + ", skill = " + this.skills + "]";
    }
}
