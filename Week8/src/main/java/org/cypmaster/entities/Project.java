package org.cypmaster.entities;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Ciprian at 12/4/2017
 */
@Cacheable(true)
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

    @Column(name = "capacity")
    private int capacity;

    @OneToMany(mappedBy = "primaryKey.project", cascade = CascadeType.ALL)
    private Set<ProjectSkills> skills;

    @OneToMany(mappedBy = "assignedProject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Student> assignedStudent;

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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<ProjectSkills> getSkills() {
        return skills;
    }

    public void setSkills(Set<ProjectSkills> skills) {
        this.skills = skills;
    }


    public List<Student> getAssignedStudent() {
        return assignedStudent;
    }

    public void setAssignedStudent(List<Student> assignedStudent) {
        this.assignedStudent = assignedStudent;
    }

    public Set<StudentsProject> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentsProject> students) {
        this.students = students;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return id == project.id &&
                Objects.equals(name, project.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
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
