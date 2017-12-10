package org.cypmaster.entities;

import javax.persistence.*;

/**
 * Created by Ciprian at 12/4/2017
 */
@Entity
@Table(name = "students_projects")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.studentId", joinColumns = @JoinColumn(name = "student_id")),
        @AssociationOverride(name = "primaryKey.projectId", joinColumns = @JoinColumn(name = "project_id"))
})
public class StudentsProject {

    @Id
    private StudentProjectId primaryKey;


    @Column(name = "level_of_preferences")
    private int levelOfPreference;


    public StudentProjectId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(StudentProjectId primaryKey) {
        this.primaryKey = primaryKey;
    }

    public int getLevel() {
        return levelOfPreference;
    }

    public void setLevel(int levelOfPreference) {
        this.levelOfPreference = levelOfPreference;
    }

    @Override
    public String toString() {
        return primaryKey.toString() + " " + levelOfPreference;
    }
}
