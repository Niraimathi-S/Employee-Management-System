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
    private String query;

    /**
     * Insert employee details into the database
     *
     * @param employee-Employee object to insert into database.
     * @return employee-inserted object if value inserted true or else null.
     */
    public Employee createEmployee(Employee employee) {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        int employeeId = 0;
        try {
            transaction = session.beginTransaction();
            employeeId = (int) session.save(employee);
            employee.setEmployeeId(employeeId);
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            if (0 == employeeId) {
                transaction.rollback();
            }
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
    public Employee updateEmployee(Employee employee) {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        boolean isAddressadded = false;
        try {
            transaction = session.beginTransaction();
            session.merge(employee);
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            if (null == employee) {
                transaction.rollback();
            }
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
    public boolean deleteAllEmployee() {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        query = "delete from Employee";
        int rowsAffected = 0;
        try {
            transaction = session.beginTransaction();
            Query hqlQuery = session.createQuery(query);
            rowsAffected = hqlQuery.executeUpdate();
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            if (1 != rowsAffected) {
                transaction.rollback();
            }
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
    public Employee getEmployeeById(int employeeId) {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        query = "SELECT e FROM Employee e LEFT JOIN FETCH "
                + "e.addresses where e.id=:id";
        Employee employee = null;
        try {
            transaction = session.beginTransaction();
            Query hqlQuery = session.createQuery(query);
            hqlQuery.setParameter("id", employeeId);
            employee = (Employee) hqlQuery.uniqueResult();
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            if (null == employee) {
                transaction.rollback();
            }
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
    public Employee getEmployeeByEmail(String email) {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        Employee employee = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add(Restrictions.eq("email", email));
            employee = (Employee) criteria.uniqueResult();
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            if (null == employee) {
                transaction.rollback();
            }
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
    public Employee getEmployeeByMobileNumber(long mobileNumber) {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        Employee employee = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add(Restrictions.eq("mobileNumber", mobileNumber));
            employee = (Employee) criteria.uniqueResult();
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            if (null == employee) {
                transaction.rollback();
            }
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
    public boolean deleteOneEmployee(Employee employee) {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        int rowsAffected = 0;
        try {
            transaction = session.beginTransaction();
            session.delete(employee);
            rowsAffected = 1;
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            if (1 != rowsAffected) {
                transaction.rollback();
            }
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
    public List<Employee> getAllEmployees() {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        query = "SELECT distinct e FROM Employee e LEFT JOIN FETCH e.addresses";
        List<Employee> employees = null;
        try {
            transaction = session.beginTransaction();
            employees = session.createQuery(query).list();
            transaction.commit();

        } catch (Exception exception) {
            exception.printStackTrace();
            if (employees.isEmpty()) {
                transaction.rollback();
            }
        } finally {
            DatabaseConnection.close(session);
        }
        return employees;
    }
}
