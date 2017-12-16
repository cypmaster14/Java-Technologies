package org.cypmaster.beans;

import org.cypmaster.dao.ProjectDAO;
import org.cypmaster.services.ProjectService;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Created by Ciprian at 12/16/2017
 */
@Stateless
@LocalBean
public class ProjectCheckingBean {

    @EJB
    private ProjectService projectService;


    public boolean projectIsAvailable(int id) {
        return projectService.projectIsAvailable(id);
    }

}
