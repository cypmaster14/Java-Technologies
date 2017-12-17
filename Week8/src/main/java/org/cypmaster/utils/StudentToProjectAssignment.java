package org.cypmaster.utils;

import java.util.Objects;

/**
 * Created by Ciprian at 12/16/2017
 */
public class StudentToProjectAssignment {

    private long studentId;
    private int projectId;

    public StudentToProjectAssignment(long studentId, int projectId) {
        this.studentId = studentId;
        this.projectId = projectId;
    }

    public StudentToProjectAssignment() {
        this(-1, -1);
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentToProjectAssignment)) return false;
        StudentToProjectAssignment that = (StudentToProjectAssignment) o;
        return studentId == that.studentId &&
                projectId == that.projectId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, projectId);
    }

    @Override
    public String toString() {
        return "StudentToProjectAssignment{" +
                "studentId=" + studentId +
                ", projectId=" + projectId +
                '}';
    }
}
