/* 
 * Copyrights (c) Ideas2It
 */

package com.ideas2it.employeemanagement.model;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.ideas2it.employeemanagement.model.Employee;

/**
 * Project is a POJO class with getter & setter methods 
 *
 * @author 	Niraimathi S
 * @version 	1.0
 */
public class Project {
    private int projectId;
    private LocalDate startDate;
    private String name;
    private String manager;
    private String domainName;
    private Set<Employee> employees = new HashSet<Employee>();

    public Project() {
    }

    /**
     * Constructor for Project class to initialize values
     */
    public Project (String name, String domainName, 
                 LocalDate startDate,  String manager) {
       // this.ProjectId = ProjectId;
        this.name = name;
        this.domainName = domainName;
        this.startDate = startDate;
        this.manager = manager;
    }

    public Project (int projectId, String name, String domainName, 
                 LocalDate startDate,  String manager) {
        this.projectId = projectId;
        this.name = name;
        this.domainName = domainName;
        this.startDate = startDate;
        this.manager = manager;
    }


    /**
     * Method to get Project id
     *
     * @return ProjectId-Project id
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * Method to set Project Name
     *
     * @param name	Project name
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    /**
     * Method to get Project name
     *
     * @return name	Project name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set Project Name
     *
     * @param name	Project name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to get Project domain name
     *
     * @return email	Project domain name
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * Method to set Project domain name
     *
     * @param email	Project domain name
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     * Method to get Project Date of birth
     *
     * @return dateOfBirth	Project's date of Birth
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Method to set Project date of birth.
     *
     * @param contactName	Project Contact Number
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Method to get Project manager
     *
     * @return name	Project manager
     */
    public String getManager() {
        return manager;
    }

    /**
     * Method to set Project manager
     *
     * @param name	Project manager
     */
    public void setManager(String manager) {
        this.manager = manager;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public int hashCode() {
        return projectId;
    }

    public boolean equals(Object o) {
        if (o instanceof Project) {
            return this.projectId == ((Project) o).projectId;
        }
        return false;
    }


    public String toString() {
        StringBuilder projectDetails = new StringBuilder();
        projectDetails.append("\nProject ID\t:").append(projectId)
                .append("\nName\t\t:").append(name).append("\nDomain name\t:")
                .append(domainName).append("\nStart date\t:").append(startDate)
                .append("\nManager\t\t:").append(manager)
                .append("\n");
        return projectDetails.toString();
    }
}
