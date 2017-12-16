package org.cypmaster.utils;

/**
 * Created by Ciprian at 12/16/2017
 */
public class StudentToProjectAssignment {

    private int studentId;
    private int projectId;

    public StudentToProjectAssignment(int studentId, int projectId) {
        this.studentId = studentId;
        this.projectId = projectId;
    }

    public StudentToProjectAssignment() {
        this(-1, -1);
    }

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

    @Override
    public String toString() {
        return "StudentToProjectAssignment{" +
                "studentId=" + studentId +
                ", projectId=" + projectId +
                '}';
    }
}
