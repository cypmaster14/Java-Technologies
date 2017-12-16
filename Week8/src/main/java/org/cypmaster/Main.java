package org.cypmaster;

import org.cypmaster.entities.Student;
import org.cypmaster.services.StudentService;

import java.util.*;

/**
 * Created by Ciprian at 12/4/2017
 */
public class Main {


    public static StudentService studentService = StudentService.getInstance();


    public static void getStudents() {
        List<Student> students = studentService.findAll();
        students.forEach(System.out::println);
    }

    public static void populate() {
        studentService.populate();
    }

    public static void getUnallocatedStudent() {
        List<Student> unallocatedStudents = studentService.getUnallocatedStudent();
        System.out.println(unallocatedStudents);
    }


    public static void main(String[] args) {
        getStudents();
        System.out.println();
        System.out.println("Unallocated students");
        getUnallocatedStudent();
        System.out.println();
        System.out.println("Projects with student preference");
    }
}
