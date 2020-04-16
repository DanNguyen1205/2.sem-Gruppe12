package presentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import domain.Account;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import domain.Login;

public class PrimaryController{
    Login login = new Login();
    boolean loggedIn;

    @FXML
    TextField userID, passwordID;
    @FXML
    Button signInBtn;


    @FXML
    private void login(){
        //Use login object to make accounts
        login.makeAccounts();

        String inputUserID = this.userID.getText();
        String inputPasswordID = this.passwordID.getText();

        //Loop for checking the input from the GUI to the accounts in the login class. 
        for(Account e : login.getAccountsList())
        {
            if(e.getUsername().equalsIgnoreCase(inputUserID) && e.getPassword().equalsIgnoreCase(inputPasswordID))
            {
                loggedIn = true;
                break;
            }
            else
            {
                loggedIn = false;
            }
        }
        System.out.println("logged in is " + loggedIn);
    }
}
