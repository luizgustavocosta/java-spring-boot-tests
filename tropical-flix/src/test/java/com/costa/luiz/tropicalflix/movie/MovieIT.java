//package com.costa.luiz.tropicalflix.movie;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.testcontainers.containers.MySQLContainer;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//@SpringBootTest
//@Testcontainers
//class MovieIT {
//
//    @Test
//    void doStuff() {
//        try (MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest")
//                .withDatabaseName("Tropicalflix")) {
//            mysql.addExposedPort(3306);
//            mysql.start();
//
//            String url = mysql.getJdbcUrl();
//
////            Connection connection = mysql.createConnection("jdbc:mysql://localhost:" + mysql.getMappedPort(3306) + "/Tropicalflix");
//            Connection connection = mysql.createConnection(url);
//
//            Statement stmt = connection.createStatement();
//            ResultSet resultSet = stmt.executeQuery("SELECT 1");
//            int resultSetInt = resultSet.getInt(1);
//            System.out.println(resultSetInt);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}