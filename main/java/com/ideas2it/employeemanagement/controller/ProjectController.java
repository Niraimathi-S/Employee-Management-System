/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import com.ideas2it.employeemanagement.exception.EmployeeManagementException;
import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.service.ProjectService;
import com.ideas2it.employeemanagement.service.impl.ProjectServiceImplementation;
import com.ideas2it.employeemanagement.logger.EmployeeManagementLogger;

/**
 * Controller class is responsible for the flow control logic and
 * acts as intermediate between view and service classes.
 * 
 * @author Niraimathi S
 * @version 1.0
 */
public class ProjectController {
    private ProjectService projectService = new ProjectServiceImplementation();

    /**
     * Validates the given input from the user. 
     *
     * @param input- the input to be validated.
     * @param patternToValidate-the pattern used to validate th input.
     * @return validatedInput-validates input returned from ProjectService.
     */
    public boolean validateInput(String input, String patternToValidate) {
        return (projectService.validateInput(input, patternToValidate));
    }

    /**
     * Creates a new project with the data given by the user. 
     *
     * @param projectDTO-The VO object with project details and address
     * @param projectDTO-The VO object to store the created project.
     */
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        ProjectDTO returnedProjectDTO = null;
        try {
            returnedProjectDTO = projectService.createProject(projectDTO);
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        return returnedProjectDTO;
    }

    /**
     * Checks if projects is empty.
     *
     * @return boolean-true if Empty, else false
     */
    public boolean isRecordsEmpty() {
        boolean isEmpty = false;
        try {
            isEmpty = projectService.isRecordsEmpty();
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        return isEmpty;
    }

    /**
     * Deletes all projects. 
     */
    public boolean deleteAllProject() {
        boolean isDeleted = false;
        try {
            isDeleted = projectService.deleteAllProject();
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        return isDeleted;
    }

    /**
     * Checks if a projectId exists or not.
     *
     * @param projectId-Project Id to check if a record already exist.
     * @return boolean-true if given projectId exist, else false.
     */
    public boolean isProjectExist(int projectId) {
        boolean isExist = false;
        try {
            isExist = projectService.isProjectExist(projectId);
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        return isExist;
    }
    
    /**
     * Deletes one project.
     *
     * @param projectDTO-The VO object of the project to be deleted.
     * @return boolean-true if given project deleted, else false.
     */
    public boolean deleteOneProject(ProjectDTO projectDTO) {
        boolean isDeleted = true;
        try {
            isDeleted = projectService.deleteOneProject(projectDTO);
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        return isDeleted;
    }

    /**
     * Updates all fields of one project. 
     *
     * @param projectDTO-projectDTO object containing updated
     *                   values get from user
     */
    public boolean updateAllFields(ProjectDTO projectDTO) {
    	ProjectDTO returnedProjectDTO = null;
    	boolean isupdated = false;
        try {
        	isupdated = projectService.updateAllFields(projectDTO);
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        return isupdated;
    }

    /**
     * Gets an project details by Project id.
     *
     * @param projectId-The projectId to get project details.
     * @return ProjectDTO-object containing details of a single project.
     */
    public ProjectDTO getProjectById(int projectId) {
        ProjectDTO returnedProjectDTO = null;
        try {
            returnedProjectDTO = projectService.getProjectById(projectId);
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        return returnedProjectDTO;
    }

    /**
     * Views one project to the user.
     *
     * @param projectid-projectid to view.
     * @return projectDTO-object which contains details of the single project.
     */
    public ProjectDTO viewOneProject(Integer projectId) {
        ProjectDTO returnedProjectDTO = null;
        try {
            returnedProjectDTO = projectService.getProjectById(projectId);
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        return returnedProjectDTO;
    }

    /**
     * Views all projects to the user.
     *
     * @return List of all projects
     */
    public List<ProjectDTO> viewAllProject() {
        List<ProjectDTO> projects = new ArrayList<ProjectDTO>();
        try {
            projects = projectService.viewAllProject();
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        return projects;
    }

    public List<EmployeeVO> getEmployeeDTOs(int[] employeeIds) {
        List<EmployeeVO> employees = new ArrayList<EmployeeVO>();
        try {
            employees = projectService.getEmployeeDTOs(employeeIds);
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        return employees;
    }
}

