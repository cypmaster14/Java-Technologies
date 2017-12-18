package org.cypmaster.beans;

import org.cypmaster.entities.Project;
import org.cypmaster.services.ProjectService;
import org.cypmaster.utils.ValueFilter;
import org.cypmaster.utils.RangeFilter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private Map<String, ValueFilter> valueFilters;
    private Map<String, RangeFilter> rangeFilters;
    private List<Project> projects;
    private boolean showResultProjectTable;

    @PostConstruct
    public void init() {
        this.valueFilters = new HashMap<>();
        this.valueFilters.put(PROJECT_FILTER_NAME, new ValueFilter("", false));
        this.valueFilters.put(PROJECT_FILTER_DESCRIPTION, new ValueFilter("", false));
        int numberOfProjects = projectService.findNumberOfProjects();
        this.rangeFilters = new HashMap<>();
        this.rangeFilters.put(PROJECT_FILTER_CAPACITY, new RangeFilter(1, numberOfProjects, false));
        this.projects = new ArrayList<>();
        this.showResultProjectTable = false;

    }


    public void search(ActionEvent actionEvent) {
        Map<String, ValueFilter> valueFiltersSelected = valueFilters.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSelected())
                .collect(Collectors.toMap(key -> key.getKey(), value -> value.getValue()));
        Map<String, RangeFilter> rangeFiltersSelected = rangeFilters.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSelected())
                .collect(Collectors.toMap(key -> key.getKey(), value -> value.getValue()));

        System.out.println("Search" + valueFiltersSelected + " " + rangeFiltersSelected);
        if (valueFiltersSelected.size() == 0 && rangeFiltersSelected.size() == 0) {
            addMessage("No criteria was selected");
            return;
        }

        projects = projectService.findWithFilters(valueFiltersSelected, rangeFiltersSelected);
        addMessage("Projects Found:" + projects.size());
        if (projects.size() > 0) {
            showResultProjectTable = true;
        } else {
            showResultProjectTable = false;
        }
        System.out.println("Results:" + projects);
    }


    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


    public Map<String, ValueFilter> getValueFilters() {
        return valueFilters;
    }

    public void setValueFilters(Map<String, ValueFilter> valueFilters) {
        this.valueFilters = valueFilters;
    }

    public Map<String, RangeFilter> getRangeFilters() {
        return rangeFilters;
    }

    public void setRangeFilters(Map<String, RangeFilter> rangeFilters) {
        this.rangeFilters = rangeFilters;
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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public boolean isShowResultProjectTable() {
        return showResultProjectTable;
    }

    public void setShowResultProjectTable(boolean showResultProjectTable) {
        this.showResultProjectTable = showResultProjectTable;
    }
}
