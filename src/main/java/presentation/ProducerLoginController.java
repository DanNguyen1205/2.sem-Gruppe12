package presentation;

import domain.Account;
import domain.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ProducerLoginController{
    Login login = new Login();
    boolean loggedIn;

    @FXML
    TextField ProducerID, ProducerKodeID;
    @FXML
    Button logIndProducerBtn;


    @FXML
    private void ProducerLogin(ActionEvent loginClicked)throws IOException {
        //Use login object to make accounts
        login.makeAccounts();

        String inputUserID = this.ProducerID.getText();
        String inputPasswordID = this.ProducerKodeID.getText();

        Parent producerViewParent = FXMLLoader.load(getClass().getResource("producerView.fxml"));
        Scene producerViewScene = new Scene(producerViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) loginClicked.getSource()).getScene().getWindow();

        for(Account e : login.getAccountsList()) {
            if(e.getUsername().equalsIgnoreCase(inputUserID) && e.getPassword().equalsIgnoreCase(inputPasswordID))
            {
                loggedIn = true;
                window.setTitle("Logget ind som producer");
                window.setScene(producerViewScene);
                window.show();
                break;
            }
            else
            {
                loggedIn = false;
            }
        }
        System.out.println("logged in is " + loggedIn);
    }








    //This method will go back to the startView
    public void goTostartView(ActionEvent backClicked) throws IOException {

        //This method switches scene to primary
        Parent startViewParent = FXMLLoader.load(getClass().getResource("startView.fxml"));
        Scene startViewScene = new Scene(startViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) backClicked.getSource()).getScene().getWindow();
        window.setTitle("Login");
        window.setScene(startViewScene);
        window.show();

    }

}
