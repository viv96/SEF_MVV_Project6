//package model;
//
//import enumerations.Days;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Locale;
//
//public class WorkDay {
//    private LocalDate date;
//    private enumerations.Days day;
//    private Boolean workStatus;
//    private Time availableHours;
//    private Time sTime;
//
//    public WorkDay(String date, enumerations.Days day, Boolean workStatus, Time loginTime, Time logoutTime) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd"); //the date stored in the project class must be stored in yyyy-MMM-dd format.
//        formatter = formatter.withLocale(Locale.ENGLISH );  // using standard GMT time.
//        this.date = LocalDate.parse(date, formatter);
//        this.day = day;
//        this.workStatus = workStatus;
//        this.availableHours = this.availableHours.timeCalculator(loginTime,logoutTime);
//    }
//
//    public Boolean getWorkStatus() {
//        return workStatus;
//    }
//
//    public void setWorkStatus(Boolean workStatus) {
//        this.workStatus = workStatus;
//    }
//
//    public enumerations.Days getDay() {
//        return day;
//    }
//
//    public void setDay(enumerations.Days day) {
//        this.day = day;
//    }
//
//    public Time getsTime() {
//        return sTime;
//    }
//
//    public void setsTime(Time sTime) {
//        this.sTime = sTime;
//    }
//
//    public Time getAvailableHours() {
//        return availableHours;
//    }
//
//    public void setAvailableHours(Time availableHours) {
//        this.availableHours = availableHours;
//    }
//}
