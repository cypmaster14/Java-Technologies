package org.cypmaster.beans;

import org.cypmaster.entities.Project;
import org.cypmaster.entities.Student;
import org.cypmaster.services.ProjectService;
import org.cypmaster.services.StudentService;
import org.cypmaster.utils.StudentToProjectAssignment;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @EJB
    private ProjectCheckingBean projectCheckingBean;
    @EJB
    private AssignmentBean assignmentBean;
    @EJB
    private ProjectStatusBean projectStatusBean;


    private List<Student> students;
    private List<Project> projects;
    private List<StudentToProjectAssignment> assignments;

    @PostConstruct
    public void init() {
        this.students = studentService.findAll();
        this.projects = projectService.findAll()
                .stream()
                .filter(project -> projectCheckingBean.projectIsAvailable(project.getId()))
                .collect(Collectors.toList());
        this.assignments = new ArrayList<>();
        this.assignments.add(new StudentToProjectAssignment());
    }

    public void addStudentAssignmentOption() {
        assignments.add(new StudentToProjectAssignment());
    }

    public void saveAssignments() {
        System.out.println(assignments);
        if (!userInputIsValid()) {
            System.out.println("Input is not valid");
            addMessage("Input is not valid");
            return;
        }
        boolean studentsWereAllocatedToProjects = assignmentBean.allocateStudentToProject(assignments);
        System.out.println(studentsWereAllocatedToProjects);
        if (!studentsWereAllocatedToProjects) {
            addMessage("Some error occurred during the allocation to projects");
        }
        assignments.forEach(
                assignment ->
                        projectStatusBean.assignStudentToProject(
                                getProjectById(assignment.getProjectId()),
                                getStudentById(assignment.getStudentId())
                        )
        );
        System.out.println(projectStatusBean.getProjectToAssignStudents());
    }

    public void deleteAssignment(StudentToProjectAssignment assignment) {
        System.out.println("Delete assignment");
        assignments.remove(assignment);
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

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private boolean userInputIsValid() {
        for (StudentToProjectAssignment assignment : assignments) {
            if (assignment.getProjectId() == -1 || assignment.getStudentId() == -1) {
                return false;
            }
        }
        return true;
    }

    private Project getProjectById(int projectId) {
        Optional<Project> result = projects.stream()
                .parallel()
                .filter(project1 -> project1.getId() == projectId)
                .findFirst();
        return result.get();
    }

    private Student getStudentById(long studentId) {
        Optional<Student> result = students.stream()
                .parallel()
                .filter(student -> student.getId() == studentId)
                .findFirst();
        return result.get();
    }


}
