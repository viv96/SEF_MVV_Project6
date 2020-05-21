package model;

import enumerations.Competency;
import enumerations.Status;
import model.Activity;
import model.User;
import java.util.ArrayList;
import java.util.Scanner;

public class employee extends User {
    private String role;
    private ArrayList<Skill> skills = new ArrayList<Skill>();
    private Calendar employeeCalendar;
//    private int staffSpot;
    Scanner scan = new Scanner(System.in);
    
    public employee(String id, String name, String password, String role, Calendar employeeCalendar) {
        super(id, name, password);
        this.role = role;
        this.employeeCalendar = null;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public Boolean spotter(ArrayList<String> list, String element){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == element) {
                return true;
            }
        }
        return false;
    }

    public Boolean specifyAvailability(){

        return true;

//        int Y = 2020;    // year
//        int startDayOfMonth = 5;
//        int spaces = startDayOfMonth;
//
//        // months[i] = name of month i
//        String[] months = {
//                "",                               // leave empty so that we start with months[1] = "January"
//                "January", "February", "March",
//                "April", "May", "June",
//                "July", "August", "September",
//                "October", "November", "December"
//        };
//
//        // days[i] = number of days in month i
//        int[] days = {
//                0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
//        };
//
//        for (int M = 1; M <= 12; M++) {
//
//            // check for leap year
//            if ((((Y % 4 == 0) && (Y % 100 != 0)) || (Y % 400 == 0)) && M == 2)
//                days[M] = 29;
//
//
//            // print calendar header
//            // Display the month and year
//            System.out.println("          " + months[M] + " " + Y);
//
//            // Display the lines
//            System.out.println("_____________________________________");
//            System.out.println("   Sun  Mon Tue   Wed Thu   Fri  Sat");
//
//            spaces = (days[M - 1] + spaces) % 7;
//
//            // print the calendar
//            for (int i = 0; i < spaces; i++)
//                System.out.print("     ");
//            for (int i = 1; i <= days[M]; i++) {
//                System.out.printf(" %3d ", i);
//                if (((i + spaces) % 7 == 0) || (i == days[M])) System.out.println();
//            }
//            System.out.println();
//        }
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
