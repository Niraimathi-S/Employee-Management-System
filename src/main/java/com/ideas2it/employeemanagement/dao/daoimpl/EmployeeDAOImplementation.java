/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.dao.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.ideas2it.employeemanagement.dao.EmployeeDAO;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.util.DatabaseConnection;

/**
 * Interacts with database and does appropriate actions based on users wish.
 * 
 * @author Niraimathi S
 * @version 1.0
 */
public class EmployeeDAOImplementation implements EmployeeDAO {
    private PreparedStatement preparedStatement;
    private String query;

    /**
     * Insert employee details into the database
     *
     * @param employee-Employee object to insert into database.
     * @return employee-inserted object if value inserted true or else null.
     */
    public Employee createEmployee(Address address) {
        Connection connection = null;
        query = "insert into employee(name,mobile_number,email,salary,"
                + "date_of_birth) values(?,?,?,?,?)";
        preparedStatement = null;
        int rowsAffected = 0;
        boolean isAddressadded = false;
        ResultSet resultSet = null;
        Employee employee = address.getEmployee();
        int employeeId = 0;
        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,employee.getName());
            preparedStatement.setLong(2,employee.getMobileNumber());
            preparedStatement.setString(3,employee.getEmail());
            preparedStatement.setFloat(4,employee.getSalary());
            preparedStatement.setDate(5,Date.valueOf(employee.getDateOfBirth()));
            rowsAffected = preparedStatement.executeUpdate();
            query = "select last_insert_id()";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            employeeId = resultSet.getInt(1);
            employee.setEmployeeId(employeeId);
            if (1 == rowsAffected) {
                isAddressadded = insertAddress(address,employeeId);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(resultSet);
        }
        return ((isAddressadded) ? employee : (employee = null));
    }

    /**
     * Insert address details of an into the database.
     *
     * @param address-address object to insert into database.
     * @param employeeid-employeeid.
     * @return true if address inserted else false.
     */
    public boolean insertAddress(Address address, int employeeId) {
        Connection connection = null;
        query = "insert into address(door_number,street,city,state,country,pincode,employee_id) values(?,?,?,?,?,?,?)";
        preparedStatement = null;
        int rowsAffected = 0;
        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setInt(1,0);
            preparedStatement.setString(1,address.getDoorNumber());
            preparedStatement.setString(2,address.getStreet());
            preparedStatement.setString(3,address.getCity());
            preparedStatement.setString(4,address.getState());
            preparedStatement.setString(5,address.getCountry());
            preparedStatement.setInt(6,address.getPinCode());
            preparedStatement.setInt(7,employeeId);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
            DatabaseConnection.close(preparedStatement);
        }
        return (1 == rowsAffected);
    }

    /**
     * update employee details into the database
     *
     * @param employee-Employee object to update into database.
     * @return employee-updated object if true or else null.
     */
    public Employee updateEmployee(Address address) {
        Connection connection = null;
        preparedStatement = null;
        boolean isAddressadded = false;
        Employee employee = address.getEmployee();
        int employeeId = 0;
        query = "update employee set name=?,mobile_number=?,email=?,"
                + "salary=?,date_of_birth=? where id=?";
        int rowsAffected = 0;
        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            employeeId = employee.getEmployeeId();
            preparedStatement.setInt(6,employeeId);
            preparedStatement.setString(1,employee.getName());
            preparedStatement.setLong(2,employee.getMobileNumber());
            preparedStatement.setString(3,employee.getEmail());
            preparedStatement.setFloat(4,employee.getSalary());
            preparedStatement.setDate(5,Date.valueOf(employee.getDateOfBirth()));
            rowsAffected = preparedStatement.executeUpdate();
            if (null != address.getDoorNumber()) {
                isAddressadded = insertAddress(address,employeeId);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
            DatabaseConnection.close(preparedStatement);

        }
        return ((1 == rowsAffected) ? employee : (employee = null));
    }

    /**
     * Deletes all employee details from the database
     *
     * @return true-if employee deleted or else false.
     */
    public boolean deleteAllEmployee() {
        Connection connection = null;
        preparedStatement = null;
        query = "delete from employee";
        int rowsAffected = 0;
        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
            DatabaseConnection.close(preparedStatement);
        }
        return (1 == rowsAffected);
    }

    /**
     * Gets single employee's details from the database using Employee Id.
     *
     * @param employeeid-Employee id.
     * @return employee-employee details of a single employee.
     */
    public Employee getEmployeeById(int employeeId) {
        List<Address> addresses = new ArrayList<>(getAddressById(employeeId));
        Connection connection = null;
        preparedStatement = null;
        Employee employee = null;
        query = "select id,name,mobile_number,email,salary,date_of_birth from" 
                + " employee where id=" + employeeId;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                employee=new Employee(resultSet.getInt(1),resultSet.getString(2),
                                  resultSet.getString(4),resultSet.getLong(3),
                                  resultSet.getDate(6).toLocalDate(),
                                  resultSet.getFloat(5),addresses);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(resultSet);
        }
        return employee;
    }

    /**
     * Gets single employee's address list from the database using Employee Id.
     *
     * @param employeeid-Employee id.
     * @return List<Address>-all addresses of a single employee.
     */
    public List<Address> getAddressById(int employeeId) {
        Connection connection = null;
        preparedStatement = null;
        Address address = null;
        List<Address> addresses = null; 
        query = "select id,door_number,street,city,state,country,pincode,employee_id from" 
                + " address where employee_id=" + employeeId;
        ResultSet resultSet = null;
        try {
            addresses = new ArrayList<Address>();
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                address = new Address(resultSet.getInt(1), resultSet.getString(2),
                                  resultSet.getString(3), resultSet.getString(4),
                                  resultSet.getString(5), resultSet.getString(6),
                                  resultSet.getInt(7), resultSet.getInt(8));
                addresses.add(address);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(resultSet);
        }
        return addresses;
    }

    /**
     * Gets single employee's details from the database using email.
     *
     * @param employee-Employee email id.
     * @return employee-employee details of a single employee.
     */
    public Employee getEmployeeByEmail(String email) {
        Connection connection = null;
        preparedStatement = null;
        Employee employee = null;
        query = "select * from employee where email= '" + email + "'";
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                employee=new Employee(resultSet.getInt(1),resultSet.getString(2),
                                  resultSet.getString(4),resultSet.getLong(3),
                                  resultSet.getDate(6).toLocalDate(),
                                  resultSet.getFloat(5));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(resultSet);
        }
        return employee;
    }

    /**
     * Gets single employee's details from the database using mobile number.
     *
     * @param employee-Employee mobile number.
     * @return employee-employee details of a single employee.
     */
    public Employee getEmployeeByMobileNumber(long mobileNumber) {
        Connection connection = null;
        preparedStatement = null;
        Employee employee = null;
        query = "select id,name,mobile_number,email,salary,date_of_birth from" 
                + " employee where mobile_number=" + mobileNumber;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                employee=new Employee(resultSet.getInt(1),resultSet.getString(2),
                                  resultSet.getString(4),resultSet.getLong(3),
                                  resultSet.getDate(6).toLocalDate(),
                                  resultSet.getFloat(5));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(resultSet);
        }
        return employee;
    }

    /**
     * Deletes one employees details from the database
     *
     * @return true-if one employees is deleted or else false.
     */
    public boolean deleteOneEmployee(int employeeId) {
        Connection connection = null;
        query = "delete from employee where id=?";
        preparedStatement = null;
        int rowsAffected = 0;
        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,employeeId);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
            DatabaseConnection.close(preparedStatement);
        }
        return (1 == rowsAffected);
    }

    /**
     * Deletes one employee's address from the database
     *
     * @return true-if deleted or else false.
     */
    public boolean deleteAddress(int addressId) {
        Connection connection = null;
        query = "delete from address where id=?";
        preparedStatement = null;
        int rowsAffected = 0;
        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,addressId);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
            DatabaseConnection.close(preparedStatement);
        }
        return (1 == rowsAffected);
    }

    /**
     * Gets all employee's details from the database.
     *
     * @return employees-list contains all employee details.
     */
    public List<Employee> getAllEmployees() {
        Connection connection=null;  
        preparedStatement=null; 
        ResultSet resultSet = null; 
        Employee employee = null;
        query = "select id,name,mobile_number,email,salary,date_of_birth"
                + " from employee";
        List<Employee> employees = null;
        List<Address> addresses = new ArrayList<>();
        try {
            employees = new ArrayList<Employee>();
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee=new Employee(resultSet.getInt(1),resultSet.getString(2),
                                  resultSet.getString(4),resultSet.getLong(3),
                                  resultSet.getDate(6).toLocalDate(),
                                  resultSet.getFloat(5));
                employees.add(employee);
            }
            if(!employees.isEmpty()) {
                for (int index = 0; index < employees.size(); index++) {
                     addresses = getAddressById(employees.get(index).getEmployeeId());
                     employees.get(index).setaddresses(addresses);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(resultSet);
        }
        return employees;
    }
}
