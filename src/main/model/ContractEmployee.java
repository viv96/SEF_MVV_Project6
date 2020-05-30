package model;

import java.util.ArrayList;

public class ContractEmployee extends Employee {
    private double rate;

    public ContractEmployee(String id, String name, String password, ArrayList<Skill> skills, String projectID, enumerations.availability weekAvailability, double rate) {
        super(id, name, password, skills, projectID, weekAvailability);
        this.rate = rate;
    }

    public boolean specifyRate(double askPrice){
        if (askPrice > 0){
            setRate(askPrice);
            return true;
        }
        return false;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
