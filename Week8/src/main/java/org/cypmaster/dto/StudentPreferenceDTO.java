package org.cypmaster.dto;

import org.cypmaster.entities.Student;

import java.util.Comparator;

/**
 * Created by Ciprian at 12/5/2017
 */
public class StudentPreferenceDTO implements Comparable<StudentPreferenceDTO> {

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


    @Override
    public String toString() {
        return "{" +
                "student=" + student.getName() +
                ", levelOfPreference=" + levelOfPreference +
                '}';
    }

    @Override
    public int compareTo(StudentPreferenceDTO o) {
        if (this.getLevelOfPreference() < o.getLevelOfPreference()) {
            return -1;
        }
        return 1;
    }
}
