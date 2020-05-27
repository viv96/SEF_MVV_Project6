package model;

public class ContractEmployee extends employee {
    private double rate;

    public ContractEmployee(String id, String name, String password, String role, String projectID, String experienceLevel, enumerations.availability weekAvailability, double rate) {
        super(id, name, password, role, projectID, experienceLevel, weekAvailability);
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
