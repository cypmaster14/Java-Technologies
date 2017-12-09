package org.cypmaster.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Ciprian at 12/4/2017
 */
@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "skills", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Student> students;

    @OneToMany(mappedBy = "primaryKey.skill", cascade = CascadeType.ALL)
    private Set<ProjectSkills> projects;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<ProjectSkills> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectSkills> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
