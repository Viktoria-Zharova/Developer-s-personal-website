package org.example.repository.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnectionProvider {
    public static Connection getConnection(){
        try{
            Class.forName(PostgresDBConnectionDataHolder.DRIVER);

        } catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        try {
            return DriverManager.getConnection(PostgresDBConnectionDataHolder.URL,
                    PostgresDBConnectionDataHolder.USERNAME, PostgresDBConnectionDataHolder.PASSWORD);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}