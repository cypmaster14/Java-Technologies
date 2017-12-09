package org.cypmaster.dao;

import org.cypmaster.entities.Project;
import org.cypmaster.utils.Filter;

import javax.ejb.Stateless;
import java.util.List;
import java.util.Map;

/**
 * Created by Ciprian at 12/9/2017
 */
@Stateless
public class ProjectDAOImpl implements ProjectDAO {
    @Override
    public List<Project> search(Map<String, Filter> filters) {
        return null;
    }
}
