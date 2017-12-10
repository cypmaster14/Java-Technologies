package org.cypmaster.utils;

import org.cypmaster.entities.Student;

/**
 * Created by Ciprian at 11/8/2017
 */
public class StudentAssignment {

    private Student student;
    private int projectID;


    public StudentAssignment(Student student, int projectID) {
        this.student = student;
        this.projectID = projectID;
    }

    public StudentAssignment() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    @Override
    public String toString() {
        return "StudentAssignment{" +
                "student=" + student +
                ", projectID=" + projectID +
                '}';
    }
}
