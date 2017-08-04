package com.company;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static BankAccountDB bankAccountDB = new BankAccountDB();


    public static void main(String[] args) {



        boolean quit = false;

        while(!quit){

            mainMenu();
            System.out.println("Please select an option \n");
            int choice = scanner.nextInt();

            switch(choice){

                case 1:
                    bankAccountDB.createBankAccountMenu();
                    break;
            }


        }


        //for terminating the database on shut down..
       // bankAccountDB.shutdownSystem();

    }

    private static void mainMenu(){

        System.out.println("Welcome to the BankAccount Database\n");
        System.out.println("========================================\n");

        System.out.println("1 - Create BankAccount Database \n" +
                "2 - Insert Into Database \n" +
                "3 - Update Database \n" +
                "4 - Delete from Database \n" +
                "5 - Select Information from\n" +
                "6 - Exit Application \n");

    }
}
