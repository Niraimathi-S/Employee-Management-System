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
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public EmployeeManagementException ( ) {
        super();
    }
	
	public EmployeeManagementException (String errorMessage) {
        super(ConstantErrors.getError().get(errorMessage));
    }
    
    public EmployeeManagementException ( String errorMessage, Throwable exception) {
        super(ConstantErrors.getError().get(errorMessage), exception);
    }
    
    public EmployeeManagementException (Throwable exception) {
        super(exception);
    }
}