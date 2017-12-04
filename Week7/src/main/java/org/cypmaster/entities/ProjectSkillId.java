package org.cypmaster.entities;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Ciprian at 12/4/2017
 */
@Embeddable
public class ProjectSkillId implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    private Skill skill;

    @ManyToOne(cascade = CascadeType.ALL)
    private Project project;

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
