package org.cypmaster.DAO;

import org.cypmaster.entities.Project;
import org.cypmaster.entities.ProjectSkills;
import org.cypmaster.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectDAOImpl implements ProjectDAO {


    private Connection connection;
    private final static String GET_PROJECTS = "SELECT * FROM Projects";
    private final static String GET_PROJECT_SKILLS = "SELECT s.id, ps.level_of_preferences FROM Projects p" +
            "  JOIN Projects_Skills ps ON P.id = ps.project_id" +
            "  JOIN Skills s ON ps.skill_id = s.id" +
            "  WHERE p.id=?" +
            "  ORDER BY level_of_preferences";
    private final static String INSERT_PROJECT = "INSERT INTO Projects (name,description,capacity) VALUES(?,?,?)";
    private final static String DELETE_PROJECT = "DELETE FROM Projects WHERE id=?";

    public ProjectDAOImpl() {
        this.connection = ConnectionUtil.getInstance().connection;
    }

    @Override
    public List<Project> getProjects() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_PROJECTS);
            List<Project> projects = new ArrayList<>();
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCapacity(resultSet.getInt("capacity"));

                List<ProjectSkills> projectSkills = getProjectSkills(project.getId());
                project.setProjectSkills(projectSkills);

                projects.add(project);
            }
            return projects;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }


    }

    private List<ProjectSkills> getProjectSkills(int projectId) {
        try {
            PreparedStatement ps = connection.prepareStatement(GET_PROJECT_SKILLS);
            ps.setInt(1, projectId);
            ResultSet rs = ps.executeQuery();
            List<ProjectSkills> projectSkills = new ArrayList<>();
            while (rs.next()) {
                ProjectSkills projectSkill = new ProjectSkills();
                projectSkill.setProjectId(projectId);
                projectSkill.setSkillId(rs.getInt(1));
                projectSkill.setLevelOfPreference(rs.getInt(2));
                projectSkills.add(projectSkill);
            }
            return projectSkills;

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public void addProject(Project project) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROJECT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, project.getName());
        preparedStatement.setString(2, project.getDescription());
        preparedStatement.setInt(3, project.getCapacity());
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Failed to insert new Project");
        }

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            project.setId(generatedKeys.getInt(1));
        } else {
            throw new SQLException("Failed to retrieve the id of the new project");
        }
    }

    @Override
    public void deleteProject(Project project) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PROJECT);
        preparedStatement.setInt(1, project.getId());
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Failed to remove the project");
        }
    }
}
