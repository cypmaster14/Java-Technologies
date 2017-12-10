package org.cypmaster.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Ciprian at 12/4/2017
 */
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "primaryKey.project", cascade = CascadeType.ALL)
    private Set<ProjectSkills> skills;

    @OneToOne(mappedBy = "assignedProject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Student assignedStudent;

    @OneToMany(mappedBy = "primaryKey.projects")
    private Set<StudentsProject> students;

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


    public Set<ProjectSkills> getSkills() {
        return skills;
    }

    public void setSkills(Set<ProjectSkills> skills) {
        this.skills = skills;
    }

    public Student getAssignedStudent() {
        return assignedStudent;
    }

    public void setAssignedStudent(Student assignedStudent) {
        this.assignedStudent = assignedStudent;
    }

    public Set<StudentsProject> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentsProject> students) {
        this.students = students;
    }


    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
