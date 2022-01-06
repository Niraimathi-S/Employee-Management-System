/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.ideas2it.employeemanagement.dao.daoimpl.ProjectDAOImplementation;
import com.ideas2it.employeemanagement.exception.EmployeeManagementException;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.service.ProjectService;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImplementation;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.util.ProjectMapper;

/**
 * Performs logical operations and data manupulation.
 * Interacts with Project POJO,Project DAO and ProjectDTO and
 * validates user inputs.
 * 
 * @author Niraimathi S
 * @version 1.0 12-11-2021
 */
public class ProjectServiceImplementation implements ProjectService{
    private ProjectDAOImplementation dao = new ProjectDAOImplementation();
    private EmployeeService employeeService = null;

    /**
     * Validates the given input from the user. 
     *
     * @param input- the input to be validated.
     * @param patternToValidate-the pattern used to validate th input.
     * @return input-validated input
     */
    public boolean validateInput(String input, String patternToValidate) { 
        return input.matches(patternToValidate);
    }

    /**
     * Creates a new project. 
     *
     * @param projectDTO-The VO object to store the created project.
     * @return boolean-true if project created, else false.
     */
    public ProjectDTO createProject(ProjectDTO projectDTO)
            throws EmployeeManagementException { 
        Project project = ProjectMapper.ProjectDTOToProject(projectDTO);
        projectDTO 
                = ProjectMapper.ProjectToProjectDTO(dao.createProject(project));
        return projectDTO;
    }

    /**
     * Checks if a projectId exists or not.
     *
     * @param projectid-projectid to check if a record already exist.
     * @return boolean-true if given projectId exist, else false.
     */
    public boolean isProjectExist(int projectId)
            throws EmployeeManagementException {
        return(null != dao.getProjectById(projectId));
    }

    /**
     * Checks if projects is empty.
     *
     * @return boolean-true if empty, else false.
     */
    public boolean isRecordsEmpty()
            throws EmployeeManagementException {
        List<Project> projects = dao.getAllProjects();
        return projects.isEmpty();
    }

    /**
     * Deletes all projects. 
     *
     */
    public boolean deleteAllProject() throws EmployeeManagementException {
        return dao.deleteAllProject();
    }

    /**
     * Deletes one project. 
     *
     * @param projectDTO-The VO object to be deleted.
     */
    public boolean deleteOneProject(ProjectDTO projectDTO)
            throws EmployeeManagementException {
        return dao.deleteOneProject(ProjectMapper.ProjectDTOToProject(projectDTO));
    }

    /**
     * Updates all fields of one project. 
     *
     * @param projectDTO-The VO object with updates values project.
     * @return boolean-true if project updated, else false.
     */
    public boolean updateAllFields(ProjectDTO projectDTO)
            throws EmployeeManagementException { 
        Project project = ProjectMapper.ProjectDTOToProject(projectDTO);
        return (null != ProjectMapper.ProjectToProjectDTO(dao.updateProject(project)));
    }

    /**
     * Gets one project by Id to the user.
     *
     * @param projectid-projectid to view.
     * @return projectDTO-project get by Id.
     */
    public ProjectDTO getProjectById(int projectId)
            throws EmployeeManagementException {
        Project project = dao.getProjectById(projectId);
        ProjectDTO projectDTO = null;
        if (null != project) {
            projectDTO = ProjectMapper.ProjectToProjectDTO(project);
        }
        return projectDTO;
    }

    /**
     * Displays all projects to the user.
     *
     * @return List<projectDTO>- view object list containing all projects.
     */
    public List<ProjectDTO> viewAllProject()
            throws EmployeeManagementException {
        List<Project> projects = dao.getAllProjects();
        List<ProjectDTO> projectDetails = new ArrayList<>();
        for (Project project : projects) {
            projectDetails.add(ProjectMapper.ProjectToProjectDTO(project));
        }
        return projectDetails;
    }

    /**
     * Gets projects by Id to the user.
     *
     * @param projectIds[]-list of project id.
     * @return List<ProjectDTO>- view object list containing all projects.
     */
    public List<ProjectDTO> getProjectDTOs(int[] projectIds)
            throws EmployeeManagementException {
        List<ProjectDTO> projects = new ArrayList<ProjectDTO>();
        ProjectDTO projectDTO;
        for (int i : projectIds) {

            if ( null != getProjectById(i)) {
                projectDTO = getProjectById(i);
                projects.add(projectDTO);
            }
        }
        return projects;     
    }

    public List<EmployeeVO> getEmployeeDTOs(int[] employeeIds)
            throws EmployeeManagementException {
        if (null == employeeService) {
            employeeService = new EmployeeServiceImplementation();
        }
        return employeeService.getEmployeeDTOs(employeeIds); 
    }
    
    public List<EmployeeVO> getAllEmployeeDTOs()
            throws EmployeeManagementException {
        if (null == employeeService) {
            employeeService = new EmployeeServiceImplementation();
        }
        return employeeService.viewAllEmployee(); 
    }

}