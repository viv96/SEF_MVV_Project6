package model;

import java.util.*;
import enumerations.Status;
import enumerations.availability;

import java.time.LocalDate;
import java.util.ArrayList;
import java.io.Serializable;

public class Project implements Serializable {
    private String projectID;
    private String projectName;
    private String projectDescription;
    private LocalDate projectStartDate;
    private LocalDate projectEndDate;
    private Status projectStatus;
    private ArrayList<Activity> listOfActivities = new ArrayList<Activity>();

    public Project(String projectName, String projectDescription, Status status, ArrayList<Activity> activities, LocalDate projectStartDate, LocalDate projectEndDate) {
        this.projectID = UUID.randomUUID().toString();
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectStatus = status;
        if (activities != null)
            this.listOfActivities = activities;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
    }

    //Getter method List of Activities
    public String getProjectID() {
        return this.projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public String getProjectDescription() {
        return this.projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public ArrayList<Activity> getListOfActivities() {
        return listOfActivities;
    }

    public void setListOfActivities(Activity activity) {
        listOfActivities.add(activity);
    }

    public LocalDate getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(LocalDate projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public LocalDate getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(LocalDate projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    /*******************************************************************************************************************
     * Method name       : UpdateListOfActivities(Activity)
     * Return type       : void
     * Creator           : Vijit Kumar (s3799493)
     * Method description: This method adds the activity to the list of activity.
     ******************************************************************************************************************/
    public void updateListOfActivities(Activity activity) {
        this.listOfActivities.add(activity);
    }

    /*******************************************************************************************************************
     * Method name       : criticalPath()
     * Return type       : void
     * Creator           : Vijit Kumar (s3799493)
     * Method description: This method calculates the critical path of the Project by doing a backward and forward pass
     *                     which will update the values of early  start, late start, early finish, and late finish of
     *                     each activity, thus calculating the critical path.
     ******************************************************************************************************************/
    public void criticalPath(){
        //STEP 1: Do the Forward pass
        for(int index1=0; index1<listOfActivities.size(); index1++){
            forwardPassInCriticalPath(listOfActivities.get(index1));
        }

        //STEP 2: Update the lateFinish and lateStart of the Last Activities with largest early finish value
        double tempLargestEarlyFinish = listOfActivities.get(0).getEarlyFinish();
        int indexOfLargestEarlyFinishActivity = 0;
        for(int index2=0; index2<listOfActivities.size(); index2++){
            if(tempLargestEarlyFinish < listOfActivities.get(index2).getEarlyFinish()){
                tempLargestEarlyFinish = listOfActivities.get(index2).getEarlyFinish();
                indexOfLargestEarlyFinishActivity = index2;
            }
        }
        //Getting the list of indexes of Last activities
        ArrayList<Integer> indexOfLastActivities = criticalPathHelper();
        for(int index3=0; index3<indexOfLastActivities.size(); index3++){
            double tempEstimatedTime = listOfActivities.get(indexOfLastActivities.get(index3)).getEstimatedTimeInWeek();
            listOfActivities.get(indexOfLastActivities.get(index3)).setLateFinish(tempLargestEarlyFinish);
            listOfActivities.get(indexOfLastActivities.get(index3)).setLateStart(tempLargestEarlyFinish-tempEstimatedTime);

            //Updating total slack value
            double tempLateFinish = listOfActivities.get(indexOfLastActivities.get(index3)).getLateFinish();
            double tempEarlyFinish = listOfActivities.get(indexOfLastActivities.get(index3)).getEarlyFinish();
            listOfActivities.get(indexOfLastActivities.get(index3)).setTotalSlack(tempLateFinish-tempEarlyFinish);
        }

        //STEP 3: Do the Backward Pass
        backwardPassInCriticalPath(indexOfLastActivities);
    }

    /*******************************************************************************************************************
     * Method name       : forwardPassInCriticalPath(Activity )
     * Return type       : void
     * Creator           : Vijit Kumar (s3799493)
     * Method description: This method is recursively called to calculate the value of early start and early finish
     ******************************************************************************************************************/
    private void forwardPassInCriticalPath(Activity activity){
        //This is a terminating condition for recursion
        if(activity.getEarlyFinish() != -1 ){
            return;
        }

        //This checks if there are multiple dependencies and then call recursively
        for(int i=0; i<activity.getDependencies().size(); i++){
            forwardPassInCriticalPath(activity.getDependencies().get(i));
        }

        //This loop checks if there are multiple dependencies then we choose the maximum value of dependency's early finish
        double tempDependencyEarlyFinish = activity.getDependencies().get(0).getEarlyFinish();
        for(int j=0; j<activity.getDependencies().size(); j++){
            if(tempDependencyEarlyFinish < activity.getDependencies().get(j).getEarlyFinish()){
                tempDependencyEarlyFinish = activity.getDependencies().get(j).getEarlyFinish();
            }
        }

        //Updating the value of earlyStart and earlyFinish
        activity.setEarlyStart(tempDependencyEarlyFinish);
        activity.setEarlyFinish(activity.getEarlyStart()+activity.getEstimatedTimeInWeek());
    }

    /*******************************************************************************************************************
     * Method name       : backwardPassInCriticalPath(Activity )
     * Return type       : void
     * Creator           : Vijit Kumar (s3799493)
     * Method description: This method uses Breadth First Search Method to do the backward pass. In this method all
     *                     the values of lateStart, lateFinish, and total slack are calculated.
     ******************************************************************************************************************/
    private void backwardPassInCriticalPath(ArrayList<Integer> indexOfActivities){
        //Create a Queue that will keep the information of indexes
        Queue<Integer> indexQueue = new LinkedList<>();

        //Updating the queue
        for(int index1=0; index1<indexOfActivities.size(); index1++){
            indexQueue.add(indexOfActivities.get(index1));
        }

        //This is where the BFS is happening aka MAGIC :)
        while(indexQueue.peek() != null){
            if(listOfActivities.get(indexQueue.peek()).getDependencies() != null) {
                int sizeOfActivityDependency = listOfActivities.get(indexQueue.peek()).getDependencies().size();

                for (int index2 = 0; index2 < sizeOfActivityDependency; index2++) {
                    double tempLateStartOfHead = listOfActivities.get(indexQueue.peek()).getLateStart();
                    String tempDependencyActivityName =
                            listOfActivities.get(indexQueue.peek())
                                    .getDependencies()
                                    .get(index2)
                                    .getActivityName();

                    //Adding the dependency index to queue
                    for (int index3 = 0; index3 < listOfActivities.size(); index3++) {
                        if (listOfActivities.get(index3).getActivityName().equalsIgnoreCase(tempDependencyActivityName)) {
                            indexQueue.add(index3);
                            break;
                        }
                    }

                    //Starting here Updating the values of Late Finish and Late Start of Dependencies
                    if (listOfActivities.get(indexQueue.peek()).getDependencies().get(index2).getLateFinish() == -1) {
                        //Updating the value of Late Finish of Dependency
                        listOfActivities.get(indexQueue.peek())
                                .getDependencies()
                                .get(index2)
                                .setLateFinish(tempLateStartOfHead);

                        //Updating the value of Late Start of Dependency
                        double tempLateFinishOfDependency =
                                listOfActivities.get(indexQueue.peek())
                                        .getDependencies()
                                        .get(index2)
                                        .getLateFinish();
                        double tempDurationOfDependency =
                                listOfActivities.get(indexQueue.peek())
                                        .getDependencies()
                                        .get(index2)
                                        .getEstimatedTimeInWeek();
                        listOfActivities.get(indexQueue.peek())
                                .getDependencies()
                                .get(index2)
                                .setLateStart(tempLateFinishOfDependency - tempDurationOfDependency);

                        //This is updating the value of total Slack of dependency
                        double tempEarlyFinishOfDependency =
                                listOfActivities.get(indexQueue.peek())
                                        .getDependencies()
                                        .get(index2)
                                        .getEarlyFinish();
                        listOfActivities.get(indexQueue.peek())
                                .getDependencies()
                                .get(index2)
                                .setTotalSlack(tempLateFinishOfDependency - tempEarlyFinishOfDependency);
                    } else if (listOfActivities.get(indexQueue.peek())
                            .getDependencies()
                            .get(index2)
                            .getLateFinish() > listOfActivities.get(indexQueue.peek()).getLateStart()) {
                        listOfActivities.get(indexQueue.peek())
                                .getDependencies()
                                .get(index2)
                                .setLateFinish(listOfActivities.get(indexQueue.peek()).getLateStart());

                        //Updating the value of Late Start of Dependency
                        double tempLateFinishOfDependency = listOfActivities.get(indexQueue.peek())
                                .getDependencies()
                                .get(index2)
                                .getLateFinish();
                        double tempDurationOfDependency =
                                listOfActivities.get(indexQueue.peek())
                                        .getDependencies()
                                        .get(index2)
                                        .getEstimatedTimeInWeek();
                        listOfActivities.get(indexQueue.peek())
                                .getDependencies()
                                .get(index2)
                                .setLateStart(tempLateFinishOfDependency - tempDurationOfDependency);

                        //Updating the value of total Slack of dependency
                        double tempEarlyFinishOfDependency = listOfActivities.get(indexQueue.peek())
                                .getDependencies()
                                .get(index2)
                                .getEarlyFinish();
                        listOfActivities.get(indexQueue.peek())
                                .getDependencies()
                                .get(index2)
                                .setTotalSlack(tempLateFinishOfDependency - tempEarlyFinishOfDependency);
                    }
                }
            }
            //Removing the index from queue after the updates are done
            indexQueue.poll();
        }
    }

    /*******************************************************************************************************************
     * Method name       : criticalPathHelper()
     * Return type       : ArrayList<Integer>
     * Creator           : Vijit Kumar (s3799493)
     * Method description: This method finds the last activities that are not part of any dependencies and return their
     *                     index position in listOfActivities.
     ******************************************************************************************************************/
    private ArrayList<Integer> criticalPathHelper(){
        //First add all the dependencies in a hash set so that multiple copies are not saved
        Set<String> dependenciesList = new HashSet<>();
        for(int index1=0; index1<listOfActivities.size(); index1++){
            if(listOfActivities.get(index1).getDependencies() != null) {
                for (int index2 = 0; index2 < listOfActivities.get(index1).getDependencies().size(); index2++) {
                    dependenciesList.add(listOfActivities.get(index1).getDependencies().get(index2).getActivityName());
                }
            }
        }

        //Now check the if Activity name exists inside the HashSet
        ArrayList<Integer> indexOfLastActivites = new ArrayList<>();
        for(int index3=0; index3<listOfActivities.size(); index3++){
            if(!(dependenciesList.contains(listOfActivities.get(index3).getActivityName()))){
                indexOfLastActivites.add(index3);
            }
        }
        return indexOfLastActivites;
    }

    public Status getProjectStatus() {
        return this.projectStatus;
    }

    public void setProjectStatus(Status projectStatus) {
        this.projectStatus = projectStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;

        Project project = (Project) o;

        return !(projectName != null ? !projectName.equals(project.projectName) : project.projectName != null);
    }
}
