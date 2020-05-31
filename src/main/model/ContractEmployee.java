package model;

import java.util.ArrayList;

public class ContractEmployee extends Employee {
    private double rate;

    public ContractEmployee(String name, String password, ArrayList<Skill> skills, ArrayList<String> projects_id, enumerations.availability weekAvailability, double rate) {
        super(name, password, skills, projects_id, weekAvailability);
        this.rate = rate;
    }

    public boolean specifyRate(double askPrice) {
        if (askPrice > 0) {
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
