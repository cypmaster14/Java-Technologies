package org.cypmaster.beans;

import org.cypmaster.entities.Student;
import org.cypmaster.services.StudentService;
import org.cypmaster.utils.UserTracking;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@ManagedBean(name = "studentBean")
@ViewScoped
public class StudentBean implements Serializable {

    private final static Long serialVersionUID = 1L;
    private final static String CURRENT_PAGE = "studentsPage";

    private StudentService studentService;
    private List<Student> students;
    private Student newStudent;
    private Student selectedStudent;


    @PostConstruct
    public void init() {
        this.studentService = StudentService.getInstance();
        this.students = studentService.getStudents();
        this.newStudent = new Student();
        String sessionID = getSessionId();
        UserTracking.userEnteredPage(CURRENT_PAGE, sessionID);
    }

    @PreDestroy
    public void destroy() {
        String sessionID = getSessionId();
        UserTracking.userLeavedPage(CURRENT_PAGE, sessionID);
    }

    public void leavesPage() {
        System.out.printf("Leaving Page:%s\n", CURRENT_PAGE);
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getNewStudent() {
        return newStudent;
    }

    public void setNewStudent(Student newStudent) {
        this.newStudent = newStudent;
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }
}
