package org.cypmaster.services;

import org.cypmaster.dao.StudentDAO;
import org.cypmaster.dao.StudentDAOImpl;
import org.cypmaster.entities.Project;
import org.cypmaster.entities.Student;
import org.cypmaster.entities.StudentsProject;

import javax.ejb.EJB;
import java.util.List;

/**
 * Created by Ciprian at 12/4/2017
 */
public class StudentService {

    private static StudentService studentService;

    @EJB
    private StudentDAO studentDAO;

    private StudentService() {
        this.studentDAO = new StudentDAOImpl();
    }

    public synchronized static StudentService getInstance() {
        if (studentService == null) {
            studentService = new StudentService();
        }
        return studentService;
    }

    public List<Student> getStudents() {
        return studentDAO.getStudents();
    }

    public List<Student> getUnallocatedStudent() {
        return studentDAO.getUnallocatedStudent();
    }

    public List<StudentsProject> getProjectsWithStudentPreference() {
        return studentDAO.getProjectWithStudentPreference();
    }

}
