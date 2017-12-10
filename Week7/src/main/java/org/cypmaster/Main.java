package org.cypmaster;

import org.cypmaster.dto.StudentPreferenceDTO;
import org.cypmaster.entities.Project;
import org.cypmaster.entities.Student;
import org.cypmaster.entities.StudentsProject;
import org.cypmaster.services.StudentService;
import org.modelmapper.ModelMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ciprian at 12/4/2017
 */
public class Main {


    public static StudentService studentService = StudentService.getInstance();


    public static void getStudents() {
        List<Student> students = studentService.getStudents();
        students.forEach(System.out::println);
    }

    public static void getProjectsWithStudentPreference() {
        List<StudentsProject> studentsProjects = studentService.getProjectsWithStudentPreference();
        studentsProjects.forEach(System.out::println);
    }


    public static void main(String[] args) {
        getStudents();
        System.out.println();
        getProjectsWithStudentPreference();
    }
}
