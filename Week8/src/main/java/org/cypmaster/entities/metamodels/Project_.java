package org.cypmaster.entities.metamodels;

import org.cypmaster.entities.Project;
import org.cypmaster.entities.ProjectSkills;
import org.cypmaster.entities.Student;
import org.cypmaster.entities.StudentsProject;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Created by Ciprian at 12/10/2017
 */
@StaticMetamodel(Project.class)
public class Project_ {

    public static volatile SingularAttribute<Project, Long> id;
    public static volatile SingularAttribute<Project, String> name;
    public static volatile SingularAttribute<Project, String> description;
    public static volatile SingularAttribute<Project, Integer> capacicty;
    public static volatile SetAttribute<Project, ProjectSkills> skills;
    public static volatile ListAttribute<Project, Student> assignedStudent;
    public static volatile SetAttribute<Project, StudentsProject> students;

}
