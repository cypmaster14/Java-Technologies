package application.controller;

import application.entity.Employee;
import application.multitenancy.TenantContextRegistry;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by SilviuG on 28.11.2017.
 */
public class EmployeeController {
    private static EmployeeController INSTANCE;

    private EmployeeController() {
    }

    public static EmployeeController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EmployeeController();
        }
        return INSTANCE;
    }

    public int save(Employee emp) {
        try (Connection connection = TenantContextRegistry.getTenantContext().getConnectionManager().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employees(name, email, country) VALUES (?,?,?)");
            preparedStatement.setString(1, emp.getName());
            preparedStatement.setString(2, emp.getEmail());
            preparedStatement.setString(3, emp.getCountry());

            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int delete(Employee emp) {
        try (Connection connection = TenantContextRegistry.getTenantContext().getConnectionManager().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employees WHERE id=?");
            preparedStatement.setInt(1, emp.getId());
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
