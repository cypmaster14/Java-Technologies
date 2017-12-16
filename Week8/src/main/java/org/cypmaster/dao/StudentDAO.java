package org.cypmaster.dao;

import org.cypmaster.dto.StudentPreferenceDTO;
import org.cypmaster.entities.Project;
import org.cypmaster.entities.Student;
import org.cypmaster.entities.StudentsProject;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Ciprian at 12/4/2017
 */
@Local
public interface StudentDAO {

    List<Student> findAll();

    void delete(long id);

    void update(Student student);

    void add(Student student);

    Student findById(long id);

    List<Student> findUnallocatedStudent();

    List<String> findProjectWithStudentPreference();

    List<Student> findStudentsWithNonePreferences();


    void populate();

    void setEntityManager(EntityManager entityManager);

}
