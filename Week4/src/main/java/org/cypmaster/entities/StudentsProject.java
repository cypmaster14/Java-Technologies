package org.cypmaster.entities;

import java.io.Serializable;

public class StudentsProject implements Serializable {

    private final static Long serialVersionUID = 1L;

    private int studentId;
    private int projectId;
    private int levelOfPreferences;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getLevelOfPreferences() {
        return levelOfPreferences;
    }

    public void setLevelOfPreferences(int levelOfPreferences) {
        this.levelOfPreferences = levelOfPreferences;
    }

    @Override
    public String toString() {
        return "StudentsSkillDAO{" +
                "studentId=" + studentId +
                ", projectId=" + projectId +
                ", levelOfPreferences=" + levelOfPreferences +
                '}';
    }
}
