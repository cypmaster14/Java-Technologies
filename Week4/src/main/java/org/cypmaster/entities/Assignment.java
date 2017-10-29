package org.cypmaster.entities;

import java.util.List;

public class Assignment {

    private int projectId;
    private List<Student> assignedStudents;

    public Assignment(int projectId, List<Student> assignedStudents) {
        this.projectId = projectId;
        this.assignedStudents = assignedStudents;
    }
}
