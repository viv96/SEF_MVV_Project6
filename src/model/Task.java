package model;

public class Task {
    private String taskName;
    private Time duration; // needs a condition to satisfy the time format (HH:MM)
    private Time taskStartTime;
    private Time taskEndTime;

    public Task(String taskName, Time duration, Time taskStartTime, Time taskEndTime) {
        this.taskName = taskName;
        this.taskStartTime = taskStartTime;
        this.taskEndTime = taskEndTime;
        this.duration = getDuration().timeCalculator(getTaskStartTime(),getTaskEndTime());
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Time getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(Time taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public Time getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Time taskEndTime) {
        this.taskEndTime = taskEndTime;
    }
}
