public class contractStaff extends staff{
    private double rate;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public contractStaff(String id, String name, String password, String role, double rate) {
        super(id, name, password, role);
        this.rate = rate;
    }

    public boolean specifyRate(double askPrice){
        if (askPrice > 0){
            setRate(askPrice);
            return true;
        }
        return false;
    }

}
