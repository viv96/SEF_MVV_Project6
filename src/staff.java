import java.util.ArrayList;
import java.util.Scanner;

public class staff extends user{
    private String role;
    private ArrayList<skill> skills = new ArrayList<skill>();
//    private int staffSpot;
    Scanner scan = new Scanner(System.in);

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<skill> skills) {
        this.skills = skills;
    }

    public staff(String id, String name, String password, String role) {
        super(id, name, password);
        this.role = role;
    }

    public Boolean spotter(ArrayList<String> list, String element){
        for (int i=0;i<list.size();i++){
            if(list.get(i)==element){
                return true;
            }
        }
        return false;
    }

    public Boolean specifyAvailability(){
        int Y = 2016;    // year
        int startDayOfMonth = 5;
        int spaces = 0;

        // months[i] = name of month i
        String[] months = {
                "",                               // leave empty so that we start with months[1] = "January"
                "January", "February", "March",
                "April", "May", "June",
                "July", "August", "September",
                "October", "November", "December"
        };

        // days[i] = number of days in month i
        int[] days = {
                0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };

        for (int M = 1; M <= 12; M++) {

            // check for leap year
            if ((((Y % 4 == 0) && (Y % 100 != 0)) || (Y % 400 == 0)) && M == 2)
                days[M] = 29;


            // print calendar header
            // Display the month and year
            System.out.println("          " + months[M] + " " + Y);

            // Display the lines
            System.out.println("_____________________________________");
            System.out.println("   Sun  Mon Tue   Wed Thu   Fri  Sat");

            // spaces required
            spaces = (days[M - 1] + spaces) % 7;

            // print the calendar
            for (int i = 0; i < spaces; i++)
                System.out.print("     ");
            for (int i = 1; i <= days[M]; i++) {
                System.out.printf(" %3d ", i);
                if (((i + spaces) % 7 == 0) || (i == days[M])) System.out.println();
            }

            System.out.println();
        }
        return true;
    }

    public status updateActivity(activity act){
        scan = new Scanner(System.in);
        int chooseStatus;
        if (spotter(act.getActStaff(),getId())){
            System.out.print("Choose a status for the activity:\n1. To Do\n2. Pending\n3. In Progress\n4. Completed\nyour choice: ");
            chooseStatus = scan.nextInt();
            switch (chooseStatus){
                case 1: {
                    return status.TO_DO;
                }
                case 2: {
                    return status.PENDING;
                }
                case 3: {
                    return status.IN_PROGRESS;
                }
                case 4: {
                    return status.COMPLETED;
                }
                default:{
                    System.out.print("Choose a valid status");
                    updateActivity(act);
                    break;
                }
            }
        }
        return status.NO_ACCESS;
    }

    public Boolean updateSkill(String skillName, competency level){
        skill newSkill = new skill(skillName,level);
        if (getSkills().contains(newSkill)){
            return false;
        }
        skills.add(new skill(skillName, level));
        return true;
    }
}
