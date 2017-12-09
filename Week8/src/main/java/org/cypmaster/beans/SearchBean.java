package org.cypmaster.beans;

import org.cypmaster.entities.Project;
import org.cypmaster.services.ProjectService;
import org.cypmaster.utils.Filter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Ciprian at 12/9/2017
 */
@ManagedBean(name = "searchBean")
@ViewScoped
public class SearchBean implements Serializable {

    private final static Long serialVersionUID = 1L;

    @EJB
    private ProjectService projectService;

    private final static String PROJECT_FILTER_NAME = "FILTER_BY_NAME";
    private final static String PROJECT_FILTER_DESCRIPTION = "FILTER_BY_DESCRIPTION";
    private final static String PROJECT_FILTER_CAPACITY = "FILTER_BY_CAPACITY";

    private Map<String, Filter> filters;

    @PostConstruct
    public void init() {
        filters = new HashMap<>();
        filters.put(PROJECT_FILTER_NAME, new Filter("", false));
        filters.put(PROJECT_FILTER_DESCRIPTION, new Filter("", false));
        filters.put(PROJECT_FILTER_CAPACITY, new Filter("", false));

    }


    public void search(ActionEvent actionEvent) {
        System.out.println("Search" + filters);
        Map<String, Filter> selectedFilters = filters.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSelected())
                .collect(Collectors.toMap(key -> key.getKey(), value -> value.getValue()));


    }

    public void buttonAction(ActionEvent actionEvent) {
        addMessage("Welcome to Primefaces!!");
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


    public Map<String, Filter> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, Filter> filters) {
        this.filters = filters;
    }

    public String getProjectFilterName() {
        return PROJECT_FILTER_NAME;
    }

    public String getProjectFilterDescription() {
        return PROJECT_FILTER_DESCRIPTION;
    }

    public String getProjectFilterCapacity() {
        return PROJECT_FILTER_CAPACITY;
    }
}
