package org.cypmaster.beans;

import org.cypmaster.entities.Skill;
import org.cypmaster.services.SkillService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "skillBean")
@ViewScoped
public class SkillBean implements Serializable {

    private final static Long serialVersionUID = 1L;

    private SkillService skillService;
    private List<Skill> skills;
    private Skill selectedSkill;
    private String newSkillName;

    @PostConstruct
    public void init() {
        this.skillService = SkillService.getInstance();
        this.skills = skillService.getSkills();
    }

    public void addSkill() {
        System.out.println("Add skill:" + newSkillName);
        Skill newSkill = new Skill();
        newSkill.setName(newSkillName);

        boolean skillAdded = skillService.addSkill(newSkill);
        RequestContext context = RequestContext.getCurrentInstance();
        if (skillAdded) {
            skills.add(newSkill);
            addMessage("Skill added", FacesMessage.SEVERITY_INFO);
            context.addCallbackParam("success", true);
        } else {
            addMessage("Failed to add the skill", FacesMessage.SEVERITY_ERROR);
            newSkillName = "";
            context.addCallbackParam("success", false);
        }
    }

    public void deleteSkill() {
        System.out.printf("Delete skill:%s", selectedSkill);
        boolean skillWasDeleted = skillService.deleteSkill(selectedSkill);
        if (skillWasDeleted) {
            addMessage("Skill was deleted", FacesMessage.SEVERITY_INFO);
            skills.remove(selectedSkill);
        } else {
            addMessage("Some error occurred during the deletion of skill", FacesMessage.SEVERITY_ERROR);
        }
    }


    public void onEditSkillSave(RowEditEvent event) {
        Skill skill = (Skill) event.getObject();
        System.out.println("[Skill Edit]" + skill);
        addMessage("Edit Skill:" + skill.getName(), FacesMessage.SEVERITY_INFO);
    }

    public void onEditSkillCancel(RowEditEvent event) {
        Skill skill = (Skill) event.getObject();
        addMessage("Edit Canceled:" + skill.getName(), FacesMessage.SEVERITY_INFO);
    }


    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Skill getSelectedSkill() {
        return selectedSkill;
    }

    public void setSelectedSkill(Skill selectedSkill) {
        this.selectedSkill = selectedSkill;
    }

    public String getNewSkillName() {
        return newSkillName;
    }

    public void setNewSkillName(String newSkillName) {
        this.newSkillName = newSkillName;
    }

    public void addMessage(String summary, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
