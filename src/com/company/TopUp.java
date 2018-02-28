package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class TopUp {
    Scanner scanner = new Scanner(System.in);
    public long phoneno;
    public int amount;
    public int availableBalance;

    public void topUp(long phone, int balance) {
        System.out.println("Enter Mobile No:");
        phoneno = scanner.nextLong();
        System.out.println("Enter Amount");
        amount = scanner.nextInt();
        if(balance>amount){
            balance = balance - amount;
            try {

                Class.forName(Constants.ClassName);
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Esewa", "root", "root");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select Balance from TopUpM where Mobile_No=" + phoneno + "");
                while (resultSet.next()) {

                    availableBalance = resultSet.getInt("Balance");
                    availableBalance = availableBalance + amount;
                }
                statement.executeUpdate("Update TopUpM set Balance=" + availableBalance + " where Mobile_No=" + phoneno + "");
                statement.executeUpdate("Update Login set Balance=" + balance + " where Phone_No=" + phone + "");
                System.out.println("Balance Transferred successfully");


            } catch (Exception e) {
                System.out.println(e);
            }
        }else{
            System.out.println("You do not have sufficient balance");
            topUp(phone,balance);
        }



    }
}

