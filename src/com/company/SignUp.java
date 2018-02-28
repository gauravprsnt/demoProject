package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class SignUp {

    Scanner scanner = new Scanner(System.in);
    LogIn logIn=new LogIn();

    public void signup() {
        System.out.println("Sign Up");
        System.out.println("Enter your Name::");
        String name = scanner.next();
        System.out.println("Enter your Phone Number::");
        Long phone = scanner.nextLong();
        System.out.println("Enter your Password");
        String password = scanner.next();
        System.out.println("Confirm Password");
        String confirmPassword = scanner.next();
        if (password.equals(confirmPassword)) {

            try {
                Class.forName(Constants.ClassName);
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Esewa", "root", "root");
                Statement statement = connection.createStatement();
                statement.executeUpdate("insert into Login values ('" + name + "'," + phone + ",'" + confirmPassword + "',0)");
                System.out.println("Your details are inserted..");
                System.out.println("Please Login!!");
                logIn.login();

            } catch (Exception e) {
                System.out.println(e);
            }

        } else {
            System.out.println("Error!! Please confirm your password!!");
            signup();
        }
    }
}
