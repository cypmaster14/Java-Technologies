package org.cypmaster.dto;

import org.cypmaster.entities.Student;

/**
 * Created by Ciprian at 12/5/2017
 */
public class StudentPreferenceDTO {

    private Student student;
    private int levelOfPreference;


    public StudentPreferenceDTO() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getLevelOfPreference() {
        return levelOfPreference;
    }

    public void setLevelOfPreference(int levelOfPreference) {
        this.levelOfPreference = levelOfPreference;
    }
}
