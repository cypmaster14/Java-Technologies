package org.cypmaster.entities;

import java.util.List;

public class Project {

    private int id;
    private String name;
    private String description;
    private int capacity;
    private List<ProjectSkills> projectSkills;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProjectSkills> getProjectSkills() {
        return projectSkills;
    }

    public void setProjectSkills(List<ProjectSkills> projectSkills) {
        this.projectSkills = projectSkills;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", capacity=" + capacity +
                ", projectSkills=" + projectSkills +
                '}';
    }
}
