package org.cypmaster.dao;

import org.cypmaster.entities.Student;
import org.cypmaster.utils.PersistenceUtil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Ciprian at 12/4/2017
 */
@Stateless
public class StudentDAOImpl implements StudentDAO {

    @PersistenceContext(name = "Week7")
    private EntityManager entityManager;

    public StudentDAOImpl() {
        this.entityManager = PersistenceUtil.getEntityManager();
    }

    @Override
    public List<Student> getStudents() {

        Query query = entityManager.createQuery("Select s from Student as s");
        List<Student> students = query.getResultList();
        return students;

    }
}
