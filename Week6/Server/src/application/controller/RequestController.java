package application.controller;

import application.entity.Employee;
import application.multitenancy.TenantContextRegistry;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 * Created by SilviuG on 28.11.2017.
 */
public class RequestController {
    private static RequestController INSTANCE;

    private RequestController() {
    }

    public static RequestController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RequestController();
        }
        return INSTANCE;
    }

    public int executeInsert(HttpServletRequest request) throws SQLException, NamingException {
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String email = request.getParameter("email");
        Employee employee = new Employee(name, email, country);

        return executeOperation(EmployeeController.getInstance()::save, employee, request);
    }

    public int executeDelete(HttpServletRequest request) throws SQLException, NamingException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Employee employee = new Employee(id);

        return executeOperation(EmployeeController.getInstance()::delete, employee, request);
    }

    private int executeOperation(Consumer<Employee> operation, Employee employee, HttpServletRequest request) throws SQLException, NamingException {
        long startTime = System.currentTimeMillis();
        operation.accept(employee);
        long endTime = System.currentTimeMillis();

        BigDecimal requestTime = BigDecimal.valueOf(endTime - startTime);

        try (Connection connection = TenantContextRegistry.getTenantContext().getConnectionManager().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO requests(request_time, remote_addr, request_params) VALUES (?,?,?)");
            preparedStatement.setBigDecimal(1, requestTime);
            preparedStatement.setString(2, request.getRemoteAddr());
            preparedStatement.setString(3, new HashMap<>(request.getParameterMap()).toString());
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}
