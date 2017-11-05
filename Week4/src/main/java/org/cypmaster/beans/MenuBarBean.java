package org.cypmaster.beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by Ciprian at 11/5/2017
 */
@ManagedBean(name = "menuBarBean")
@ApplicationScoped
public class MenuBarBean implements Serializable {

    private final static long serialVersionUID = 1L;


    public String goToHomePage() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        System.out.println(req.getRequestURL().toString());
        return "goToHomePage";
    }

    public String goToStudentsPage() {
        return "goToStudentsPage";
    }

    public String goToSkillsPage() {
        return "goToSkillsPage";
    }
}
