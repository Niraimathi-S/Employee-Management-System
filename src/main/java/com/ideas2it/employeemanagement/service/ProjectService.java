/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.util.Mapper;

/**
 * Performs logical operations and data manupulation.
 * Interacts with Project POJO, and ProjectDTO and validates user inputs.
 * 
 * @author Niraimathi S
 * @version 1.0
 */
public interface ProjectService {

    /**
     * Validates the given input from the user. 
     *
     * @param input- the input to be validated.
     * @param patternToValidate-the pattern used to validate th input.
     */
    public boolean validateInput(String input, String patternToValidate);

    /**
     * Creates a new project. 
     *
     * @param projectDTO-The VO object to store the created project.
     */
    public ProjectDTO createProject(ProjectDTO projectDTO);

    /**
     * Checks if a projectId exists or not.
     *
     * @param projectid-projectid to check if a record already exist.
     */
    public boolean isProjectExist(int projectId);

    /**
     * Checks if projects is empty.
     *
     */
    public boolean isRecordsEmpty();

    /**
     * Checks if a mobileNumber already exists or not.
     *
     * @param mobileNumber-the number to be checked for duplicate.
     
    public boolean checkDuplicateMobileNumber(long mobileNumber);*/

    /**
     * Checks if a email already exists or not.
     *
     * @param email- email to be checked for duplicate.
     
    public boolean checkDuplicateEmail(String email);*/

    /**
     * Deletes all projects. 
     *
     */
    public boolean deleteAllProject();

    /**
     * Deletes one project. 
     *
     * @param projectDTO-The VO object of the project to be deleted.
     */
    public boolean deleteOneProject(ProjectDTO projectDTO);

    /**
     * Updates all fields of one project. 
     *
     * @param projectDTO-The VO object containing updated
     *                   values get from user
     */
    public boolean updateAllFields(ProjectDTO projectDTO);

    /**
     * Displays all projects to the user.
     *
     */
    public List<ProjectDTO> viewAllProject();

    /**
     * Gets one project by Id to the user.
     *
     * @param projectid-projectid to view.
     */
    public ProjectDTO getProjectById(int projectId);

    public List<EmployeeVO> getEmployeeDTOs(int[] employeeIds);

    public List<ProjectDTO> getProjectDTOs(int[] projectIds);
}
