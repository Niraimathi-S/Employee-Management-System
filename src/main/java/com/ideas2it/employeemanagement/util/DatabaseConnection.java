/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.util;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Establish database connection. 
 * 
 * @author Niraimathi S
 * @version 1.0
 */
public class DatabaseConnection {
    private static DatabaseConnection databaseConnection;
    private DatabaseConnection() { }
    private static SessionFactory factory;

    public static SessionFactory getSessionFactory() {
        try {
            if (null == factory || factory.isClosed()) {
               factory = new Configuration().configure().buildSessionFactory(); 
            } 
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return factory;
    }

    /*
     * Gets session.
     */
    public static Session getSession() {
        return getSessionFactory().openSession();
    }

    /*
     * Closes the session.
     *
     * @param session, session which is to be closed.
     */    
    public static void close(Session session) {
        try {
            if (null != session) {
                session.close();
            }
        } catch (Throwable exception) {
            exception.printStackTrace();
        }
    }
}
