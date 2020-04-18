import enumerations.Competency;

public class Skill {
    private String skillName;
    private Competency skillLevel;

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Competency getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(Competency skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Skill(String skillName, Competency skillLevel) {
        this.skillName = skillName;
        this.skillLevel = skillLevel;
    }

    @Override
    public String toString() {
        return "Skill [name = " + this.skillName + ", level = " + this.skillLevel.toString() + "]";
    }
}

