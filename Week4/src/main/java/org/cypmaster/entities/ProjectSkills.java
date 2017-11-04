package org.cypmaster.entities;

import java.io.Serializable;

public class ProjectSkills implements Serializable {

    private final static Long serialVersionUID = 1L;

    private int projectId;
    private int skillId;
    private int levelOfPreference;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getLevelOfPreference() {
        return levelOfPreference;
    }

    public void setLevelOfPreference(int levelOfPreference) {
        this.levelOfPreference = levelOfPreference;
    }

    @Override
    public String toString() {
        return "ProjectSkills{" +
                "projectId=" + projectId +
                ", skillId=" + skillId +
                ", levelOfPreference=" + levelOfPreference +
                '}';
    }
}
