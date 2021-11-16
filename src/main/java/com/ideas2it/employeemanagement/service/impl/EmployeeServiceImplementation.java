/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.employeemanagement.dao.daoimpl.EmployeeDAOImplementation;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.util.Mapper;

/**
 * Performs logical operations and data manupulation.
 * Interacts with Employee POJO,Employee DAO and EmployeeVO and
 * validates user inputs.
 * 
 * @author Niraimathi S
 * @version 1.0 12-11-2021
 */
public class EmployeeServiceImplementation implements EmployeeService{
    private EmployeeDAOImplementation dao = new EmployeeDAOImplementation();

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
     * Creates a new employee. 
     *
     * @param employeeid-employeeid
     * @param employeeVO-The VO object to store the created employee.
     * @return boolean-true if employee created, else false.
     */
    public EmployeeVO createEmployee(AddressDTO addressDTO) { 
        Address address = Mapper.AddressDTOToAddress(addressDTO);
        EmployeeVO employeeVO 
                = Mapper.EmployeeToEmployeeDTO(dao.createEmployee(address));
        return employeeVO;
    }

    /**
     * Checks if a employeeId exists or not.
     *
     * @param employeeid-employeeid to check if a record already exist.
     * @return boolean-true if given employeeId exist, else false.
     */
    public boolean isEmployeeExist(int employeeId) {
        return(null != dao.getEmployeeById(employeeId));
    }

    /**
     * Checks if employees is empty.
     *
     * @return boolean-true if empty, else false.
     */
    public boolean isRecordsEmpty() {
        List<Employee> employees = dao.getAllEmployees();
        return employees.isEmpty();
    }

    /**
     * Checks if a mobileNumber already exists or not.
     *
     * @param mobileNumber-the number to be checked for duplicate.
     * @return boolean-true if given mobileNumber exist, else false.
     */
    public boolean checkDuplicateMobileNumber(long mobileNumber) {
        return (null != dao.getEmployeeByMobileNumber(mobileNumber));
    }

    /**
     * Checks if a email already exists or not.
     *
     * @param email- email to be checked for duplicate.
     * @return boolean-true if given email exist, else false.
     */
    public boolean checkDuplicateEmail(String email) {
        return (null != dao.getEmployeeByEmail(email));
    }

    /**
     * Deletes all employees. 
     *
     */
    public boolean deleteAllEmployee() {
        return dao.deleteAllEmployee();
    }

    /**
     * Deletes one employee. 
     *
     * @param employeeid-The employeeId of the employee to be deleted.
     */
    public boolean deleteOneEmployee(int employeeId) {
        return dao.deleteOneEmployee(employeeId);
    }

    /**
     * Deletes one address of an employee.
     *
     * @param addressId-The addressId of the address to be deleted.
     * @return boolean-true if given address exist, else false.
     */
    public boolean deleteAddress(int addressId) {
        return dao.deleteAddress(addressId);
    }

    /**
     * Updates all fields of one employee. 
     *
     * @param employeeid-employeeid to update all fields
     * @param AddressDTO-AddressDTO object containing updated
     *                   values get from user
     * @return boolean-true if employee updated, else false.
     */
    public boolean updateAllFields(AddressDTO addressDTO) { 
        Address address = Mapper.AddressDTOToAddress(addressDTO);
        EmployeeVO employeeVO 
                = Mapper.EmployeeToEmployeeDTO(dao.updateEmployee(address));
        return (null != employeeVO);
    }

    /**
     * Gets one employee by Id to the user.
     *
     * @param employeeid-employeeid to view.
     * @return employeeVO-employee get by Id.
     */
    public EmployeeVO getEmployeeById(int employeeId) {
        Employee employee = dao.getEmployeeById(employeeId);
        return (Mapper.EmployeeToEmployeeDTO(employee));
    }

    /**
     * Gets all addresses of an employee.
     *
     * @param employeeId-The employeeId to get all address.
     * @return List<AddressDTO>-List of all addresses of a single employee.
     */
    public List<AddressDTO> getAddressById(int employeeId) {
        List<AddressDTO> addressDTO = new ArrayList<>();
        for (Address address : dao.getAddressById(employeeId)) {
            addressDTO.add(Mapper.AddressToAddressDTO(address));
        }
        return addressDTO;
    }

    /**
     * Displays all employees to the user.
     *
     * @return List<employeeVO>- view object list containing all employees.
     */
    public List<EmployeeVO> viewAllEmployee() {
        List<Employee> employees = dao.getAllEmployees();
        List<EmployeeVO> employeeDetails = new ArrayList<>();
        for (Employee employee : employees) {
            employeeDetails.add(Mapper.EmployeeToEmployeeDTO(employee));
        }
        return employeeDetails;
    }
}
