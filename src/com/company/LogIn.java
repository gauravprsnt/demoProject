package com.company;

import java.sql.*;
import java.util.Scanner;

public class LogIn {

    Scanner scanner = new Scanner(System.in);
    Main main = new Main();
    TopUp topup = new TopUp();
    SendMoney sendmoney = new SendMoney();
    int balance;
    Long phoneNo, phone;
    String password;
    String name;
    String pass;

    public void login() {
        System.out.println("Login!!");
        System.out.println("Enter your Phone No::");
        phone = scanner.nextLong();
        System.out.println("Enter your password::");
        password = scanner.next();

        try {

            Class.forName(Constants.ClassName);
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Esewa", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select Name,Password,Phone_No, Balance from Login where Phone_No=" + phone + " and Password='" + password + "'");

            while (resultSet.next()) {
                name = resultSet.getString("Name");
                pass = resultSet.getString("Password");
                phoneNo = resultSet.getLong("Phone_No");
                balance = resultSet.getInt("Balance");

            }
            if (phone.equals(phoneNo) && password.equals(pass)) {
                System.out.println("Your Details::");
                System.out.println("Name::" + name);
                System.out.println("Phone No::" + phoneNo);
                System.out.println("Balance::" + balance);
                System.out.println("");
                System.out.println("Press 1 for TopUp");
                System.out.println("Press 2 for Send Money");
                System.out.println("Press 3 for LogOut");
                int a = scanner.nextInt();
                switch (a) {
                    case 1:
                        topup.topUp(phoneNo, balance);
                        break;
                    case 2:
                        sendmoney.sendMoney(balance, phoneNo);
                        break;
                    case 3:
                        main.display();
                        break;

                    default:
                        System.out.println("Please enter a right choice");

                }


            } else {
                System.out.println("Phone No and Password did not Match");
                login();
            }


        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
