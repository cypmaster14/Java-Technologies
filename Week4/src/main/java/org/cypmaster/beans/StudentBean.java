package org.cypmaster.beans;

import org.cypmaster.entities.Student;
import org.cypmaster.services.StudentService;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "studentBean")
@ViewScoped
public class StudentBean implements Serializable {

    private final static Long serialVersionUID = 1L;

    private StudentService studentService;
    private List<Student> students;
    private Student newStudent;
    private Student selectedStudent;


    @PostConstruct
    public void init() {
        this.studentService = StudentService.getInstance();
        this.students = studentService.getStudents();
        this.newStudent = new Student();

    }

    public void addStudent(ActionEvent event) {
        System.out.println("Adding student:" + newStudent.toString());
        RequestContext context = RequestContext.getCurrentInstance();

        boolean studentWasAdded = studentService.addStudent(newStudent);
        if (studentWasAdded) {
            context.addCallbackParam("success", true);
            addMessage("Student was added", FacesMessage.SEVERITY_INFO);
            students.add(newStudent);
            newStudent = new Student();
        } else {
            context.addCallbackParam("success", false);
            addMessage("Some error occurred during the inserting of the user.", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void removeStudent(ActionEvent event) {

        System.out.println("Deleting student:" + selectedStudent);
        boolean studentWasRemoved = studentService.removeStudent(selectedStudent);
        if (studentWasRemoved) {
            addMessage("Student was removed", FacesMessage.SEVERITY_INFO);
            students.remove(selectedStudent);
        } else {
            addMessage("Some error occurred during the removal of the student", FacesMessage.SEVERITY_ERROR);
        }
    }

    private void addMessage(String summary, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
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