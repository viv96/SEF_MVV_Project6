package model;

import java.util.ArrayList;

public class ContractEmployee extends employee {
    private double rate;

    public ContractEmployee(String id, String name, String password, String role, Calendar employeeCalendar, double rate) {
        super(id, name, password, role, employeeCalendar);
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
