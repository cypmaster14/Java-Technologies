package org.cypmaster.DAO;

import org.cypmaster.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentsSkillDAOImpl implements StudentsSkillDAO {

    private Connection connection;

    private final static String INSERT_STUDENT_SKILL = "INSERT INTO Students_Skills(student_id,skill_id) VALUES(?,?)";

    public StudentsSkillDAOImpl() {
        this.connection = ConnectionUtil.getInstance().connection;
    }


    @Override
    public void addSkillToStudent(int studentId, int skillId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_STUDENT_SKILL);
        ps.setInt(1, studentId);
        ps.setInt(2, skillId);
        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Failed to add the skill to the student");
        }

    }
}
