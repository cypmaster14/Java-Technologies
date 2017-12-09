package org.cypmaster.services;

import org.cypmaster.dao.ProjectDAO;
import org.cypmaster.entities.Project;
import org.cypmaster.utils.Filter;

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


    public List<Project> search(Map<String, Filter> selectedFilters) {
        return projectDAO.search(selectedFilters);
    }


}
