/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.dao.impl;

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

import com.ideas2it.employeemanagement.dao.ProjectDAO;
import com.ideas2it.employeemanagement.exception.EmployeeManagementException;
import com.ideas2it.employeemanagement.logger.EmployeeManagementLogger;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.util.DatabaseConnection;
import com.ideas2it.employeemanagement.util.ConstantErrors;

/**
 * Interacts with database and does appropriate actions based on users wish.
 * 
 * @author Niraimathi S
 * @version 1.0
 */
public class ProjectDAOImplementation implements ProjectDAO {

    /**
     * Insert project details into the database
     *
     * @param project-Project object to insert into database.
     * @return project-inserted object if value inserted true or else null.
     */
    public Project createProject(Project project) throws EmployeeManagementException {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        int projectId = 0;
        try {
            transaction = session.beginTransaction();
            projectId = (int) session.save(project);
            project.setProjectId(projectId);
            transaction.commit();
        } catch (HibernateException exception) {
        	if (null != transaction) {
                transaction.rollback();
            }
        	EmployeeManagementLogger.logger.error(ConstantErrors.getError()
        			.get(ConstantErrors.ERROR_CODE_03), exception);
            throw new EmployeeManagementException(ConstantErrors.ERROR_CODE_09);
        } finally {
              DatabaseConnection.close(session);
        }
        return ((0 != projectId) ? project : (project = null));
    }

    /**
     * update project details into the database
     *
     * @param project-Project object to update into database.
     * @return project-updated object if true or else null.
     */
    public Project updateProject(Project project) throws EmployeeManagementException {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            project = (Project) session.merge(project);
            transaction.commit();
        } catch (HibernateException exception) {
        	if (null != transaction) {
                transaction.rollback();
            }
        	EmployeeManagementLogger.logger.error(ConstantErrors.getError()
        			.get(ConstantErrors.ERROR_CODE_03), exception);
            throw new EmployeeManagementException(ConstantErrors.ERROR_CODE_10);
        } finally {
            DatabaseConnection.close(session);
        }
        return project;
    }

    /**
     * Deletes all project details from the database
     *
     * @return true-if project deleted or else false.
     */
    public boolean deleteAllProject() throws EmployeeManagementException {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        String query = "delete from Project";
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
            throw new EmployeeManagementException(ConstantErrors.ERROR_CODE_14);
        } finally {
            DatabaseConnection.close(session);
        }
        return (1 == rowsAffected);
    }

    /**
     * Gets single project's details from the database using Project Id.
     *
     * @param projectid-Project id.
     * @return project-project details of a single project.
     */
    public Project getProjectById(int projectId) throws EmployeeManagementException {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        String query = "select p from Project p left join fetch p.employees e"
                + " where p.id = :id";
        Project project = null;
        try {
            transaction = session.beginTransaction();
            Query hqlQuery = session.createQuery(query);
            hqlQuery.setParameter("id", projectId);
            project = (Project) hqlQuery.uniqueResult();
        } catch (HibernateException exception) {
        	if (null != transaction) {
                transaction.rollback();
            }
        	EmployeeManagementLogger.logger.error(ConstantErrors.getError()
        			.get(ConstantErrors.ERROR_CODE_03), exception);
            throw new EmployeeManagementException(ConstantErrors.ERROR_CODE_11);
        } finally {
            DatabaseConnection.close(session);
        }
        return project;
    }

    /**
     * Deletes one project and address details from the database
     *
     * @return true-if one projects is deleted or else false.
     */
    public boolean deleteOneProject(Project project) 
    		throws EmployeeManagementException {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        int rowsAffected = 0;
        try {
            transaction = session.beginTransaction();
            session.delete(project);
            rowsAffected = 1;
        } catch (HibernateException exception) {
        	if (null != transaction) {
                transaction.rollback();
            }
        	EmployeeManagementLogger.logger.error(ConstantErrors.getError()
        			.get(ConstantErrors.ERROR_CODE_03), exception);
            throw new EmployeeManagementException(ConstantErrors.ERROR_CODE_13);
        } finally {
            DatabaseConnection.close(session);
        }
        return (1 == rowsAffected);
    }

    /**
     * Gets all project's details from the database.
     *
     * @return projects-list contains all project details.
     */
    public List<Project> getAllProjects() throws EmployeeManagementException {
        Session session = DatabaseConnection.getSession();
        Transaction transaction = null;
        String query = "SELECT distinct p FROM Project p LEFT JOIN FETCH p.employees";
        List<Project> projects = null;
        try {
            transaction = session.beginTransaction();
            projects = session.createQuery(query).list();

        } catch (HibernateException exception) {
        	if (null != transaction) {
                transaction.rollback();
            }
        	EmployeeManagementLogger.logger.error(ConstantErrors.getError()
        			.get(ConstantErrors.ERROR_CODE_03), exception);
            throw new EmployeeManagementException(ConstantErrors
            		.ERROR_CODE_12);
        } finally {
            DatabaseConnection.close(session);
        }
        return projects;
    }
}
