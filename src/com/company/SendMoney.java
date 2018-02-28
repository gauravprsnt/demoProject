package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SendMoney {
    int availableBalance;
    Scanner scanner = new Scanner(System.in);


    public void sendMoney(int balance, long phoneNo) {

        System.out.println("Enter Esewa Id::");
        Long id = scanner.nextLong();
        System.out.println("Enter Amount");
        int amount = scanner.nextInt();
        if (balance > amount) {
            balance = balance - amount;
            try {

                Class.forName(Constants.ClassName);
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Esewa", "root", "root");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select Balance from Login where Phone_No=" + id + "");
                while (resultSet.next()) {
                    availableBalance = resultSet.getInt("Balance");

                }

                availableBalance = availableBalance + amount;

                statement.executeUpdate("UPDATE Login set Balance=" + availableBalance + " where Phone_No=" + id + "");

                statement.executeUpdate("UPDATE Login set Balance=" + balance + " where Phone_No=" + phoneNo + "");
                System.out.println("Money Transferred...");

                System.out.println("New Balance::" + balance);


            } catch (Exception e) {
                System.out.println(e);
            }

        } else {
            System.out.println("You do not have sufficient balance");
            sendMoney(balance, phoneNo);
        }


    }
}
