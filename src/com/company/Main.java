package com.company;


import java.util.Scanner;

public class Main {

    public void display()
    {
        Scanner scanner = new Scanner(System.in);
        LogIn logIn = new LogIn();
        SignUp signUp = new SignUp();

        System.out.println("Welcome To Esewa!!");
        System.out.println("Please Choose your Choice:");
        System.out.println("Press 1 for Login");
        System.out.println("Press 2 for Sign Up");
        System.out.println("Press 3 for Exit");
        int a = scanner.nextInt();
        switch (a) {
            case 1:
                logIn.login();
                break;
            case 2:
                signUp.signup();
                break;
            case 3:
                break;
            default:
                System.out.println("Please enter 1 or 2");

        }

    }

    public static void main(String[] args) {

    Main main=new Main();
    main.display();
    }


}



