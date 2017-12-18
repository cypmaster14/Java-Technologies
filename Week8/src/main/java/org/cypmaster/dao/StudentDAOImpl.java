package org.cypmaster.dao;

import org.cypmaster.entities.*;
import org.cypmaster.utils.StudentToProjectAssignment;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.*;
import java.util.*;

/**
 * Created by Ciprian at 12/4/2017
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class StudentDAOImpl implements StudentDAO {

    @PersistenceContext(unitName = "Week7")
    private EntityManager entityManager;


    public StudentDAOImpl() {
//        this.entityManager = PersistenceUtil.getEntityManager();
    }

    @Override
    public List<Student> findAll() {
        Query query = entityManager.createQuery("FROM Student");
        List<Student> students = query.getResultList();

        return students;
    }

    @Override
    public void delete(long id) {

        Optional<Student> student = Optional.ofNullable(entityManager.find(Student.class, id));
        if (student.isPresent()) {
            entityManager.remove(student.get());
        }

    }

    @Override
    public void update(Student student) {

        Optional<Student> aux = Optional.ofNullable(entityManager.find(Student.class, student.getId()));
        if (aux.isPresent()) {
            entityManager.merge(student);
        }
    }

    @Override
    public void add(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(long id) {
        return entityManager.find(Student.class, id);
    }


    @Override
    public List<Student> findUnallocatedStudent() {
        Query query = entityManager.createQuery("SELECT s.name || ' ' || s.studentsProject.size from Student s where s.studentsProject.size < (Select count(p.id) from Project p)");
        List<Student> students = query.getResultList();
        return students;
    }

    @Override
    public List<String> findProjectWithStudentPreference() {

        Query query = entityManager.createQuery(
                "SELECT concat(avg(sp.levelOfPreference),' ', sp.id.projects.name) from StudentsProject sp " +
                        "group by sp.id.projects.id,sp.id.projects.name " +
                        "ORDER BY 1 asc ");
        List<String> studentsProjects = query.getResultList();
        return studentsProjects;
    }

    @Override
    public List<Student> findStudentsWithNonePreferences() {
        return null;
    }

    @Override
    public void assignStudentToProject(List<StudentToProjectAssignment> assignments) throws Exception {
        for (StudentToProjectAssignment assignment : assignments) {
            System.out.println("Saving:" + assignment);
            Student student = entityManager.find(Student.class, assignment.getStudentId());
            Project project = entityManager.find(Project.class, assignment.getProjectId());

            student.setAssignedProject(project);
            project.getAssignedStudent().add(student);

            entityManager.persist(student);
            entityManager.persist(project);
        }
    }


    @Override
    public void populate() {
        Skill skill1 = new Skill();
        skill1.setName("JavaEE");

        Skill skill2 = new Skill();
        skill2.setName("JavaScript");

        Skill skill3 = new Skill();
        skill3.setName("Spring");

        Skill skill4 = new Skill();
        skill4.setName("Python");

        Skill skill5 = new Skill();
        skill5.setName("Node.js");

        Student student1 = new Student();
        student1.setName("Lazar Lila-Ciprian");
        student1.setEmail("l@mail.com");

        Student student2 = new Student();
        student2.setName("Gabor Silviu");
        student2.setEmail("g@mail.com");

        Student student3 = new Student();
        student3.setName("Groza Vasile");
        student3.setEmail("v@mail.com");

        Student student4 = new Student();
        student4.setName("Stefan Cernescu");
        student4.setEmail("sc@mail.com");

        Student student5 = new Student();
        student5.setName("Lupu Silviu");
        student5.setEmail("ls@mail.com");

        student1.setSkills(new HashSet<>(Arrays.asList(skill1, skill2, skill3, skill4)));
        student2.setSkills(new HashSet<>(Arrays.asList(skill1, skill3, skill4)));
        student3.setSkills(new HashSet<>(Arrays.asList(skill2, skill5, skill4)));
        student4.setSkills(new HashSet<>(Arrays.asList(skill1, skill5, skill3, skill4)));
        student5.setSkills(new HashSet<>(Arrays.asList(skill2, skill4)));

        Project project1 = new Project();
        project1.setName("Java EE Project");
        project1.setDescription("Some Description");
        project1.setCapacity(2);

        Project project2 = new Project();
        project2.setName("Python Scrapper");
        project2.setDescription("Some Description");
        project2.setCapacity(1);


        Project project3 = new Project();
        project3.setName("OPID");
        project3.setDescription("Some Description");
        project3.setCapacity(1);


        Project project4 = new Project();
        project4.setName("EDeC");
        project4.setDescription("Some Description");
        project4.setCapacity(1);

        student1.setAssignedProject(project3);
        student2.setAssignedProject(project1);
        student3.setAssignedProject(project2);
        student4.setAssignedProject(project3);
        student5.setAssignedProject(project4);

        ProjectSkills projectSkills1_1 = new ProjectSkills();
        projectSkills1_1.setPrimaryKey(new ProjectSkillId());
        projectSkills1_1.setProject(project1);
        projectSkills1_1.setSkill(skill1);
        projectSkills1_1.setLevelOfPreference(0);
        ProjectSkills projectSkills1_2 = new ProjectSkills();
        projectSkills1_2.setPrimaryKey(new ProjectSkillId());
        projectSkills1_2.setProject(project1);
        projectSkills1_2.setSkill(skill3);
        projectSkills1_2.setLevelOfPreference(1);
        ProjectSkills projectSkills1_3 = new ProjectSkills();
        projectSkills1_3.setPrimaryKey(new ProjectSkillId());
        projectSkills1_3.setProject(project1);
        projectSkills1_3.setSkill(skill2);
        projectSkills1_3.setLevelOfPreference(2);

        project1.setSkills(new HashSet<>(Arrays.asList(projectSkills1_1, projectSkills1_2, projectSkills1_3)));


        ProjectSkills projectSkills2_1 = new ProjectSkills();
        projectSkills2_1.setPrimaryKey(new ProjectSkillId());
        projectSkills2_1.setProject(project2);
        projectSkills2_1.setSkill(skill4);
        projectSkills2_1.setLevelOfPreference(0);
        ProjectSkills projectSkills2_2 = new ProjectSkills();
        projectSkills2_2.setPrimaryKey(new ProjectSkillId());
        projectSkills2_2.setProject(project2);
        projectSkills2_2.setSkill(skill2);
        projectSkills2_2.setLevelOfPreference(1);

        project2.setSkills(new HashSet<>(Arrays.asList(projectSkills2_1, projectSkills2_2)));


        ProjectSkills projectSkills3_1 = new ProjectSkills();
        projectSkills3_1.setPrimaryKey(new ProjectSkillId());
        projectSkills3_1.setProject(project3);
        projectSkills3_1.setSkill(skill5);
        projectSkills3_1.setLevelOfPreference(0);
        ProjectSkills projectSkills3_2 = new ProjectSkills();
        projectSkills3_2.setPrimaryKey(new ProjectSkillId());
        projectSkills3_2.setProject(project3);
        projectSkills3_2.setSkill(skill2);
        projectSkills3_2.setLevelOfPreference(1);
        ProjectSkills projectSkills3_3 = new ProjectSkills();
        projectSkills3_3.setPrimaryKey(new ProjectSkillId());
        projectSkills3_3.setProject(project3);
        projectSkills3_3.setSkill(skill4);
        projectSkills3_3.setLevelOfPreference(2);

        project3.setSkills(new HashSet<>(Arrays.asList(projectSkills3_1, projectSkills3_2, projectSkills3_3)));


        ProjectSkills projectSkills4_1 = new ProjectSkills();
        projectSkills4_1.setPrimaryKey(new ProjectSkillId());
        projectSkills4_1.setProject(project4);
        projectSkills4_1.setSkill(skill5);
        projectSkills4_1.setLevelOfPreference(0);
        ProjectSkills projectSkills4_2 = new ProjectSkills();
        projectSkills4_2.setPrimaryKey(new ProjectSkillId());
        projectSkills4_2.setProject(project4);
        projectSkills4_2.setSkill(skill2);
        projectSkills4_2.setLevelOfPreference(1);

        project4.setSkills(new HashSet<>(Arrays.asList(projectSkills4_1, projectSkills4_2)));

        entityManager.getTransaction().begin();

        try {

            entityManager.persist(student1);
            entityManager.persist(student2);
            entityManager.persist(student3);
            entityManager.persist(student4);
            entityManager.persist(student5);

            entityManager.persist(skill1);
            entityManager.persist(skill2);
            entityManager.persist(skill3);
            entityManager.persist(skill4);
            entityManager.persist(skill5);

            entityManager.persist(project1);
            entityManager.persist(project2);
            entityManager.persist(project3);
            entityManager.persist(project4);

            entityManager.persist(projectSkills1_1);
            entityManager.persist(projectSkills1_2);
            entityManager.persist(projectSkills1_3);

            entityManager.persist(projectSkills2_1);
            entityManager.persist(projectSkills2_2);

            entityManager.persist(projectSkills3_1);
            entityManager.persist(projectSkills3_2);
            entityManager.persist(projectSkills3_3);

            entityManager.persist(projectSkills4_1);
            entityManager.persist(projectSkills4_2);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                System.out.println("Transaction active");
                entityManager.getTransaction().rollback();
            }
        }
    }


    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
