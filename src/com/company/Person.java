package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by ryanfox on 8/1/17.
 */
public class Person {

    private String fname;
    private String lname;
    private String phoneNumber;
    private Address address;
    private ArrayList<BankAccount> bankAccounts;

    public Person(){


        this.fname = "default fname";
        this.lname = "default lanme";
        this.phoneNumber = "000-000-0000";
        this.address = new Address();
        this.bankAccounts = new ArrayList<BankAccount>();

    }


    public Person(String fname, String lname,String phoneNumber,int houseNumber,String streetName,String zip,String state){

        this.fname = fname;
        this.lname = lname;
        this.phoneNumber = phoneNumber;
        this.address = new Address(houseNumber,streetName,zip,state);
        this.bankAccounts = new ArrayList<BankAccount>();
    }


    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public ArrayList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address +
                ", bankAccounts=" + bankAccounts +
                '}';
    }

    public Person newPersonMenu(Scanner scanner){

        System.out.println("Please Enter in a first name : " );
        this.fname = scanner.next();
        System.out.println("Please enter in a last name : ");
        this.lname = scanner.next();
        System.out.println("Please enter in you're phone number(000-000-0000) : ");
        this.phoneNumber = scanner.next();
        this.address.addressMenu(scanner);

        return this;

    }
}
