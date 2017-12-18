package org.cypmaster.beans;

import org.cypmaster.services.ProjectService;
import org.cypmaster.utils.PersistenceUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Created by Ciprian at 12/10/2017
 */
@ManagedBean(name = "testCacheBean")
@ViewScoped
public class TestCacheBean {

    @EJB
    private ProjectService projectService;

    private String message;


    @PostConstruct
    public void init() {
        this.message = "Test Cache";
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            projectService.findAll();
            projectService.findById(1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


