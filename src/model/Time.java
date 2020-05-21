package model;

public class Time {
    private int hour;
    private int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public Time timeCalculator(Time t1, Time t2) {  //inbuilt calculator for calculating time for tasks and activities for 'employee' class.
        Time resultTime = null;
        int extraHour = 0;
        assert false;
        resultTime.setMinute(t1.getMinute() + t2.getMinute());
        while (resultTime.getMinute() >= 60) {
            extraHour = extraHour + 1;
            resultTime.setMinute(getMinute() - 60);
        }
        resultTime.setHour(t1.getHour() + t2.getHour() + extraHour);
        return resultTime;
    }
}
