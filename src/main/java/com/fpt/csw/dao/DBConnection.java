package com.fpt.csw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** JDBC connection helper for the XAMPP MySQL database. */
public class DBConnection {
    private static final String URL =
            "jdbc:mysql://localhost:3306/fpt_employee_db"
                    + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";   // XAMPP default is empty

    private DBConnection() { }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}