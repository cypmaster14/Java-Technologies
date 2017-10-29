package org.cypmaster.DAO;

import org.cypmaster.entities.Skill;
import org.cypmaster.entities.Student;
import org.cypmaster.entities.StudentsProject;
import org.cypmaster.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {


    private Connection connection;
    private final static String GET_STUDENTS = "SELECT * FROM Students";
    private final static String ADD_STUDENT = "INSERT INTO Students(name,email) VALUES(?,?,?)";
    private final static String GET_STUDENT_SKILLS = "SELECT sk.id,sk.name FROM Students s" +
            "  JOIN Students_Skills ss ON s.id = ss.student_id" +
            "  JOIN Skills sk ON ss.skill_id = sk.id" +
            "  WHERE ss.student_id=?";
    private final static String GET_STUDENT_PROJECTS = "SELECT sp.project_id,sp.level_of_preferences FROM Projects p" +
            "  JOIN Students_Projects sp ON p.id = sp.project_id" +
            "  JOIN Students s ON sp.student_id = s.id" +
            "  WHERE s.id=?" +
            "  ORDER BY sp.level_of_preferences ASC";

    public StudentDAOImpl() {
        this.connection = ConnectionUtil.getInstance().connection;
    }

    @Override
    public List<Student> getStudents() throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_STUDENTS);
        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setEmail(resultSet.getString("email"));

            List<Skill> skills = getStudentSkills(student.getId());
            student.setSkills(skills);

            List<StudentsProject> projects = getStudentProjects(student.getId());
            student.setStudentsProjects(projects);

            students.add(student);
            System.out.println(student);
        }

        return students;
    }

    @Override
    public void addStudent(Student student) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(ADD_STUDENT);
        statement.setString(1, student.getName());
        statement.setString(2, student.getEmail());
        statement.execute();
    }


    private List<Skill> getStudentSkills(int studentId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_SKILLS);
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Skill> skills = new ArrayList<>();
            while (resultSet.next()) {
                Skill skill = new Skill();
                skill.setId(resultSet.getInt(1));
                skill.setName(resultSet.getString(2));
                skills.add(skill);
            }
            return skills;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private List<StudentsProject> getStudentProjects(int studentId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_PROJECTS);
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<StudentsProject> projects = new ArrayList<>();
            while (resultSet.next()) {
                StudentsProject studentsProject = new StudentsProject();
                studentsProject.setStudentId(studentId);
                studentsProject.setProjectId(resultSet.getInt(1));
                studentsProject.setLevelOfPreferences(resultSet.getInt(2));
                projects.add(studentsProject);
            }
            return projects;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }
}
