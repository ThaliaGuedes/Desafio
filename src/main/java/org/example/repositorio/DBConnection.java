package org.example.repositorio;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
   public static Connection getConnection() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName("desafioJava");
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(5432);
        dataSource.setUser("postgres");
        dataSource.setPassword("12345");

       try {
           return dataSource.getConnection();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }

}
