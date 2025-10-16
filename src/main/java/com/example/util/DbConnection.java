package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    static{
        try {
            Class <?>x=Class.forName("oracle.jdbc.driver.OracleDriver");
            IO.println("Oracle JDBC Driver Registered!"+x.getName());
        } catch (ClassNotFoundException e) {
            IO.println("ClassNotFoundException"+e.getMessage());
        }
    }
    public static Connection makecon() throws SQLException {
        return  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","c##scott","lion");
    }
}
