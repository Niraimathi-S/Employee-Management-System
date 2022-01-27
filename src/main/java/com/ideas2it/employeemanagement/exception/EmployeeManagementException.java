/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.exception;

import java.lang.Exception;
import com.ideas2it.employeemanagement.util.ConstantErrors;

/**
 * Handles the Custom exception occurs in the Employee Management System.
 *
 * @author Niraimathi S
 * @version 1.0
 */
public class EmployeeManagementException extends Exception {
    public EmployeeManagementException (String errorMessage) {
        super(ConstantErrors.getError().get(errorMessage));
    }
}