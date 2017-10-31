package org.cypmaster.DAO;

import java.sql.SQLException;

public interface StudentsSkillDAO {

    void addSkillToStudent(int studentId, int skillId) throws SQLException;
}
