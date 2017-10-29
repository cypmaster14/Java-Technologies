package org.cypmaster.beans;

import org.cypmaster.entities.Skill;
import org.cypmaster.entities.Student;
import org.cypmaster.services.StudentService;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "studentsSkillBean")
@ViewScoped
public class StudentsSkillsBean implements Serializable {

    private final static Long serialVersionUID = 1L;

    private StudentService studentService;

    private List<Student> students;
    private Integer selectedStudentId;
    private Map<String, Integer> studentsForSelect;
    private List<Skill> studentSkills;
    private Skill selectedSkill;
    private int newSkillId;

    @PostConstruct
    public void init() {
        this.studentService = StudentService.getInstance();
        this.students = studentService.getStudents();
        studentsForSelect = new HashMap<>();
        students.forEach(student -> studentsForSelect.put(student.getName(), student.getId()));
    }

    public void onStudentSelected(ActionEvent event) {
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


    public void addSkill(ActionEvent event) {
        System.out.printf("Add skill with id:%d to student with id:%d\n", newSkillId, selectedStudentId);

        RequestContext context = RequestContext.getCurrentInstance();
        context.addCallbackParam("success", true);
    }

    public void removeSkill(ActionEvent event) {
        System.out.printf("Remove skill with id:%d from student with id:%d\n", selectedSkill.getId(), selectedStudentId);
        studentSkills.remove(selectedSkill);
        selectedSkill = null;
    }


    public Integer getSelectedStudentId() {
        return selectedStudentId;
    }

    public void setSelectedStudentId(Integer selectedStudentId) {
        this.selectedStudentId = selectedStudentId;
    }

    public Map<String, Integer> getStudentsForSelect() {
        return studentsForSelect;
    }

    public void setStudentsForSelect(Map<String, Integer> studentsForSelect) {
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

    public int getNewSkillId() {
        return newSkillId;
    }

    public void setNewSkillId(int newSkillId) {
        this.newSkillId = newSkillId;
    }
}
