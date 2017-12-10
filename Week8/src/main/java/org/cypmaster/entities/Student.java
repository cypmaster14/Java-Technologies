package org.cypmaster.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Ciprian at 12/4/2017
 */

@Cacheable(true)
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @JoinTable(name = "students_skills",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"student_id", "skill_id"})}

    )
    @ManyToMany
    private Set<Skill> skills;


    @OneToMany(mappedBy = "primaryKey.students", cascade = CascadeType.ALL)
    private Set<StudentsProject> studentsProject;


    @JoinTable(name = "assignments",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"student_id", "project_id"})}
    )
    @ManyToOne
    private Project assignedProject;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Project getAssignedProject() {
        return assignedProject;
    }

    public void setAssignedProject(Project assignedProject) {
        this.assignedProject = assignedProject;
    }

    public Set<StudentsProject> getStudentsProject() {
        return studentsProject;
    }

    public void setStudentsProject(Set<StudentsProject> studentsProject) {
        this.studentsProject = studentsProject;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
//                ", assignedProject=" + assignedProject +
                '}';
    }
}
