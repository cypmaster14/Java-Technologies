package org.cypmaster.entities;

import java.io.Serializable;
import java.util.List;

public class Assignment implements Serializable {

    private final static Long serialVersionUID = 1L;

    private int projectId;
    private List<Student> assignedStudents;

    public Assignment(int projectId, List<Student> assignedStudents) {
        this.projectId = projectId;
        this.assignedStudents = assignedStudents;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public List<Student> getAssignedStudents() {
        return assignedStudents;
    }

    public void setAssignedStudents(List<Student> assignedStudents) {
        this.assignedStudents = assignedStudents;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "projectId=" + projectId +
                ", assignedStudents=" + assignedStudents +
                '}';
    }
}
