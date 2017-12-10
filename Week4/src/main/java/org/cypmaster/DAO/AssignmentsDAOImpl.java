package org.cypmaster.DAO;

import org.cypmaster.entities.Assignment;
import org.cypmaster.entities.Student;
import org.cypmaster.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ciprian at 11/8/2017
 */
public class AssignmentsDAOImpl implements AssignmentDAO {

    private Connection connection;

    private final static String GET_ASSIGNMENTS = "SELECT * FROM Assignments";
    private final static String GET_STUDENT_INFO = "SELECT s.name FROM Students s" +
            " JOIN Assignments a ON s.id = a.student_id" +
            " WHERE a.student_id=?";
    private final static String UPDATE_ASSIGNMENT = "UPDATE Assignments" +
            " SET project_id=?" +
            " WHERE student_id=?";

    public AssignmentsDAOImpl() {
        this.connection = ConnectionUtil.getInstance().connection;
    }


    @Override
    public List<Assignment> getAssignments() throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(GET_ASSIGNMENTS);
        Map<Integer, List<Student>> projectToStudents = new HashMap<>();

        while (rs.next()) {

            int projectId = rs.getInt("project_id");
            List<Student> students = projectToStudents.getOrDefault(projectId, new ArrayList<>());

            Student student = new Student();
            student.setId(rs.getInt("student_id"));
            getStudentInfo(student);
            students.add(student);

            projectToStudents.put(projectId, students);
        }

        List<Assignment> assignments = new ArrayList<>();
        projectToStudents.forEach((key, value) -> {
            Assignment assignment = new Assignment(key, value);
            assignments.add(assignment);
        });

        return assignments;
    }

    @Override
    public void updateAssignment(int studentId, int projectId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(UPDATE_ASSIGNMENT);
        ps.setInt(1, projectId);
        ps.setInt(2, studentId);
        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("No rows were updated");
        }

    }


    private void getStudentInfo(Student student) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_INFO);
            preparedStatement.setInt(1, student.getId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                student.setName(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
