package org.cypmaster.services;

import org.cypmaster.dao.StudentDAO;
import org.cypmaster.dto.StudentPreferenceDTO;
import org.cypmaster.entities.Student;
import org.cypmaster.entities.StudentsProject;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Ciprian at 12/4/2017
 */
@Stateless
@Local
public class StudentService {

    private static StudentService studentService;

    @EJB
    private StudentDAO studentDAO;

    public StudentService() {
    }

    public synchronized static StudentService getInstance() {
        if (studentService == null) {
            studentService = new StudentService();
        }
        return studentService;
    }

    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    public List<Student> getUnallocatedStudent() {
        return studentDAO.findUnallocatedStudent();
    }

    public List<String> getProjectsWithStudentPreference() {
        return studentDAO.findProjectWithStudentPreference();
    }

    public void populate() {
        studentDAO.populate();
    }

}
