package org.cypmaster.services;

import org.cypmaster.DAO.SkillDAO;
import org.cypmaster.DAO.SkillDAOImpl;
import org.cypmaster.entities.Skill;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class SkillService implements Serializable {

    private final static long serialVersionUID = 1L;

    private static SkillService INSTANCE;

    private SkillDAO skillDAO;

    public static SkillService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SkillService();
        }
        return INSTANCE;
    }

    private SkillService() {
        this.skillDAO = new SkillDAOImpl();
    }

    public List<Skill> getSkills() {
        try {
            List<Skill> skills = skillDAO.getSkills();
            return skills;

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public boolean addSkill(Skill skill) {
        try {
            skillDAO.addSkill(skill);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSkill(Skill skill) {
        try {
            skillDAO.deleteSkill(skill);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
