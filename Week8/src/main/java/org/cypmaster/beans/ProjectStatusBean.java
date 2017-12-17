package org.cypmaster.beans;

import org.cypmaster.entities.Project;
import org.cypmaster.entities.Student;
import org.cypmaster.services.ProjectService;
import org.cypmaster.services.StudentService;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ciprian at 12/17/2017
 */
@Startup
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class ProjectStatusBean {

    @EJB
    private StudentService studentService;
    @EJB
    private ProjectService projectService;

    private Map<Project, List<Student>> projectToAssignStudents;

    @PostConstruct
    public void init() {
        projectToAssignStudents = new HashMap<>();
        List<Project> projects = projectService.findAll();
        System.out.println("[SINGLETON PROJECT STATUS BEAN]");
        projects.forEach(project -> {
            projectToAssignStudents.put(project, project.getAssignedStudent());
            System.out.println(project);
            System.out.println(project.getAssignedStudent());
        });
    }

    @Lock(LockType.READ)
    public List<Student> getAssignedStudents(Project project) {
        return projectToAssignStudents.get(project);
    }

    @Lock(LockType.WRITE)
    public void assignStudentToProject(Project project, Student student) {
        System.out.printf("Assign project %s to student %s", project, student);
        projectToAssignStudents.get(project).add(student);
    }

    @Lock(LockType.READ)
    public Map<Project, List<Student>> getProjectToAssignStudents() {
        return projectToAssignStudents;
    }
}

