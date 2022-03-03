/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.dao.impl;

import java.sql.SQLException;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
//import org.hibernate.Query; 
import org.hibernate.Transaction;
import org.hibernate.hql.internal.ast.QuerySyntaxException;
import org.hibernate.query.Query;

import com.ideas2it.employeemanagement.dao.EmployeeDAO;
import com.ideas2it.employeemanagement.exception.EmployeeManagementException;
import com.ideas2it.employeemanagement.logger.EmployeeManagementLogger;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.util.DatabaseConnection;
import com.ideas2it.employeemanagement.util.ConstantErrors;

/**
 * Interacts with database and does appropriate actions based on users wish.
 * 
 * @author Niraimathi S
 * @version 1.0
 */
public class EmployeeDAOImplementation implements EmployeeDAO {;

    /**
     * Insert employee details into the database
     *
     * @param employee-Employee object to insert into database.
     * @return employee-inserted object if value inserted true or else null.
     */
    public Employee createEmployee(Employee employee) 
    		throws EmployeeManagementException {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        int employeeId = 0;
        try {
            transaction = session.beginTransaction();
            employeeId = (Integer) session.save(employee);
            employee.setEmployeeId(employeeId);
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) {
                transaction.rollback();
            }
            EmployeeManagementLogger.logger.error(ConstantErrors.getError()
            		.get(ConstantErrors.ERROR_CODE_03), exception);
            throw new EmployeeManagementException(ConstantErrors
            		.ERROR_CODE_01);
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
            throws EmployeeManagementException   {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        boolean isAddressadded = false;
        try {
            transaction = session.beginTransaction();
            employee = (Employee) session.merge(employee);
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) {
                transaction.rollback();
            }
            EmployeeManagementLogger.logger.error(ConstantErrors.getError()
            		.get(ConstantErrors.ERROR_CODE_03), exception);
            throw new EmployeeManagementException(ConstantErrors
            		.ERROR_CODE_02);
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
    public boolean deleteAllEmployee() throws EmployeeManagementException  {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        String query = "delete from Employee";
        int rowsAffected = 0;
        try {
            transaction = session.beginTransaction();
            Query hqlQuery = session.createQuery(query);
            rowsAffected = hqlQuery.executeUpdate();
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) {
                transaction.rollback();
            }
            EmployeeManagementLogger.logger.error(ConstantErrors.getError()
            		.get(ConstantErrors.ERROR_CODE_03), exception);
            throw new EmployeeManagementException(ConstantErrors
            		.ERROR_CODE_07);
            
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
            throws EmployeeManagementException  {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        String query = "select e from mployee e left join fetch "
                + "e.addresses a left join fetch e.projects p "
                + "WHERE e.id = :id";
        Employee employee = null;
        try {
            transaction = session.beginTransaction();
            Query hqlQuery = session.createQuery(query);
            hqlQuery.setParameter("id", employeeId);
            employee = (Employee) hqlQuery.uniqueResult();
        } catch (Exception exception) {
            if (null != transaction) {
                transaction.rollback();
            }
            EmployeeManagementLogger.logger.error(ConstantErrors.getError().get(ConstantErrors.ERROR_CODE_03),exception);
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
        String query = "SELECT e FROM Employee e LEFT JOIN FETCH "
            + "e.addresses a LEFT JOIN FETCH e.projects where e.email=:email";
        Employee employee = null;
        try {
            transaction = session.beginTransaction();
            Query hqlQuery = session.createQuery(query);
            hqlQuery.setParameter("email", email);
            employee = (Employee) hqlQuery.uniqueResult();
        } catch (HibernateException exception) {
            if (null != transaction) {
                transaction.rollback();
            }
            EmployeeManagementLogger.logger.error(ConstantErrors.getError(
            		).get(ConstantErrors.ERROR_CODE_03), exception);
            throw new EmployeeManagementException(ConstantErrors
            		.ERROR_CODE_04);
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
            throws EmployeeManagementException  {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        String query = "SELECT e FROM Employee e LEFT JOIN FETCH "
            + "e.addresses a LEFT JOIN FETCH e.projects where e.mobileNumber=:mobileNumber";
        Employee employee = null;
        try {
            transaction = session.beginTransaction();
            Query hqlQuery = session.createQuery(query);
            hqlQuery.setParameter("mobileNumber", mobileNumber);
            employee = (Employee) hqlQuery.uniqueResult();
            transaction.commit();
        } catch (HibernateException exception) {
        	if (null != transaction) {
                transaction.rollback();
            }
        	EmployeeManagementLogger.logger.error(ConstantErrors.getError()
        			.get(ConstantErrors.ERROR_CODE_03), exception);
            throw new EmployeeManagementException(ConstantErrors
            		.ERROR_CODE_05);
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
            throws EmployeeManagementException  {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        int rowsAffected = 0;
        try {
            transaction = session.beginTransaction();
            session.delete(employee);
            rowsAffected = 1;
            transaction.commit();
        } catch (HibernateException exception) {
        	if (null != transaction) {
                transaction.rollback();
            }
        	EmployeeManagementLogger.logger.error(ConstantErrors.getError()
        			.get(ConstantErrors.ERROR_CODE_03), exception);
            throw new EmployeeManagementException(ConstantErrors
            		.ERROR_CODE_08);
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
    public List<Employee> getAllEmployees() 
    		throws EmployeeManagementException  {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        String query = "SELECT distinct e FROM Employee e LEFT JOIN FETCH "
                + "e.addresses a LEFT JOIN FETCH e.projects";
        List<Employee> employees = null;
        try {
            transaction = session.beginTransaction();
            employees = session.createQuery(query).list();

        } catch (HibernateException exception) {
        	if (null != transaction) {
                transaction.rollback();
            }
        	EmployeeManagementLogger.logger.error(ConstantErrors.getError()
        			.get(ConstantErrors.ERROR_CODE_03), exception);
            throw new EmployeeManagementException(ConstantErrors
            		.ERROR_CODE_06);
        } finally {
            DatabaseConnection.close(session);
        }
        return employees;
    }
}
