package Test;

import data.Account;
import data.PersistenceHandler;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class LoginTest {
    PersistenceHandler ps;
    ArrayList<Account> accountArrayList;

    @org.junit.Before
    public void setUp() throws Exception {
        ps = PersistenceHandler.getInstance();
        accountArrayList = ps.loadAccounts();
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void checkIfLoginCorrect() {
        boolean loggedin = false;
        for (Account e : accountArrayList)
        {
            if (e.getUsername().equals("1") && e.getPassword().equals("2"))
            {
                loggedin = true;
                break;
            }
        }
        assertTrue(loggedin);
    }

    @org.junit.Ignore
    public void getAccountsList() {
    }

    @org.junit.Ignore
    public void setAccountsList() {
    }
}