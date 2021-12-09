/*
 * copyright (c) ideas2it
 */
package com.ideas2it.employeemanagement.util;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

/**
 * Stores all the constant error codes and its values.
 *
 * @version 1.0 
 * @author  Niraimathi S
 */
public class ConstantErrors { 

    public static final Map<String, String> ERROR = getError();
    public static final String ERROR_CODE_01 = "ERROR_CODE_01";
    public static final String ERROR_CODE_02 = "ERROR_CODE_02";
    public static final String ERROR_CODE_03 = "ERROR_CODE_03";
    public static final String ERROR_CODE_04 = "ERROR_CODE_04";
    public static final String ERROR_CODE_05 = "ERROR_CODE_05";
    public static final String ERROR_CODE_06 = "ERROR_CODE_06";
    public static final String ERROR_CODE_07 = "ERROR_CODE_07";
    public static final String ERROR_CODE_08 = "ERROR_CODE_08";
    public static final String ERROR_CODE_09 = "ERROR_CODE_09";
    public static final String ERROR_CODE_10 = "ERROR_CODE_10";
    public static final String ERROR_CODE_11 = "ERROR_CODE_11";
    public static final String ERROR_CODE_12 = "ERROR_CODE_12";
    public static final String ERROR_CODE_13 = "ERROR_CODE_13";
    public static final String ERROR_CODE_14 = "ERROR_CODE_14";

    /**
     * Gets list of error message and its coressponding code.
     *
     * @return  Map<String, String>  group containing pair of error code and its
     *                               corresponding message 
     */
    public static Map<String, String> getError() {
        Map<String, String> collectionOfErrorCodes = new HashMap<String, String>();
        collectionOfErrorCodes.put(ERROR_CODE_01, "Exception in Employee Creation");
        collectionOfErrorCodes.put(ERROR_CODE_02, "Exception in Employee Updation");
        collectionOfErrorCodes.put(ERROR_CODE_03, "Exception occured while fetching single employee.");
        collectionOfErrorCodes.put(ERROR_CODE_04, "Exception occured while getting an employee by email");
        collectionOfErrorCodes.put(ERROR_CODE_05, "Exception occured while getting an employee by mobile number");
        collectionOfErrorCodes.put(ERROR_CODE_06, "Exception occured during fetching all employee");
        collectionOfErrorCodes.put(ERROR_CODE_07, "Exception in Employee Delete all operation");
        collectionOfErrorCodes.put(ERROR_CODE_08, "Exception occured during deletion of single employee");
        collectionOfErrorCodes.put(ERROR_CODE_09, "Exception in Project Creation.");
        collectionOfErrorCodes.put(ERROR_CODE_10, "Exception in Project Updation.");
        collectionOfErrorCodes.put(ERROR_CODE_11, "Exception in Single Project fetching");
        collectionOfErrorCodes.put(ERROR_CODE_12, "Exception in all Project fetching");
        collectionOfErrorCodes.put(ERROR_CODE_13, "Exception in Single Project Deletion");
        collectionOfErrorCodes.put(ERROR_CODE_14, "Exception in all Project Deletion");
        return Collections.unmodifiableMap(collectionOfErrorCodes);
    }
}
