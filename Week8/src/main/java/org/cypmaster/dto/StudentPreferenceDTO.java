package org.cypmaster.dto;

import org.cypmaster.entities.Student;

import java.util.Comparator;

/**
 * Created by Ciprian at 12/5/2017
 */
public class StudentPreferenceDTO {

    private int projectId;
    private String projectName;
    private float averagePreference;

    public StudentPreferenceDTO() {
    }

    public StudentPreferenceDTO(int projectId, String projectName, float averagePreference) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.averagePreference = averagePreference;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public float getAveragePreference() {
        return averagePreference;
    }

    public void setAveragePreference(float averagePreference) {
        this.averagePreference = averagePreference;
    }

    @Override
    public String toString() {
        return "StudentPreferenceDTO{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", averagePreference=" + averagePreference +
                '}';
    }
}
