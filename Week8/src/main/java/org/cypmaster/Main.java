package org.cypmaster;

import org.cypmaster.dto.StudentPreferenceDTO;
import org.cypmaster.entities.Project;
import org.cypmaster.entities.Student;
import org.cypmaster.entities.StudentsProject;
import org.cypmaster.services.StudentService;
import org.modelmapper.ModelMapper;

import java.util.*;

/**
 * Created by Ciprian at 12/4/2017
 */
public class Main {


    public static StudentService studentService =  StudentService.getInstance();


    public static void getStudents() {
        List<Student> students = studentService.getStudents();
        students.forEach(System.out::println);
    }

    public static void getProjectsWithStudentPreference() {
        List<StudentsProject> studentsProjects = studentService.getProjectsWithStudentPreference();
        Map<Project, List<StudentPreferenceDTO>> projectToStudentPreference = new HashMap<>();
        for (StudentsProject studentsProject : studentsProjects) {

            Project project = studentsProject.getPrimaryKey().getProjects();
            StudentPreferenceDTO studentPreferenceDTO = new StudentPreferenceDTO();
            studentPreferenceDTO.setStudent(studentsProject.getPrimaryKey().getStudents());
            studentPreferenceDTO.setLevelOfPreference(studentsProject.getLevel());

            List<StudentPreferenceDTO> studentPreference = projectToStudentPreference.getOrDefault(project, new ArrayList<>());
            studentPreference.add(studentPreferenceDTO);

            projectToStudentPreference.put(project, studentPreference);
        }

        projectToStudentPreference.forEach((key, value) -> {
            Collections.sort(value);
            System.out.println(key + " " + value);
        });

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
        getProjectsWithStudentPreference();
    }
}
