package org.cypmaster.beans;

import org.cypmaster.entities.Student;
import org.cypmaster.services.StudentService;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
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

    public void addStudent() {
        System.out.println("Adding student:" + newStudent.toString());

        RequestContext context = RequestContext.getCurrentInstance();


        newStudent.setName("");
        newStudent.setEmail("");
        context.addCallbackParam("success", true);


    }

    public void deleteStudent() {
        System.out.println("Deleting student:" + selectedStudent);
    }

    public void deleteStudent(Student student) {
        System.out.println("Deleting student:" + student);
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
