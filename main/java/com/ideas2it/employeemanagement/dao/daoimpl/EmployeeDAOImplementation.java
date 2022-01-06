/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException; 
import org.hibernate.Session;
import org.hibernate.Query; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.ideas2it.employeemanagement.dao.EmployeeDAO;
import com.ideas2it.employeemanagement.exception.EmployeeManagementException;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.util.DatabaseConnection;
import com.ideas2it.employeemanagement.util.ConstantErrors;

/**
 * Interacts with database and does appropriate actions based on users wish.
 * 
 * @author Niraimathi S
 * @version 1.0
 */
public class EmployeeDAOImplementation implements EmployeeDAO {
    private String query;
    private String errorMessage;

    /**
     * Insert employee details into the database
     *
     * @param employee-Employee object to insert into database.
     * @return employee-inserted object if value inserted true or else null.
     */
    public Employee createEmployee(Employee employee) throws EmployeeManagementException {
        Session session = DatabaseConnection.getSession();
        errorMessage = "Exception in Employee Creation";
        Transaction transaction = null;
        int employeeId = 0;
        try {
            transaction = session.beginTransaction();
            employeeId = (int) session.save(employee);
            employee.setEmployeeId(employeeId);
            transaction.commit();
        } catch (Exception exception) {
            if (0 == employeeId) {
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantErrors.ERROR_CODE_01);
        } finally {
              DatabaseConnection.close(session);
        }
        return ((0 != employeeId) ? employee : (employee = null));
    }

    /**
     * update employee details into the database
     *
     * @param employee-Employee object to update into database.
     * @return employee-updated object if true or else null.
     */
    public Employee updateEmployee(Employee employee) 
            throws EmployeeManagementException  {
        Session session = DatabaseConnection.getSession();
        errorMessage = "Exception in Employee Updation";     
        Transaction transaction = null;
        boolean isAddressadded = false;
        try {
            transaction = session.beginTransaction();
            employee = (Employee) session.merge(employee);
            transaction.commit();
        } catch (Exception exception) {
            if (null == employee) {
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantErrors.ERROR_CODE_02);
        } finally {
            DatabaseConnection.close(session);
        }
        return employee;
    }

    /**
     * Deletes all employee details from the database
     *
     * @return true-if employee deleted or else false.
     */
    public boolean deleteAllEmployee() throws EmployeeManagementException {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        errorMessage = "Exception in Employee Delete all operation";
        query = "delete from Employee";
        int rowsAffected = 0;
        try {
            transaction = session.beginTransaction();
            Query hqlQuery = session.createQuery(query);
            rowsAffected = hqlQuery.executeUpdate();
            transaction.commit();
        } catch (Exception exception) {
            if (1 != rowsAffected) {
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantErrors.ERROR_CODE_07);
        } finally {
            DatabaseConnection.close(session);
        }
        return (1 == rowsAffected);
    }

    /**
     * Gets single employee's details from the database using Employee Id.
     *
     * @param employeeid-Employee id.
     * @return employee-employee details of a single employee.
     */
    public Employee getEmployeeById(int employeeId)
            throws EmployeeManagementException {
        Session session = DatabaseConnection.getSession();

        Transaction transaction = null;
        //query = "SELECT e FROM Employee e LEFT JOIN FETCH "
          //      + "e.addresses a LEFT JOIN FETCH e.projects where e.id=:id";
        query = "select e from Employee e left join fetch "
                + "e.addresses a left join fetch e.projects p "
                + "WHERE e.id = :id";
        errorMessage = "Exception occured while fetching single employee.";
        Employee employee = null;
        try {
            transaction = session.beginTransaction();
            Query hqlQuery = session.createQuery(query);
            hqlQuery.setParameter("id", employeeId);
            employee = (Employee) hqlQuery.uniqueResult();
            transaction.commit();
        } catch (Exception exception) {
            if (null == employee) {
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantErrors.ERROR_CODE_03);
        } finally {
            DatabaseConnection.close(session);
        }
        return employee;
    }

    /**
     * Gets single employee's details from the database using email.
     *
     * @param employee-Employee email id.
     * @return employee-employee details of a single employee.
     */
    public Employee getEmployeeByEmail(String email)
            throws EmployeeManagementException {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        query = "SELECT e FROM Employee e LEFT JOIN FETCH "
            + "e.addresses a LEFT JOIN FETCH e.projects where e.email=:email";
        errorMessage = "Exception occured while getting an employee by email";
        Employee employee = null;
        try {
            transaction = session.beginTransaction();
            Query hqlQuery = session.createQuery(query);
            hqlQuery.setParameter("email", email);
            employee = (Employee) hqlQuery.uniqueResult();
            transaction.commit();
        } catch (Exception exception) {
            if (null == employee) {
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantErrors.ERROR_CODE_04);
        } finally {
            DatabaseConnection.close(session);
        }
        return employee;
    }

    /**
     * Gets single employee's details from the database using mobile number.
     *
     * @param employee-Employee mobile number.
     * @return employee-employee details of a single employee.
     */
    public Employee getEmployeeByMobileNumber(long mobileNumber)
            throws EmployeeManagementException {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        query = "SELECT e FROM Employee e LEFT JOIN FETCH "
            + "e.addresses a LEFT JOIN FETCH e.projects where e.mobileNumber=:mobileNumber";
        errorMessage = "Exception while getting an employee by mobile number";
        Employee employee = null;
        try {
            transaction = session.beginTransaction();
            Query hqlQuery = session.createQuery(query);
            hqlQuery.setParameter("mobileNumber", mobileNumber);
            employee = (Employee) hqlQuery.uniqueResult();
            transaction.commit();
        } catch (Exception exception) {
            if (null == employee) {
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantErrors.ERROR_CODE_05);
        } finally {
            DatabaseConnection.close(session);
        }
        return employee;
    }

    /**
     * Deletes one employee and address details from the database
     *
     * @return true-if one employees is deleted or else false.
     */
    public boolean deleteOneEmployee(Employee employee)
            throws EmployeeManagementException {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        errorMessage = "Exception occured during deletion of single employee";
        int rowsAffected = 0;
        try {
            transaction = session.beginTransaction();
            session.delete(employee);
            rowsAffected = 1;
            transaction.commit();
        } catch (Exception exception) {
            if (1 != rowsAffected) {
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantErrors.ERROR_CODE_08);
        } finally {
            DatabaseConnection.close(session);
        }
        return (1 == rowsAffected);
    }

    /**
     * Gets all employee's details from the database.
     *
     * @return employees-list contains all employee details.
     */
    public List<Employee> getAllEmployees() throws EmployeeManagementException {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        query = "SELECT distinct e FROM Employee e LEFT JOIN FETCH "
                + "e.addresses a LEFT JOIN FETCH e.projects";
        errorMessage = "Exception occured during fetching all employee";
        List<Employee> employees = null;
        try {
            transaction = session.beginTransaction();
            employees = session.createQuery(query).list();
            transaction.commit();

        } catch (Exception exception) {
            if (employees.isEmpty()) {
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantErrors.ERROR_CODE_06);
        } finally {
            DatabaseConnection.close(session);
        }
        return employees;
    }
}
