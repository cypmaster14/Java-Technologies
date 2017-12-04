package org.cypmaster.dao;

import org.cypmaster.entities.Student;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Ciprian at 12/4/2017
 */
@Local
public interface StudentDAO {

    List<Student> getStudents();

}
