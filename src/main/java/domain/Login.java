package domain;

import data.Account;
import data.PersistenceHandler;

import java.util.ArrayList;

public class Login {

    //Arraylist to hold the accounts
    private ArrayList<Account> accountsList;
    PersistenceHandler persistenceHandler = PersistenceHandler.getInstance();


    public Login()
    {
        //Load in the accounts to the arraylist
        setAccountsList(persistenceHandler.loadAccounts());
    }

    //Method that the controller is gonna call to check if login info is correct.
    public boolean checkIfLoginCorrect(String username, String password)
    {
        boolean loggedin = false;
        for(Account e : accountsList)
        {
            if(e.getUsername().equals(username) && e.getPassword().equals(password))
            {
                loggedin = true;
                break;
            }
            else
            {
                loggedin = false;
                break;
            }
        }
        return loggedin;
    }

    public ArrayList<Account> getAccountsList() {
        return accountsList;
    }

    public void setAccountsList(ArrayList<Account> accountsList) {
        this.accountsList = accountsList;
    }
}
