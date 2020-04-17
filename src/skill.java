public class skill {
    private String skillName;
    private competency skillLevel;

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public competency getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(competency skillLevel) {
        this.skillLevel = skillLevel;
    }

    public skill(String skillName, competency skillLevel) {
        this.skillName = skillName;
        this.skillLevel = skillLevel;
    }
}

