/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.model.ProjectDTO;


/**
 * Contains methods to convert View object to POJO and viceversa.
 * 
 * @author Niraimathi S
 * @version 1.0
 */
public class Mapper{

    /**
     * Converts Employee to EmployeeVO.
     * 
     * @param employee-employee object which is to be converted into DTO.
     * @return employeeVO-converted employeeVO object.
     */
    public static EmployeeVO EmployeeToEmployeeDTO(Employee employee) {
        EmployeeVO employeeVO = new EmployeeVO();
        employeeVO.setEmployeeId(employee.getEmployeeId());
        employeeVO.setName(employee.getName());
        employeeVO.setEmail(employee.getEmail());
        employeeVO.setMobileNumber(employee.getMobileNumber());
        employeeVO.setDateOfBirth(employee.getDateOfBirth());
        employeeVO.setSalary(employee.getSalary());
        Set<AddressDTO> addressesDTO = new HashSet<AddressDTO>();
        Set<ProjectDTO> projectsDTO = new HashSet<ProjectDTO>();
        if ((null != employee.getAddresses())
                && (!employee.getAddresses().isEmpty())) {
            for(Address address:employee.getAddresses()) {
                addressesDTO.add(AddressToAddressDTO(address));
            }
            employeeVO.setaddressesDTO(addressesDTO);
        }

        if ((null != employee.getProjects())
                && (!employee.getProjects().isEmpty())) {
            for(Project project:employee.getProjects()) {
                projectsDTO.add(ProjectToProjectDTO(project));
            }
            employeeVO.setProjectsDTO(projectsDTO);
        }


        return employeeVO;
    }

    /**
     * Converts EmployeeVO to Employee.
     *
     * @param employeeVO-employeeVO object to convert into POJO object.
     * @return employee-converted employee object.
     */
    public static Employee EmployeeDTOToEmployee(EmployeeVO employeeVO) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeVO.getEmployeeId()); 
        employee.setName(employeeVO.getName());
        employee.setEmail(employeeVO.getEmail());
        employee.setMobileNumber(employeeVO.getMobileNumber());
        employee.setDateOfBirth(employeeVO.getDateOfBirth());
        employee.setSalary(employeeVO.getSalary());
        Set<Address> addresses = new HashSet<Address>();
        Set<Project> projects = new HashSet<Project>();

        if ((null != employeeVO.getaddressesDTO()) 
                && (!employeeVO.getaddressesDTO().isEmpty()))  {
            for(AddressDTO addressDTO:employeeVO.getaddressesDTO()) {
                addresses.add(AddressDTOToAddress(addressDTO));
            }
            employee.setAddresses(addresses);
        }

        if ((null != employeeVO.getProjectsDTO()) 
                && (!employeeVO.getProjectsDTO().isEmpty())){
            for(ProjectDTO projectDTO:employeeVO.getProjectsDTO()) {
                projects.add(ProjectDTOToProject(projectDTO));
            }
            employee.setProjects(projects);
        }


        return employee;
    }

    /**
     * Converts AddressDTO to Address.
     *
     * @param AddressDTO-AddressDTO object to convert into POJO object.
     * @return Address-converted Address object.
     */
    public static Address AddressDTOToAddress(AddressDTO addressDTO) {
        Address address = new Address();
        EmployeeVO employeeVO = new EmployeeVO();
        address.setAddressId(addressDTO.getAddressId());
        address.setDoorNumber(addressDTO.getDoorNumber());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setCountry(addressDTO.getCountry());
        address.setPinCode(addressDTO.getPinCode());
        employeeVO = addressDTO.getEmployeeVO();

        if (null != employeeVO) {
            Employee employee = new Employee();
            employee.setEmployeeId(employeeVO.getEmployeeId()); 
            employee.setName(employeeVO.getName());
            employee.setEmail(employeeVO.getEmail());
            employee.setMobileNumber(employeeVO.getMobileNumber());
            employee.setDateOfBirth(employeeVO.getDateOfBirth());
            employee.setSalary(employeeVO.getSalary());
            address.setEmployee(employee);
        }
        return address;
    }

    /**
     * Converts Address to AddressDTO.
     *
     * @param Address-Address object to convert into DTO object.
     * @return AddressDTO-converted AddressDTO object.
     */
    public static AddressDTO AddressToAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressId(address.getAddressId());
        addressDTO.setDoorNumber(address.getDoorNumber());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setPinCode(address.getPinCode());
        Employee employee = address.getEmployee();

        if (null != employee) {
            EmployeeVO employeeVO = new EmployeeVO();
            employeeVO.setEmployeeId(employee.getEmployeeId());
            employeeVO.setName(employee.getName());
            employeeVO.setEmail(employee.getEmail());
            employeeVO.setMobileNumber(employee.getMobileNumber());
            employeeVO.setDateOfBirth(employee.getDateOfBirth());
            employeeVO.setSalary(employee.getSalary());
            addressDTO.setEmployeeVO(employeeVO);

        }
        return addressDTO;
    }

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
        return project;
    }
}
