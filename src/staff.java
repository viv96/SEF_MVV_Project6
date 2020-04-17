import java.util.ArrayList;

public class staff extends user{
    private String role;
    private ArrayList<skill> skills = new ArrayList<skill>();

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<skill> skills) {
        this.skills = skills;
    }

    public staff(String id, String name, String password, String role) {
        super(id, name, password);
        this.role = role;
    }

    public Boolean specifyAvailability(){
        return true;
    }

    public void updateActivity(){

    }

    public Boolean updateSkill(String skillname, competency level){
        skill newSkill = new skill(skillname,level);
        if (getSkills().contains(newSkill)){
            return false;
        }
        skills.add(new skill(skillname, level));
        return true;
    }
}
