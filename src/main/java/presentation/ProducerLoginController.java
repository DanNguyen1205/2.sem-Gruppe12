package presentation;

import data.Account;
import domain.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ProducerLoginController{
    Login login = new Login();

    @FXML
    TextField ProducerID, ProducerKodeID;
    @FXML
    Button logIndProducerBtn;
    @FXML
    Label notifierlbl;


    @FXML
    private void ProducerLogin(ActionEvent loginClicked)throws IOException {
        //Use login object to make accounts
        String inputUserID = this.ProducerID.getText();
        String inputPasswordID = this.ProducerKodeID.getText();

        Parent producerViewParent = FXMLLoader.load(getClass().getResource("producerView.fxml"));
        Scene producerViewScene = new Scene(producerViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) loginClicked.getSource()).getScene().getWindow();


        if(login.checkIfLoginCorrect(inputUserID, inputPasswordID))
        {
            window.setTitle("Logget ind som producer");
            window.setScene(producerViewScene);
            window.show();
            System.out.println("Logged in successful");
        }
        else
        {
            System.out.println("Logged in failed.");
            notifierlbl.setText("Forkert kodeord! Prøv igen");
        }

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
