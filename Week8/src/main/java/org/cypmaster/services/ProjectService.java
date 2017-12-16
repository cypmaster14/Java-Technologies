package org.cypmaster.services;

import org.cypmaster.dao.ProjectDAO;
import org.cypmaster.entities.Project;
import org.cypmaster.utils.RangeFilter;
import org.cypmaster.utils.ValueFilter;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Map;

/**
 * Created by Ciprian at 12/9/2017
 */
@Stateless
@Local
public class ProjectService {

    @EJB
    private ProjectDAO projectDAO;

    public ProjectService() {

    }


    public List<Project> search(Map<String, ValueFilter> valueFiltersSelected, Map<String, RangeFilter> rangeFiltersSelected) {
        return projectDAO.search(valueFiltersSelected, rangeFiltersSelected);
    }

    public int findNumberOfProjects() {
        return projectDAO.findNumberOfProjects();
    }

    public List<Project> findAll() {
        return projectDAO.findAll();
    }

    public Project findById(int id) {
        return projectDAO.findById(id);
    }

    public boolean projectIsAvailable(int id) {
        return projectDAO.projectIsAvailable(id);
    }


}
