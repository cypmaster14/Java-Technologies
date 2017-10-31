package org.cypmaster.beans;

import org.cypmaster.entities.Project;
import org.cypmaster.entities.ProjectSkills;
import org.cypmaster.services.ProjectService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "projectsSkillSBean")
@ViewScoped
public class ProjectsSkillsBean {


    private ProjectService projectService;

    private List<Project> projects;
    private Map<Integer, String> projectsForSelect;
    private Integer selectedProjectId;
    private List<ProjectSkills> projectSkills;
    private ProjectSkills selectedProjectSkill;


    @PostConstruct
    public void init() {
        this.projectService = ProjectService.getInstance();
        this.projects = projectService.getProjects();
        this.projectsForSelect = new HashMap<>();
        projects.forEach(project -> projectsForSelect.put(project.getId(), project.getName()));

    }


    public void onSelectedProject() {
        System.out.println("Selected project:" + selectedProjectId);
        projectSkills = getSkillsOfProjectById();
        System.out.printf("Skills of project:%d are:%s\n", selectedProjectId, projectSkills.toString());
    }


    private List<ProjectSkills> getSkillsOfProjectById() {
        for (Project project : projects) {
            if (project.getId() == selectedProjectId) {
                return project.getProjectSkills();
            }
        }
        return Collections.EMPTY_LIST;
    }

    public Integer getSelectedProjectId() {
        return selectedProjectId;
    }

    public void setSelectedProjectId(Integer selectedProjectId) {
        this.selectedProjectId = selectedProjectId;
    }

    public List<ProjectSkills> getProjectSkills() {
        return projectSkills;
    }

    public void setProjectSkills(List<ProjectSkills> projectSkills) {
        this.projectSkills = projectSkills;
    }

    public ProjectSkills getSelectedProjectSkill() {
        return selectedProjectSkill;
    }

    public void setSelectedProjectSkill(ProjectSkills selectedProjectSkill) {
        this.selectedProjectSkill = selectedProjectSkill;
    }

    public Map<Integer, String> getProjectsForSelect() {
        return projectsForSelect;
    }

    public void setProjectsForSelect(Map<Integer, String> projectsForSelect) {
        this.projectsForSelect = projectsForSelect;
    }
}
