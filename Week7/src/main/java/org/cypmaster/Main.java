package org.cypmaster;

import org.cypmaster.entities.Student;
import org.cypmaster.services.StudentService;

import java.util.List;

/**
 * Created by Ciprian at 12/4/2017
 */
public class Main {

    public static void main(String[] args) {
        StudentService studentService = StudentService.getInstance();
        List<Student> students = studentService.getStudents();
        System.out.println(students);
    }
}
