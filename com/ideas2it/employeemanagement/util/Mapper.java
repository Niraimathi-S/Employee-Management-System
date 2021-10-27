/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.util;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeVO;

/**
 * Contains methods to convert View object to POJO and viceversa.
 * 
 * @author Niraimathi S
 * @version 1.0 12-11-2021
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
        return employee;
    }
}
