/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Establish database connection. 
 * 
 * @author Niraimathi S
 * @version 1.0 12-11-2021
 */
public class DatabaseConnection {
    private static DatabaseConnection databaseConnection;
    private DatabaseConnection() { }
    /*public static DatabaseConnection getInstance() {
        if ((null != databaseConnection) || connection.isClosed()) {
            databaseConnection = new  DatabaseConnection();
        }
        return databaseConnection;
    }*/
    private static Connection connection = null;

    /*
     * Gets connection.
     */
    public static Connection getConnection() {
        try {
            if ((null == connection) || connection.isClosed()) {
                connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/ems",
                    "root", "Test123@");
            }
        } catch (SQLException e) {
            System.out.println("Connection not established!");
        }
        return connection;
    }

    /*
     * Closes the connection.
     *
     * @param connection, connection which is to be closed.
     */    
    public static void close(Connection connection) {
        try {
            if (null != connection) {
                connection.close();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /*
     * Closes the prepared statement.
     *
     * @param preparedStatement, Prepared statement which is to be closed.
     */
    public static void close(PreparedStatement preparedStatement) {
        try {
            if (null != preparedStatement) {
                preparedStatement.close();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    
    /*
     * Closes the result set.
     *
     * @param resultSet, Result set which is to be closed.
     */    
    public static void close(ResultSet resultSet) {
        try {
            if (null != resultSet) {
                resultSet.close();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
