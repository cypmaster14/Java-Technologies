package org.cypmaster.DAO;

import org.cypmaster.entities.Assignment;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ciprian at 11/8/2017
 */
public interface AssignmentDAO {

    List<Assignment> getAssignments() throws SQLException;

    void updateAssignment(int studentId, int projectId) throws SQLException;
}
