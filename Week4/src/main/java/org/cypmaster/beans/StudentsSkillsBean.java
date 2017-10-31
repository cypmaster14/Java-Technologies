package org.cypmaster.beans;

import org.cypmaster.entities.Skill;
import org.cypmaster.entities.Student;
import org.cypmaster.services.SkillService;
import org.cypmaster.services.StudentService;
import org.cypmaster.services.StudentsSkillService;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.swing.text.html.Option;
import java.io.Serializable;
import java.util.*;

@ManagedBean(name = "studentsSkillBean")
@ViewScoped
public class StudentsSkillsBean implements Serializable {

    private final static Long serialVersionUID = 1L;

    private StudentService studentService;
    private SkillService skillService;
    private StudentsSkillService studentsSkillService;

    private List<Student> students;
    private Integer selectedStudentId;
    private Map<Integer, String> studentsForSelect;
    private List<Skill> studentSkills;
    private Skill selectedSkill;
    private int newSkillIdOfStudent;
    private List<Skill> skills;

    @PostConstruct
    public void init() {
        this.studentService = StudentService.getInstance();
        this.skillService = SkillService.getInstance();
        this.students = studentService.getStudents();
        this.skills = skillService.getSkills();
        this.studentsSkillService = StudentsSkillService.getInstance();
        studentsForSelect = new HashMap<>();
        students.forEach(student -> studentsForSelect.put(student.getId(), student.getName()));
    }

    public void selectStudent() {
        System.out.println("Selected student:" + selectedStudentId);
        studentSkills = getSkillsOfStudentById();
        System.out.printf("Skills of student:%d are:%s\n", selectedStudentId, studentSkills.toString());
    }

    private List<Skill> getSkillsOfStudentById() {
        for (Student student : students) {
            if (student.getId() == selectedStudentId) {
                return student.getSkills();
            }
        }
        return Collections.EMPTY_LIST;
    }


    public void addSkill() {
        System.out.printf("Add skill with id:%d to student with id:%d\n", newSkillIdOfStudent, selectedStudentId);

        RequestContext context = RequestContext.getCurrentInstance();
        boolean skillWasAdded = studentsSkillService.addSkillToStudent(selectedStudentId, newSkillIdOfStudent);
        if (skillWasAdded) {
            Optional<Skill> addedSkill = getSkillById(newSkillIdOfStudent);
            if (addedSkill.isPresent()) {
                studentSkills.add(addedSkill.get());
            }
            context.addCallbackParam("success", true);
            addMessage("Skill was added to student", FacesMessage.SEVERITY_INFO);
        } else {
            context.addCallbackParam("success", false);
            addMessage("Skill couldn't be added", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void removeSkill(ActionEvent event) {
        System.out.printf("Remove skill with id:%d from student with id:%d\n", selectedSkill.getId(), selectedStudentId);
        studentSkills.remove(selectedSkill);
        selectedSkill = null;
    }

    public Optional<Skill> getSkillById(int skillId) {
        for (Skill skill : skills) {
            if (skill.getId() == skillId) {
                return Optional.ofNullable(skill);
            }
        }
        return Optional.ofNullable(null);
    }

    private void addMessage(String summary, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Integer getSelectedStudentId() {
        return selectedStudentId;
    }

    public void setSelectedStudentId(Integer selectedStudentId) {
        this.selectedStudentId = selectedStudentId;
    }


    public Map<Integer, String> getStudentsForSelect() {
        return studentsForSelect;
    }

    public void setStudentsForSelect(Map<Integer, String> studentsForSelect) {
        this.studentsForSelect = studentsForSelect;
    }

    public List<Skill> getStudentSkills() {
        return studentSkills;
    }

    public void setStudentSkills(List<Skill> studentSkills) {
        this.studentSkills = studentSkills;
    }

    public Skill getSelectedSkill() {
        return selectedSkill;
    }

    public void setSelectedSkill(Skill selectedSkill) {
        this.selectedSkill = selectedSkill;
    }

    public int getNewSkillIdOfStudent() {
        return newSkillIdOfStudent;
    }

    public void setNewSkillIdOfStudent(int newSkillIdOfStudent) {
        this.newSkillIdOfStudent = newSkillIdOfStudent;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

}
