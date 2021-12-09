/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * 
 * @author Niraimathi S
 * @version 1.0
 */
public class EmployeeManagementLogger {

    public static Logger logger
            = LogManager.getLogger(EmployeeManagementLogger.class.getName());

    public void fatal(String message) {
       	logger.fatal(message);
    }

    public void error(String message) {
       	logger.error(message);
    }

    public void warn(String message) {
       	logger.warn(message);
    }

    public void info(String message) {
       	logger.info(message);
    }

    public void debug(String message) {
    	logger.debug(message);
    }
}
