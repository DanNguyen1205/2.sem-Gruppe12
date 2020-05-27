package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;


public class PersistenceHandler {
    String SQL_INSERT_PRODUCTION = "INSERT INTO production (name, release_Date, producer) VALUES (?,?,?)";
    String SQL_INSERT_PERSON = "INSERT INTO person (name, email) VALUES (?,?)";
    String SQL_INSERT_PERSONTOCREDIT = "INSERT INTO persontocredit (role, productions_fk, persons_fk) VALUES (?,?,?)";



    //Arrays to hold the data from the database
    ArrayList<Account> accountArrayList = new ArrayList<>();
    ArrayList<Person> personArrayList = new ArrayList<>();
    ArrayList<Program> programArrayList = new ArrayList<>();
    ArrayList<PersonToCredit> personToCreditsArrayList = new ArrayList<>();


    //Singleton stuff
    private PersistenceHandler() {
        Dbconfig db = new Dbconfig();
    }
    private static PersistenceHandler singletonInstance = new PersistenceHandler();
    //Method to get the singleton object.
    public static PersistenceHandler getInstance()
    {
        return singletonInstance;
    }


    //Method for adding a production and its credits
    public void addToDatabase(Program program) throws SQLException
    {
        //Foreign keys used for when inserting persontocredit table
        long production_fk;
        long persons_fk;

        try (PreparedStatement insertProdStatement = Dbconfig.connection.prepareStatement(SQL_INSERT_PRODUCTION,
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
                    PreparedStatement insertPersonStatement = Dbconfig.connection.prepareStatement(
                            SQL_INSERT_PERSON,
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
                                System.out.println("The insert primary key for " + key.getName()
                                        + " is: " + generatedKeys.getLong(1));
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
                        insertPersonToCreditMethod(key.getRole(), production_fk,
                                CheckForDuplicateSetqueryForMatch.getLong("id"));
                    }
                }
            }
    }

    public void insertPersonToCreditMethod(String role, long produtions_id, long persons_id) {
        try (PreparedStatement insertPersonToCreditStatement = Dbconfig.connection.prepareStatement(SQL_INSERT_PERSONTOCREDIT);) {
            insertPersonToCreditStatement.setString(1, role);
            insertPersonToCreditStatement.setLong(2, produtions_id);
            insertPersonToCreditStatement.setLong(3, persons_id);
            insertPersonToCreditStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Method to check for duplicate persons
    public ResultSet queryForMatch(String email)
    {
        ResultSet resultSet = null;
        try {
            PreparedStatement queryformatch = Dbconfig.connection.prepareStatement(
                    "SELECT * FROM person WHERE email = ?");
            queryformatch.setString(1, email);
            resultSet = queryformatch.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    //---------------------------------------------------------------------------------------------
    //Methods for loading in the data from the database.

    public ArrayList<Program> loadPrograms()
    {
        programArrayList.clear();
        try(PreparedStatement queryStatement = Dbconfig.connection.prepareStatement("SELECT * FROM production");) {
            ResultSet resultSet = queryStatement.executeQuery();

            while(resultSet.next())
            {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String release_date = resultSet.getString("release_date");
                String producer = resultSet.getString("producer");

                Program newProduction = new Program(name, release_date, producer);
                newProduction.setId(id);
                Credit newCredit = new Credit();
                newProduction.setCredits(newCredit);

                programArrayList.add(newProduction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programArrayList;
    }

    public ArrayList<Person> loadPersons() {
        personArrayList.clear();
        try (PreparedStatement queryStatement = Dbconfig.connection.prepareStatement("SELECT * FROM person");) {
            ResultSet resultSet = queryStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                Person newPerson = new Person(name, email);
                newPerson.setId(id);

                personArrayList.add(newPerson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personArrayList;
    }

    public ArrayList<PersonToCredit> loadPersonToCredit()
    {
        personToCreditsArrayList.clear();
        try(PreparedStatement queryStatement = Dbconfig.connection.prepareStatement("SELECT * FROM persontocredit");) {
            ResultSet resultSet = queryStatement.executeQuery();

            while(resultSet.next())
            {
                long id = resultSet.getLong("id");
                String role = resultSet.getString("role");
                long productions_fk = resultSet.getLong("productions_fk");
                long persons_fk = resultSet.getLong("persons_fk");

                PersonToCredit newPersonToCredit = new PersonToCredit(id, role, productions_fk, persons_fk);

                personToCreditsArrayList.add(newPersonToCredit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personToCreditsArrayList;
    }

    public ArrayList<Account> loadAccounts()
    {
        accountArrayList.clear();
        try (PreparedStatement queryStatement = Dbconfig.connection.prepareStatement("SELECT * FROM account");) {
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



}
