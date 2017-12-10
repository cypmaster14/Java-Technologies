package org.cypmaster.services;

import org.cypmaster.DAO.AssignmentDAO;
import org.cypmaster.DAO.AssignmentsDAOImpl;
import org.cypmaster.entities.Assignment;
import org.cypmaster.utils.StudentAssignment;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ciprian at 11/8/2017
 */
public class AssignmentService {

    private static AssignmentService instance;

    private AssignmentDAO assignmentDAO;

    public AssignmentService() {
        this.assignmentDAO = new AssignmentsDAOImpl();
    }

    public synchronized static AssignmentService getInstance() {
        if (instance == null) {
            instance = new AssignmentService();
        }
        return instance;
    }

    public List<Assignment> getAssignments() {
        try {
            List<Assignment> assignments = assignmentDAO.getAssignments();
            return assignments;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    public boolean updateAssignment(StudentAssignment studentAssignment) {
        try {
            assignmentDAO.updateAssignment(studentAssignment.getStudent().getId(), studentAssignment.getProjectID());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
