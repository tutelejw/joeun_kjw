// src/main/java/com/academy/util/DBUtil.java
package com.academy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class DBUtil {
    private static final Logger logger = Logger.getLogger(DBUtil.class);
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String DB_USER = "test";
    private static final String DB_PASS = "test";

    static {
        try {
            Class.forName(DB_DRIVER);
            System.out.println("Oracle JDBC Driver loaded.");
            logger.info("Oracle JDBC Driver loaded.");
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found: ");        	
            logger.error("Oracle JDBC Driver not found: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        System.out.println("Establishing a new DB connection...");
    	logger.info("Establishing a new DB connection...");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}