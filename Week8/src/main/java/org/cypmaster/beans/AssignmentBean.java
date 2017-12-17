package org.cypmaster.beans;

import org.cypmaster.services.StudentService;
import org.cypmaster.utils.StudentToProjectAssignment;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.util.List;

/**
 * Created by Ciprian at 12/16/2017
 */
@Stateful
@LocalBean
public class AssignmentBean {

    @EJB
    private StudentService studentService;

    @PostConstruct
    public void init() {

    }

    public boolean allocateStudentToProject(List<StudentToProjectAssignment> assignments) {
        return studentService.allocateStudentToProject(assignments);
    }
}
