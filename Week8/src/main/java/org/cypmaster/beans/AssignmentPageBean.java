package org.cypmaster.beans;

import org.cypmaster.entities.Project;
import org.cypmaster.entities.Student;
import org.cypmaster.services.ProjectService;
import org.cypmaster.services.StudentService;
import org.cypmaster.utils.StudentToProjectAssignment;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ciprian at 12/16/2017
 */
@ManagedBean(name = "assignmentPageBean")
@ViewScoped
public class AssignmentPageBean {

    @EJB
    private ProjectService projectService;
    @EJB
    private StudentService studentService;

    private List<Student> students;
    private List<Project> projects;
    private List<StudentToProjectAssignment> assignments;

    @PostConstruct
    public void init() {
        this.students = studentService.findAll();
        this.projects = projectService.findAll();
        this.assignments = new ArrayList<>();
        this.assignments.add(new StudentToProjectAssignment());
    }

    public void addStudentAssignmentOption() {
        assignments.add(new StudentToProjectAssignment());
    }

    public void saveAssignments() {
        System.out.println(assignments);
    }


    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<StudentToProjectAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<StudentToProjectAssignment> assignments) {
        this.assignments = assignments;
    }


}
