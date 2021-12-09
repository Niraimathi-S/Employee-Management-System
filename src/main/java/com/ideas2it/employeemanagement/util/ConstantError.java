/*
 * copyright (c) ideas2it
 */
package com.ideas2it.employeemanagement.utils;
​
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
​
/**
 * Stores all the constant error codes and its values.
 *
 * @version 1.00 
 * @author  Niraimathi S
 */
public class ConstantErrors { 
​
    public static final Map<String, String> ERROR = getError();
    public static final String ERROR_CODE_001 = "ERROR_CODE_001";
    public static final String ERROR_CODE_002 = "ERROR_CODE_002";
    public static final String ERROR_CODE_003 = "ERROR_CODE_003";
    public static final String ERROR_CODE_004 = "ERROR_CODE_004";
    public static final String ERROR_CODE_005 = "ERROR_CODE_005";
    public static final String ERROR_CODE_006 = "ERROR_CODE_006";
    public static final String ERROR_CODE_007 = "ERROR_CODE_007";
    public static final String ERROR_CODE_008 = "ERROR_CODE_008";
    public static final String ERROR_CODE_009 = "ERROR_CODE_009";
    public static final String ERROR_CODE_010 = "ERROR_CODE_010";
    public static final String ERROR_CODE_011 = "ERROR_CODE_011";
    public static final String ERROR_CODE_012 = "ERROR_CODE_012";
    public static final String ERROR_CODE_013 = "ERROR_CODE_013";
    public static final String ERROR_CODE_014 = "ERROR_CODE_014";
​
    /**
     * Gets list of error message and its coressponding code.
     *
     * @return  Map<String, String>  group containing pair of error code and its
     *                               corresponding message 
     */
    private static Map<String, String> getError() {
        Map<String, String> collectionOfErrorCodes = new HashMap<String, String>();
​
        collectionOfErrorCodes.put(ERROR_CODE_001, "Exception in Employee Creation");
        collectionOfErrorCodes.put(ERROR_CODE_002, "Exception in Employee Updation");
        collectionOfErrorCodes.put(ERROR_CODE_003, "Exception occured while fetching single employee.");
        collectionOfErrorCodes.put(ERROR_CODE_004, "Exception occured while getting an employee by email");
        collectionOfErrorCodes.put(ERROR_CODE_005, "Exception occured while getting an employee by mobile number");
        collectionOfErrorCodes.put(ERROR_CODE_006, "Exception occured during fetching all employee");
        collectionOfErrorCodes.put(ERROR_CODE_007, "Exception in Employee Delete all operation");
        collectionOfErrorCodes.put(ERROR_CODE_008, "Exception occured during deletion of single employee");
        collectionOfErrorCodes.put(ERROR_CODE_009, "Exception in Project Creation.");
        collectionOfErrorCodes.put(ERROR_CODE_010, "Exception in Project Updation.");
        collectionOfErrorCodes.put(ERROR_CODE_011, "Exception in Single Project fetching");
        collectionOfErrorCodes.put(ERROR_CODE_012, "Exception in all Project fetching");
        collectionOfErrorCodes.put(ERROR_CODE_013, "Exception in Single Project Deletion");
        collectionOfErrorCodes.put(ERROR_CODE_014, "Exception in all Project Deletion");
        return Collections.unmodifiableMap(collectionOfErrorCodes);
    }
}
