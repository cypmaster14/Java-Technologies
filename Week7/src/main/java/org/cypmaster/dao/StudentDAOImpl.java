package org.cypmaster.dao;

import org.cypmaster.entities.Student;
import org.cypmaster.entities.StudentsProject;
import org.cypmaster.utils.PersistenceUtil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transaction;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ciprian at 12/4/2017
 */
@Stateless
public class StudentDAOImpl implements StudentDAO {

//    @PersistenceContext(name = "Week7")
    private EntityManager entityManager;

    public StudentDAOImpl() {
        this.entityManager = PersistenceUtil.getEntityManager();
    }

    @Override
    public List<Student> getStudents() {

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            Query query = entityManager.createQuery("Select s from Student as s");
            List<Student> students = query.getResultList();
            transaction.commit();
            return students;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return Collections.emptyList();
        }
    }


    @Override
    public List<Student> getUnallocatedStudent() {
        Query query = entityManager.createQuery("select p from Project as p");
        Integer numberOfProjects = query.getResultList().size();
        Query query2 = entityManager.createQuery("Select distinct s.name from Student as s, Project as p where s.studentsProject.size < ?");
        List<Student> students = query2.setParameter(0, numberOfProjects).getResultList();
        return students;
    }

    @Override
    public List<StudentsProject> getProjectWithStudentPreference() {

        Query query = entityManager.createQuery("SELECT sp from StudentsProject  as sp");
        List<StudentsProject> studentsProjects = query.getResultList();
        return studentsProjects;

    }

}
