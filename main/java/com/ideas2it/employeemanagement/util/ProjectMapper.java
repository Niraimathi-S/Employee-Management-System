/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.model.ProjectDTO;


/**
 * Contains methods to convert View object to POJO and viceversa.
 * 
 * @author Niraimathi S
 * @version 1.0 12-11-2021
 */
public class ProjectMapper{

    /**
     * Converts Project to ProjectDTO.
     * 
     * @param project-project object which is to be converted into DTO.
     * @return projectDTO-converted projectDTO object.
     */
    public static ProjectDTO ProjectToProjectDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId(project.getProjectId());
        projectDTO.setName(project.getName());
        projectDTO.setDomainName(project.getDomainName());
        projectDTO.setStartDate(project.getStartDate());
        projectDTO.setManager(project.getManager()); 
        Set<EmployeeVO> employeesVO = new HashSet<EmployeeVO>();
        if ((null != project.getEmployees()) 
                && (!project.getEmployees().isEmpty())) {
            for(Employee employee:project.getEmployees()) {
                EmployeeVO employeeVO = new EmployeeVO();
                employeeVO.setEmployeeId(employee.getEmployeeId());
                employeeVO.setName(employee.getName());
                employeeVO.setEmail(employee.getEmail());
                employeeVO.setMobileNumber(employee.getMobileNumber());
                employeeVO.setDateOfBirth(employee.getDateOfBirth());
                employeeVO.setSalary(employee.getSalary());
                //addressDTO.setEmployeeVO(employeeVO);
                //employeesVO.add(Mapper.EmployeeToEmployeeDTO(employee));
                employeesVO.add(employeeVO);
            }
            projectDTO.setEmployeesVO(employeesVO);
        }

        return projectDTO;
    }

    /**
     * Converts ProjectDTO to Project.
     *
     * @param projectDTO-projectDTO object to convert into POJO object.
     * @return project-converted project object.
     */
    public static Project ProjectDTOToProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setProjectId(projectDTO.getProjectId());
        project.setName(projectDTO.getName());
        project.setDomainName(projectDTO.getDomainName());
        project.setStartDate(projectDTO.getStartDate());
        project.setManager(projectDTO.getManager()); 
        Set<Employee> employees = new HashSet<Employee>();

        if ((null != projectDTO.getEmployeesVO()) 
                && (!projectDTO.getEmployeesVO().isEmpty())) {
            for(EmployeeVO employeeVO:projectDTO.getEmployeesVO()) {
                Employee employee = new Employee();
                employee.setEmployeeId(employeeVO.getEmployeeId()); 
                employee.setName(employeeVO.getName());
                employee.setEmail(employeeVO.getEmail());
                employee.setMobileNumber(employeeVO.getMobileNumber());
                employee.setDateOfBirth(employeeVO.getDateOfBirth());
                employee.setSalary(employeeVO.getSalary());
                //address.setEmployee(employee);
                //employees.add(Mapper.EmployeeDTOToEmployee(employeeVO));
                employees.add(employee);
            }
            project.setEmployees(employees);
        }

        return project;
    }
}
