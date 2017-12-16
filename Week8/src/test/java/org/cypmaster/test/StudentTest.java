package org.cypmaster.test;

import org.cypmaster.dao.ProjectDAO;
import org.cypmaster.dao.ProjectDAOImpl;
import org.cypmaster.dao.StudentDAO;
import org.cypmaster.dao.StudentDAOImpl;
import org.cypmaster.dto.StudentPreferenceDTO;
import org.cypmaster.entities.Project;
import org.cypmaster.entities.Student;
import org.cypmaster.entities.StudentsProject;
import org.cypmaster.utils.PersistenceUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.naming.NamingException;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Ciprian at 12/9/2017
 */
public class StudentTest {

    private static EntityManagerFactory emf;
    private static EntityManager entityManager;
    private static StudentDAO studentDAO;
    private static ProjectDAO projectDAO;

    @BeforeClass
    public static void setUp() throws NamingException {
        emf = PersistenceUtil.getEntityManagerFactory(org.cypmaster.utils.PersistenceUtil.PERSISTENCE_UNIT_TEST_NAME);
        entityManager = emf.createEntityManager();
        studentDAO = new StudentDAOImpl();
        studentDAO.setEntityManager(entityManager);
        projectDAO = new ProjectDAOImpl();
        projectDAO.setEntityManager(entityManager);
    }

    @AfterClass
    public static void onDestroy() {
        entityManager.close();
        emf.close();
    }


    @Test
    public void studentsExists() {
        List<Student> students = studentDAO.findAll();
        System.out.println(students);
        assertTrue(students.size() != 0);
    }

    @Test
    public void studentUpdate() {
        Student gabor = studentDAO.findById(2);
        gabor.setName("Silviu #6 Gabor");
        studentDAO.update(gabor);

        Student gabor6 = studentDAO.findById(2);
        assertTrue(gabor.getName().equals(gabor6.getName()));
    }

    @Test
    public void addAndDeleteStudent() {
        List<Student> students = studentDAO.findAll();
        int numberOfStudentsBeforeOperations = students.size();


        Student studentMihalache = new Student();
        studentMihalache.setName("Mihalache Stefan");
        studentMihalache.setEmail("ms@mail.com");

        studentDAO.add(studentMihalache);

        long id = studentMihalache.getId();
        studentDAO.delete(id);

        int numberOfStudentsAfterOperations = studentDAO.findAll().size();
        assertTrue(numberOfStudentsBeforeOperations == numberOfStudentsAfterOperations);
    }

    @Test
    public void getUnallocatedStudent() {
        List<Student> unallocatedStudents = studentDAO.findUnallocatedStudent();
        System.out.println(unallocatedStudents);

        assertTrue(unallocatedStudents.size() != 0);
    }

    @Test
    public void getProjectWithStudentPreference() {
        List<String> projectPreference = studentDAO.findProjectWithStudentPreference();
        projectPreference.forEach(info -> {
            System.out.println(Arrays.toString(info.split(" ", 2)));
        });
        List<Project> projects = projectDAO.findAll();
        System.out.println(projectPreference.size() == projects.size());
    }
}
