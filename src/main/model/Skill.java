package model;

import enumerations.Competency;

import java.io.Serializable;

public class Skill implements Serializable {
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
        return "\n[name: " + this.skillName + "\nlevel: " + this.skillLevel.toString() + "]\n";
    }
}

