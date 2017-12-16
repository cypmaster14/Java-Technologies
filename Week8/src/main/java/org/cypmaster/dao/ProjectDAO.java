package org.cypmaster.dao;

import org.cypmaster.entities.Project;
import org.cypmaster.utils.RangeFilter;
import org.cypmaster.utils.ValueFilter;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

/**
 * Created by Ciprian at 12/9/2017
 */
@Local
public interface ProjectDAO {

    List<Project> search(Map<String, ValueFilter> filters, Map<String, RangeFilter> rangeFiltersSelected);

    Integer findNumberOfProjects();

    List<Project> findAll();

    Project findById(int id);

    boolean projectIsAvailable(int id);

    void setEntityManager(EntityManager entityManager);



}
