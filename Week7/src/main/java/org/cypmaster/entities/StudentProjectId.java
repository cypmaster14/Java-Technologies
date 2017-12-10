package org.cypmaster.entities;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Ciprian at 12/4/2017
 */
@Embeddable
public class StudentProjectId implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    private Student students;

    @ManyToOne(cascade = CascadeType.ALL)
    private Project projects;

    public Student getStudents() {
        return students;
    }

    public void setStudents(Student students) {
        this.students = students;
    }

    public Project getProjects() {
        return projects;
    }

    public void setProjects(Project projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "StudentProjectId{" +
                "students=" + students +
                ", projects=" + projects +
                '}';
    }
}
