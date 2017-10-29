package org.cypmaster.entities;

import java.util.List;

public class Student {

    private int id;
    private String name;
    private String email;
    private List<Skill> skills;
    private List<StudentsProject> studentsProjects;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<StudentsProject> getStudentsProjects() {
        return studentsProjects;
    }

    public void setStudentsProjects(List<StudentsProject> studentsProjects) {
        this.studentsProjects = studentsProjects;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", skills=" + skills +
                ", studentsProjects=" + studentsProjects +
                '}';
    }
}
