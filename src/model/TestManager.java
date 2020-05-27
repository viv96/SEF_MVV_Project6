package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestManager {
    public static void main(String[] args){
        File file1 = new File("testcases/activities1.txt");

        //Manager creates a new project
        Manager manager1 = new Manager("BHENCHOD");
        Project p1 = manager1.createProject("Project 1", "This is my first project", "26/05/2020");

        ArrayList<String> activityNames = new ArrayList<>();
        ArrayList<String> activityDesc = new ArrayList<>();
        ArrayList<String> activityTime = new ArrayList<>();
        ArrayList<String> activityDependency = new ArrayList<>();

        //Scanning the File
        try(Scanner input = new Scanner(file1)){
            while(input.hasNextLine()){
                //System.out.println(input.nextLine());
                String[] line = input.nextLine().split("; ");
                activityNames.add(line[0]);
                activityDesc.add(line[1]);
                activityTime.add(line[2]);
                activityDependency.add(line[3]);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        //Converting the activityTime to double array
        ArrayList<Double> activityDuration = new ArrayList<>();
        for(int i=0; i<activityTime.size(); i++){
            activityDuration.add(Double.parseDouble(activityTime.get(i)));
        }

        for(int j=0; j<activityDependency.size(); j++){
            if(activityDependency.get(j).equalsIgnoreCase("none")){
                Activity activityTemp1 = manager1.createActivity(p1, activityNames.get(j), activityDesc.get(j), activityDuration.get(j), null);
                manager1.addActivityToProject(p1,activityTemp1);
            }
            else {
                ArrayList<Activity> dependency = new ArrayList<>();
                String str = activityDependency.get(j);
                String[] arrStr = str.split(",");
                for(String a: arrStr){
                    for(int index=0; index<p1.getListOfActivities().size(); index++){
                        if(p1.getListOfActivities().get(index).getActivityName().equalsIgnoreCase(a)){
                            Activity dependentActivity = p1.getListOfActivities().get(index);
                            dependency.add(dependentActivity);
                            break;
                        }
                    }
                }
                Activity activityTemp2 =  manager1.createActivity(p1, activityNames.get(j), activityDesc.get(j), activityDuration.get(j),dependency);
                manager1.addActivityToProject(p1,activityTemp2);
            }
        }

        //manager1.viewAllActivitiesInProject(p1);
        System.out.println();
        System.out.println();
        manager1.calculateCriticalPath(p1);
        manager1.viewAllActivitiesInProject(p1);

        /*
        System.out.println("dependencyIndex: 2 and dependencySize: "+ +p1.getListOfActivities().get(2).getDependencies().size());
        System.out.println("dependencyIndex: 3 and dependencySize: "+ +p1.getListOfActivities().get(3).getDependencies().size());
        System.out.println("dependencyIndex: 4 and dependencySize: "+ +p1.getListOfActivities().get(4).getDependencies().size());
        System.out.println("dependencyIndex: 5 and dependencySize: "+ +p1.getListOfActivities().get(5).getDependencies().size());
        System.out.println("dependencyIndex: 6 and dependencySize: "+ +p1.getListOfActivities().get(6).getDependencies().size());
        System.out.println("dependencyIndex: 7 and dependencySize: "+ +p1.getListOfActivities().get(7).getDependencies().size());
        System.out.println("dependencyIndex: 8 and dependencySize: "+ +p1.getListOfActivities().get(8).getDependencies().size());
        */
        //System.out.println(activityNames);
        //System.out.println(activityDesc);
        //System.out.println(activityDuration);
        //System.out.println(activityDependency);

       /*
        manager1.viewAllActivitiesInProject(p1);
        System.out.print("Enter number of dependencies: ");
        int numberOfDependencies = scan.nextInt();
        scan.nextLine();

        if(numberOfDependencies == 0){
            manager1.createActivity(p1,"Build AI","This will create an AI app",5, null);
        } else {
            ArrayList<Activity> dependency = new ArrayList<>();
            for(int i=0; i<numberOfDependencies; i++){
                String addThisActivityID = scan.nextLine();
                for(int index=0; i<p1.getListOfActivities().size(); i++){
                    if(p1.getListOfActivities().get(index).getActivityID().equalsIgnoreCase(addThisActivityID)){
                        Activity dependentActivity = p1.getListOfActivities().get(index);
                        dependency.add(dependentActivity);
                        break;
                    }
                }
            }
            Activity activityTemp =  manager1.createActivity(p1,"Terminate printer","This will terminate the printers in the room",4,dependency);
            manager1.addActivityToProject(p1,activityTemp);
        }*/
    }
}