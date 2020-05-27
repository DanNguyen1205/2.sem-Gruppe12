package data;

import java.sql.*;

//Class file that holds the config for logging in to database with a connection.
public class Dbconfig {
    static Connection connection = null;

    public Dbconfig()
    {
        dbconnect();
    }

    public static void dbconnect() {
        String url = "jdbc:postgresql://balarama.db.elephantsql.com:5432/jgrqgqsa";
        String username = "jgrqgqsa";
        String password = "_JVJoRa4m5orQHcKr6pGP2F9TKWIRWtZ";

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection(url, username, password);

        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}