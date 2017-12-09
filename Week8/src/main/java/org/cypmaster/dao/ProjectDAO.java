package org.cypmaster.dao;

import org.cypmaster.entities.Project;
import org.cypmaster.utils.Filter;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

/**
 * Created by Ciprian at 12/9/2017
 */
@Local
public interface ProjectDAO {

    List<Project> search(Map<String, Filter> filters);

}
