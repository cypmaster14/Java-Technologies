package org.cypmaster.DAO;

import org.cypmaster.entities.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {

    List<Student> getStudents() throws SQLException;

    void addStudent(Student student) throws SQLException;
}
