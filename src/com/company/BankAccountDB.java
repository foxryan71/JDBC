package com.company;

import java.sql.*;
import java.util.Scanner;

/**
 * Created by ryanfox on 8/1/17.
 */
public class BankAccountDB {
    private Connection conn;
    private PreparedStatement preparedStatement;
    private CallableStatement callableStatement;
    private Statement statement;
    private String createBankAccountTable;
    private String createPersonTable;
    private String findPersonQuery;
    private String insertPersonQuery;
    private Scanner scanner = new Scanner(System.in);




    public BankAccountDB(){

        this.conn = new Connection();
        //sets the table query strings.
        setTableQueries();
        //creates the table schema.
        createScheme();
        //set Preparedstatements
        generatePreparedStatements();

    }
    //On inital start up will load the BankAccountDB schema.
    private void createScheme(){

        try {
            this.statement = conn.getConn().createStatement();
            if(!statement.execute(this.createBankAccountTable)){

                System.out.println("Successfully set up table for bank Account! \n");

            }

            if(!statement.execute(this.createPersonTable)){

                System.out.println("Successfully created table for Person\n ");

            }

        }catch(SQLException e){

            System.out.println("Sorry could not create database Schema. " + e.getMessage());
        }finally {

           try {

               this.statement.close();
           }catch (SQLException e){
               System.out.println("Could not close statement.");
           }
        }


    }

    private void setTableQueries(){

        //create bankaccount table query.
        this.createBankAccountTable =
                "CREATE TABLE IF NOT EXISTS BankAccount ( " +
                "id INTEGER PRIMARY KEY, " +
                "ownerid INTEGER not null, " +
                "AccountType TEXT not null, " +
                "Balance Double not null, " +
                        "FOREIGN KEY (ownerid) REFERENCES Person(id) " +
                ");";


        //create person table query string
        this.createPersonTable =
                "CREATE TABLE IF NOT EXISTS Person ( " +
                        "id INTEGER PRIMARY KEY, " +
                        "firstName TEXT not null, " +
                        "lastName TEXT not null, " +
                        "phoneNumber TEXT not null, " +
                        "houseNumber INTEGER not null, " +
                        "streetName TEXT not null, " +
                        "zip TEXT not null, " +
                        "state TEXT not null " +
                        ");";
    }


    public Connection getConn() {
        return conn;
    }
    // Will close everything and terminate the database when the console application is terminated.
    //Drops all tables and closes all connections
    public void shutdownSystem(){

        String dropPersonTable = "DROP TABLE IF EXISTS 'Person'";
        String dropBankAccountTable = "DROP TABLE IF EXISTS 'BankAccount'";

        try {

            this.statement.executeUpdate(dropPersonTable);
            this.statement.executeUpdate(dropBankAccountTable);
            System.out.println("Dropped all tables in database \n");


        }catch(SQLException e){

            System.out.println("Something went wrong with adding to the batch....");
        }

        finally {

            try {
                this.conn.getConn().close();


            }catch(SQLException e){
                System.out.println("Something went wrong with closing connections\n");
            }
        }

    }

/*
    //Method to create a new bank account into the database.
    private boolean createBankAccount(Person person, AccountType accountType){




    }
*/

    //menu to create a new BankAccount
    public void createBankAccountMenu(){

       int choice = 0 ;
       AccountType accountType = null;
       Person person = null;
        boolean quit = false;

        while(!quit) {
            System.out.println("New Bank Account Menu\n" +
                    "=====================================\n" +
                    "1 - Create new Bank Account \n" +
                    "2 - Exit \n");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                  while(choice != 3) {
                      System.out.println("1 - Add new Person \n" +
                              "2 - Add existing person to Bank Account \n" +
                              "3 - Exit \n");
                            choice = scanner.nextInt();
                            switch(choice){

                                case 1 :
                                    //create new Person object
                                    person = new Person();
                                   //do a person check to see if they exist already in database.
                                    person = person.newPersonMenu(this.scanner);
                                    //if true person exist...
                                    if(checkPersonExsistence(person)){
                                        System.out.println("Person already exist.\n");
                                        break;
                                        //person doesnt exist lets register them.
                                    }else{

                                        if(insertIntoPersonTable(person)){
                                            System.exit(0);
                                        }else{
                                            break;
                                        }
                                    }


                                    break;
                                case 2 :
                                    break;
                                case 3:
                                    break;
                                default:
                                        System.out.println("Invalid Option... \n");
                                    break;

                            }//end of inner switch..
                  }//end of inner while loop

                    case 2 :
                        quit = true;
                        break;
                    default:
                        System.out.println("Invlaid Option ...\n");

            }//end of outter switch

        }//end of outer while loop.
    }//end of create bank account menu

    //checks person inside database..
    private boolean checkPersonExsistence(Person person){

        try {
            //Prepared statement
            this.preparedStatement = this.conn.getConn().prepareStatement(this.findPersonQuery);

            //setting prepared statement two arguments.
            this.preparedStatement.setString(1,person.getFname());
            this.preparedStatement.setString(2,person.getLname());

            //get result set
            ResultSet resultSet = this.preparedStatement.executeQuery();
            //check if result set is empty or contains a person.
            if(resultSet.wasNull()){

                this.preparedStatement.close(); //may change...
                //return false because the person did not exist.
                return false;
            }else{
                return true;
            }
        }catch (SQLException e){

            System.out.println("Something went wrong with the query \n");
        }

        return false;

    }




    //Method to generate queries that are used throughout the program...
    private void generatePreparedStatements(){
        // Query to select a person based on first name and last name
        this.findPersonQuery = "SELECT * FROM PERSON WHERE fname = ? and lname = ?";


        //Query to insert into Person Table...

        this.insertPersonQuery = "INSERT INTO PERSON('firstName','lastName','phoneNumber','houseNumber','streetName','zip','state') " +
                "VALUES (?,?,?,?,?,?,?)";

    }

    private boolean insertIntoPersonTable(Person person){
        try {
            this.preparedStatement = this.conn.getConn().prepareStatement(this.insertPersonQuery);
            //setting prepared statement  seven arguments.
            this.preparedStatement.setString(1,person.getFname());
            this.preparedStatement.setString(2,person.getLname());
            this.preparedStatement.setString(3,person.getPhoneNumber());
            this.preparedStatement.setInt(4,person.getAddress().getHouseNumber());
            this.preparedStatement.setString(5, person.getAddress().getStreetName());
            this.preparedStatement.setString(6,person.getAddress().getZip());
            this.preparedStatement.setString(7,person.getAddress().getState());

            this.preparedStatement.execute();

            this.preparedStatement.close();
            System.out.println("Successfully entered in person : " +
                    "\n" +
                    person.toString() );
            return true;
        }catch(SQLException e){

           System.out.println("Something went wrong with the SQL Insert Person query...\n");
           return false;
        }

    }
}//end of class
