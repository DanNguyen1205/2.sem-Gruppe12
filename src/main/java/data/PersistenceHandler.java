package data;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PersistenceHandler {
    String SQL_INSERT_PRODUCTION = "INSERT INTO production (name, release_Date, producer) VALUES (?,?,?)";
    String SQL_INSERT_PERSON = "INSERT INTO person (name, email) VALUES (?,?)";
    String SQL_INSERT_PERSONTOCREDIT = "INSERT INTO persontocredit (role, productions_fk, persons_fk) VALUES (?,?,?)";



    //Array to hold the programs from the file
    private ArrayList<Program> programList = null;
    ArrayList<Program> programsArray = new ArrayList<>();
    ArrayList<Account> accountArrayList = new ArrayList<>();

    //Singleton stuff
    private PersistenceHandler() {
        dbconfig db = new dbconfig();
    }
    private static PersistenceHandler singletonInstance = new PersistenceHandler();
    //Method to get the singleton object.
    public static PersistenceHandler getInstance()
    {
        return singletonInstance;
    }


    //Reads the array from the binary file and returns it.
    public ArrayList<Program> loadCredits() {
        programList = new ArrayList<>();


        return programList;
    }



    //Method for adding a production and its credits
    public void addToDatabase(Program program) throws SQLException
    {
        //Foreign keys used for when inserting persontocredit table
        long production_fk;
        long persons_fk;

        try (PreparedStatement insertProdStatement = dbconfig.connection.prepareStatement(SQL_INSERT_PRODUCTION,
                Statement.RETURN_GENERATED_KEYS);
        ) {
            insertProdStatement.setString(1, program.getName());
            insertProdStatement.setString(2, program.getReleaseDate());
            insertProdStatement.setString(3, program.getProducer());

            int affectedRows = insertProdStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            //Block of code to get the insert key for the program
            try (ResultSet generatedKeys = insertProdStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    program.setId(generatedKeys.getLong(1));
                    production_fk = generatedKeys.getLong(1);
                    System.out.println("The insert primary key for " + program.getName() + " is: " + production_fk);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }

            //Insert for persons
            try (
                    PreparedStatement insertPersonStatement = dbconfig.connection.prepareStatement(SQL_INSERT_PERSON,
                            Statement.RETURN_GENERATED_KEYS);
            ) {
                //Loop through the HashMap that contains the persons involved in the program.
                for(Map.Entry<Person, String> entry : program.getCredit().getCreditMap().entrySet())
                {
                    Person key = entry.getKey();
                    String value = entry.getValue();

                    //Check if theresa duplicate in the database if not, then we insert
                    ResultSet CheckForDuplicateSetqueryForMatch = queryForMatch(key.getEmail());
                    if(CheckForDuplicateSetqueryForMatch.next() == false)
                    {
                        insertPersonStatement.setString(1, key.getName());
                        insertPersonStatement.setString(2, key.getEmail());

                        int affectedRows = insertPersonStatement.executeUpdate();

                        if (affectedRows == 0) {
                            throw new SQLException("Creating user failed, no rows affected.");
                        }

                        //Block of code to get the insert id from the person statement
                        try (ResultSet generatedKeys = insertPersonStatement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                persons_fk = generatedKeys.getLong(1);
                                System.out.println("The insert primary key for " + key.getName() + " is: " + generatedKeys.getLong(1));
                            } else {
                                throw new SQLException("Creating user failed, no ID obtained.");
                            }
                        }
                        //Use the insertoperson method
                        insertPersonToCreditMethod(key.getRole(), production_fk, persons_fk);
                    }
                    //If a duplicate does exist we dont add any person to the person table
                    //Instead we insert into persontocredit table with existing id from that person already in the database.
                    else
                    {
                        insertPersonToCreditMethod(key.getRole(), production_fk, CheckForDuplicateSetqueryForMatch.getLong("id"));
                    }
                }
            }
    }

    public void insertPersonToCreditMethod(String role, long produtions_id, long persons_id) {
        try (PreparedStatement insertPersonToCreditStatement = dbconfig.connection.prepareStatement(SQL_INSERT_PERSONTOCREDIT);) {
            insertPersonToCreditStatement.setString(1, role);
            insertPersonToCreditStatement.setLong(2, produtions_id);
            insertPersonToCreditStatement.setLong(3, persons_id);
            insertPersonToCreditStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Method to load in the accounts
    public ArrayList<Account> loadAccounts()
    {
        try (PreparedStatement queryStatement = dbconfig.connection.prepareStatement("SELECT * FROM account");) {
            ResultSet resultSet = queryStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                Account newAccount = new Account(username, password);
                newAccount.setId(id);

                accountArrayList.add(newAccount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountArrayList;
    }

    //Method to check for duplicate persons
    public ResultSet queryForMatch(String cpr)
    {
        ResultSet resultSet = null;
        try {
            PreparedStatement queryformatch = dbconfig.connection.prepareStatement("SELECT * FROM person WHERE email = ?");
            queryformatch.setString(1, cpr);
            resultSet = queryformatch.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }



}
