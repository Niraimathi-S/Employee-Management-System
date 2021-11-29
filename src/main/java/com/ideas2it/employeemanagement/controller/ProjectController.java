/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.service.ProjectService;
import com.ideas2it.employeemanagement.service.impl.ProjectServiceImplementation;

/**
 * Controller class is responsible for the flow control logic and
 * acts as intermediate between view and service classes.
 * 
 * @author Niraimathi S
 * @version 1.0 12-11-2021
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
        return projectService.createProject(projectDTO);
    }

    /**
     * Checks if projects is empty.
     *
     * @return boolean-true if Empty, else false
     */
    public boolean isRecordsEmpty() {
        return projectService.isRecordsEmpty();
    }

    /**
     * Checks if a mobileNumber already exists or not.
     *
     * @param mobileNumber-the number to be checked for duplicate.
     * @return boolean-true if duplicate, else false.
     
    public boolean checkDuplicateMobileNumber(long mobileNumber) {
        return projectService.checkDuplicateMobileNumber(mobileNumber);
    } */

    /**
     * Checks if a email already exists or not.
     *
     * @param email- email to be checked for duplicate.
     * @return boolean-true if duplicate, else false.
    
    public boolean checkDuplicateEmail(String email) {
        return projectService.checkDuplicateEmail(email);
    } */

    /**
     * Deletes all projects. 
     */
    public boolean deleteAllProject() {
        return projectService.deleteAllProject();
    }

    /**
     * Checks if a projectId exists or not.
     *
     * @param projectId-Project Id to check if a record already exist.
     * @return boolean-true if given projectId exist, else false.
     */
    public boolean isProjectExist(int projectId) {
        return (projectService.isProjectExist(projectId));
    }
    
    /**
     * Deletes one project.
     *
     * @param projectDTO-The VO object of the project to be deleted.
     * @return boolean-true if given project deleted, else false.
     */
    public boolean deleteOneProject(ProjectDTO projectDTO) {
        return projectService.deleteOneProject(projectDTO);
    }

    /**
     * Updates all fields of one project. 
     *
     * @param projectDTO-projectDTO object containing updated
     *                   values get from user
     */
    public boolean updateAllFields(ProjectDTO projectDTO) {
        return projectService.updateAllFields(projectDTO);
    }

    /**
     * Gets an project details by Project id.
     *
     * @param projectId-The projectId to get project details.
     * @return ProjectDTO-object containing details of a single project.
     */
    public ProjectDTO getProjectById(int projectId) {
        return projectService.getProjectById(projectId);
    }

    /**
     * Views one project to the user.
     *
     * @param projectid-projectid to view.
     * @return projectDTO-object which contains details of the single project.
     */
    public ProjectDTO viewOneProject(Integer projectId) {
        return projectService.getProjectById(projectId);
    }

    /**
     * Views all projects to the user.
     *
     * @return List of all projects
     */
    public List<ProjectDTO> viewAllProject() {
        return projectService.viewAllProject();
    }

    public List<EmployeeVO> getEmployeeDTOs(int[] employeeIds) {
        return projectService.getEmployeeDTOs(employeeIds);
    }
}

