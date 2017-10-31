package org.cypmaster.services;

import org.cypmaster.DAO.StudentsSkillDAO;
import org.cypmaster.DAO.StudentsSkillDAOImpl;

import java.sql.SQLException;

public class StudentsSkillService {

    private static StudentsSkillService INSTANCE;

    private final StudentsSkillDAO studentsSkillDAO;

    private StudentsSkillService() {
        this.studentsSkillDAO = new StudentsSkillDAOImpl();
    }


    public synchronized static StudentsSkillService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StudentsSkillService();
        }
        return INSTANCE;
    }

    public boolean addSkillToStudent(int studentId, int skillId) {
        try {
            studentsSkillDAO.addSkillToStudent(studentId, skillId);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
