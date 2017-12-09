package org.cypmaster.entities.metamodels;

import org.cypmaster.entities.Skill;
import org.cypmaster.entities.Student;
import org.cypmaster.entities.StudentsProject;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Created by Ciprian at 12/9/2017
 */
@StaticMetamodel(Student.class)
public class Student_ {

    public static volatile SingularAttribute<Student, Long> id;
    public static volatile SingularAttribute<Student, String> name;
    public static volatile SingularAttribute<Student, String> email;
    public static volatile SetAttribute<Student, Skill> skills;
    public static volatile SetAttribute<Student, StudentsProject> studentsProject;
}
