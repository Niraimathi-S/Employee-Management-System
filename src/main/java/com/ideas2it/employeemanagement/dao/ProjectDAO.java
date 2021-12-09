/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.dao;

import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.exception.EmployeeManagementException;
import com.ideas2it.employeemanagement.util.DatabaseConnection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;

/**
 * Interacts with database and does appropriate actions based on users wish.
 * 
 * @author Niraimathi S
 * @version 1.0 12-11-2021
 */

public interface ProjectDAO {
    /**
     * Insert project details into the database
     *
     * @param project-Project object to insert into database.
     * @return project-inserted object if true or else null.
     */
    public Project createProject(Project project)
            throws EmployeeManagementException;

    /**
     * update project details into the database
     *
     * @param project-Project object to update into database.
     */
    public Project updateProject(Project project)
            throws EmployeeManagementException;

    /**
     * Deletes project details from the database
     *
     * @return true-if project deleted or else false.
     */
    public boolean deleteAllProject() throws EmployeeManagementException;

    /**
     * Gets single project's details from the database using Project Id.
     *
     * @param project-Project id.
     * @return project-project details of a single project.
     */
    public Project getProjectById(int projectId)
            throws EmployeeManagementException;

    /**
     * Deletes all projects details from the database
     *
     * @param projectVO-The VO object to be deleted.
     * @return true-if all projects are deleted or else false.
     */
    public boolean deleteOneProject(Project project)
            throws EmployeeManagementException;

    /**
     * Gets all project's details from the database.
     *
     * @return projects-list contains all project details.
     */
    public List<Project> getAllProjects() throws EmployeeManagementException;
}
