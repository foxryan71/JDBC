package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by ryanfox on 8/1/17.
 */
public class Address {

    private int houseNumber;
    private String streetName;
    private String zip;
    private String state;

    public Address(){


       this.houseNumber = 0;
       this.streetName = "default streetName";
       this.zip = "00000";
       this.state = "DF";

    }

    public Address(int houseNumber, String streetName, String zip, String state) {
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.zip = zip;
        this.state = state;
    }


    public int getHouseNumber() {
        return houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getZip() {
        return zip;
    }

    public String getState() {
        return state;
    }

    public Address addressMenu(Scanner scanner){

            System.out.println("Please enter in a houseNumber : ");
            this.houseNumber = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Please enter in a a street name : ");
            this.streetName = scanner.nextLine();
            System.out.println("Please enter in your zip code : ");
            this.zip = scanner.next();
            System.out.println("Please enter in your state(NY) : ");
            this.state = scanner.next();
            return this;


    }

    @Override
    public String toString() {
        return "Address{" +
                "houseNumber=" + houseNumber +
                ", streetName='" + streetName + '\'' +
                ", zip='" + zip + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
