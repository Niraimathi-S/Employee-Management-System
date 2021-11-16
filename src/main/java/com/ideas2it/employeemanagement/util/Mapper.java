/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.util;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.AddressDTO;
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
        List<AddressDTO> addressesDTO = new ArrayList<AddressDTO>();
        if (null != employee.getaddresses()) {
            for(Address address:employee.getaddresses()) {
                addressesDTO.add(AddressToAddressDTO(address));
            }
        }
        employeeVO.setaddressesDTO(addressesDTO);
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
        List<Address> addresses = new ArrayList<Address>();

        if (null != employeeVO.getaddressesDTO()) {
            for(AddressDTO addressDTO:employeeVO.getaddressesDTO()) {
                addresses.add(AddressDTOToAddress(addressDTO));
            }
        }
        employee.setaddresses(addresses);
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
        EmployeeVO employeeVO;
        address.setAddressId(addressDTO.getAddressId());
        address.setDoorNumber(addressDTO.getDoorNumber());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setCountry(addressDTO.getCountry());
        address.setPinCode(addressDTO.getPinCode());
        employeeVO = addressDTO.getEmployeeVO();
        address.setEmployeeId(addressDTO.getEmployeeId());
        if (null != employeeVO) {
            address.setEmployee(EmployeeDTOToEmployee(employeeVO));
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
        addressDTO.setEmployeeId(address.getEmployeeId());
        if (null != employee) {
            addressDTO.setEmployeeVO(EmployeeToEmployeeDTO(employee));
        }

        return addressDTO;
    }
}
