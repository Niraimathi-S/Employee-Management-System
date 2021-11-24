/* 
 * Copyrights (c) Ideas2It
 */

package com.ideas2it.employeemanagement.model;

import com.ideas2it.employeemanagement.model.Employee;

/**
 * Address is a POJO class with getter & setter methods 
 *
 * @author 	Niraimathi S
 * @version 	1.0
 */
public class Address {
    private int addressId;
    private String doorNumber;
    private String street;
    private String city;
    private String state;
    private String country;
    private int pinCode;
    private Employee employee;

    public Address() {
    }

    /**
     * Constructor for Address class to initialize values.
     */
    public Address(String doorNumber, String street, String city, 
                   String state, String country, int pinCode,
                   Employee employee) {
        this.doorNumber = doorNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
        this.employee = employee;
    }

    public Address(int addressId, String doorNumber, String street, String city, 
                      String state, String country, int pinCode) {
        this.addressId = addressId;
        this.doorNumber = doorNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
    }

    /**
     * Method to get Employee id
     *
     * @return addressId-addressId
     */
    public int getAddressId() {
        return addressId;
    }

    /**
     * Method to set addressId
     *
     * @param addressId-addressId
     */
    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    /**
     * Method to get doorNumber.
     *
     * @return doorNumber-doorNumber
     */
    public String getDoorNumber() {
        return doorNumber;
    }

    /**
     * Method to set doorNumber.
     *
     * @param doorNumber-doorNumber
     */
    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }

    /**
     * Method to get street
     *
     * @return street-street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Method to set street
     *
     * @param street-street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Method to get city.
     *
     * @return city-city
     */
    public String getCity() {
        return city;
    }

    /**
     * Method to set city.
     *
     * @param city-city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Method to get state
     *
     * @return state-state
     */
    public String getState() {
        return state;
    }

    /**
     * Method to set state.
     *
     * @param contactName-State
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Method to get country
     *
     * @param salary	country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Method to set country
     *
     * @param salary	country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Method to get pincode
     *
     * @return pinCode-pinCode
     */
    public int getPinCode() {
        return pinCode;
    }

    /**
     * Method to set pincode.
     *
     * @param pinCode-pinCode
     */
    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    /**
     * Method to get Employee.
     *
     * @return employee-Employee object
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Method to set Employee.
     *
     * @param employee	Employee object.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String toString() {
        StringBuilder address = new StringBuilder();
        address.append("\nDoor Number\t:").append(doorNumber)
                .append("\nStreet\t\t:").append(street)
                .append("\nCity/District\t:").append(city)
                .append("\nState\t\t:").append(state)
                .append("\nCountry\t\t:").append(country)
                .append("\nPincode\t\t:").append(pinCode);
        return address.toString();
    }
}
