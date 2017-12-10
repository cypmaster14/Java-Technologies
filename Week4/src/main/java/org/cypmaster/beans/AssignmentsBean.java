package org.cypmaster.beans;

import org.cypmaster.entities.Assignment;
import org.cypmaster.entities.Student;
import org.cypmaster.services.AssignmentService;
import org.cypmaster.utils.StudentAssignment;
import org.cypmaster.utils.UserTracking;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Ciprian at 11/8/2017
 */
@ManagedBean(name = "assignmentBean")
@SessionScoped
public class AssignmentsBean implements Serializable {

    private final static long serialVersionUID = 1L;
    private final static String CURRENT_PAGE = "assignmentPage";


    private AssignmentService assignmentService;

    private List<StudentAssignment> assignments;
    private StudentAssignment selectedAssignment;


    @PostConstruct
    public void init() {
        System.out.println("PostConstruct");
        this.assignmentService = AssignmentService.getInstance();
        Map<Student, Integer> studentToProject = new HashMap<>();
        List<Assignment> assignments = assignmentService.getAssignments();
        for (Assignment assignment : assignments) {
            assignment.getAssignedStudents().forEach(student -> studentToProject.put(student, assignment.getProjectId()));
        }
        this.assignments = new ArrayList<>();
        studentToProject.forEach((student, projectId) -> this.assignments.add(new StudentAssignment(student, projectId)));
        System.out.println(assignments);

        String sessionID = getSessionId();
        UserTracking.userEnteredPage(CURRENT_PAGE, sessionID);
    }

    @PreDestroy
    public void destroy() {
        String sessionID = getSessionId();
        UserTracking.userLeavedPage(CURRENT_PAGE, sessionID);
    }


    public void onRowEditCancel(RowEditEvent event) {
        StudentAssignment studentAssignment = (StudentAssignment) event.getObject();
        addMessage("Edit Cancelled:" + studentAssignment.getStudent().getName(), FacesMessage.SEVERITY_INFO);
    }

    public void onRowEditSave(RowEditEvent event) {
        StudentAssignment studentAssignment = (StudentAssignment) event.getObject();
        System.out.println("[Assignment Edit]" + studentAssignment.getStudent().getName());
        boolean assignmentWasUpdate = assignmentService.updateAssignment(studentAssignment);
        if (assignmentWasUpdate) {
            addMessage("Assignment was updated" + studentAssignment.getStudent().getName(), FacesMessage.SEVERITY_INFO);
        } else {
            addMessage("Some error occurred", FacesMessage.SEVERITY_ERROR);
        }
    }

    private void addMessage(String summary, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private String getSessionId() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionId(true);
    }

    public void getUsersOnPage() {
        Set<String> users = UserTracking.getNumberOfUsersOnPage(CURRENT_PAGE);
        addMessage("Users:" + users.size(), FacesMessage.SEVERITY_INFO);
    }

    public List<StudentAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<StudentAssignment> assignments) {
        this.assignments = assignments;
    }

    public StudentAssignment getSelectedAssignment() {
        return selectedAssignment;
    }

    public void setSelectedAssignment(StudentAssignment selectedAssignment) {
        this.selectedAssignment = selectedAssignment;
    }
}
