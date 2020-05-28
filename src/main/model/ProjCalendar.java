package model;

import enumerations.availability;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class ProjCalendar implements Serializable {
    private String projectID;
    private LocalDate availStartDate;
    private LocalDate availEndDate;
    private enumerations.availability availability;

    public ProjCalendar(String projectID, LocalDate availStartDate, LocalDate availEndDate, enumerations.availability availability) {
        this.projectID = projectID;
        this.availStartDate = availStartDate;
        this.availEndDate = availEndDate;
        this.availability = availability;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public LocalDate getAvailStartDate() {
        return availStartDate;
    }

    public void setAvailStartDate(LocalDate availStartDate) {
        this.availStartDate = availStartDate;
    }

    public LocalDate getAvailEndDate() {
        return availEndDate;
    }

    public void setAvailEndDate(LocalDate availEndDate) {
        this.availEndDate = availEndDate;
    }

    public enumerations.availability getAvailability() {
        return availability;
    }

    public void setAvailability(enumerations.availability availability) {
        this.availability = availability;
    }

    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        String avail;
        if (getAvailability().equals(enumerations.availability.eighty)){
            avail = "80%";
        } else if (getAvailability().equals(enumerations.availability.sixty)){
            avail = "60";
        } else if (getAvailability().equals(enumerations.availability.forty)){
            avail = "40%";
        } else if (getAvailability().equals(enumerations.availability.twenty)){
           avail = "20%";
        } else {
            avail = "0%";
        }
        return "Project ID: "+projectID+"Start Date: "+getAvailEndDate().format(formatter)+"End Date: "+getAvailEndDate().format(formatter)+"Availability: "+avail ;
    }
//    public void getDay(String year, String month, String day){
//
////        LocalDate localDate = LocalDate.of(year, month, day);
////        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
////        System.out.println(dayOfWeek);
////
////        Calendar calendar = new GregorianCalendar();
////        calendar.set(year, month, day); // 1 = Feb  months are zero based remember
////        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
////        LocalDate.now().getDayOfWeek(); //get day of week for current day
//
//        LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day)).getDayOfWeek(); //get day of week for a particular day
//    }

}
