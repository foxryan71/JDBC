package com.company;
import javax.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ryanfox on 7/31/17.
 */
public class Connection {

    private final String driver = "jdbc:sqlite:" ;
    private final String url = "/Users/ryanfox/Documents/Java_Practice/JDBC/";
    private final String database = "BankAccountDB";

    private java.sql.Connection conn;

    public Connection(){



        try {
           this.conn = DriverManager.getConnection(driver + url + database);
           System.out.println("Connected to the database! \n");
        }catch (SQLException e){
            System.out.println("Could not connect to the database \n");
        }
    }

    public java.sql.Connection getConn() {
        return conn;
    }
}
