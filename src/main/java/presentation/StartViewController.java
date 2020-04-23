package presentation;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartViewController {

    public void goNextPushed(ActionEvent goNextClicked) throws IOException {

        Parent AdminViewParent = FXMLLoader.load(getClass().getResource("SearchView.fxml"));
        Scene AdminViewScene = new Scene(AdminViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) goNextClicked.getSource()).getScene().getWindow();
        window.setTitle("Søg på et program");
        window.setScene(AdminViewScene);
        window.show();

    }


    public void switchToPrimary(ActionEvent LoginClicked) throws IOException {

        //This method switches scene to primary
        Parent primaryViewParent = FXMLLoader.load(getClass().getResource("primary.fxml"));
        Scene primaryViewScene = new Scene(primaryViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) LoginClicked.getSource()).getScene().getWindow();
        window.setTitle("Log ind som administrator");
        window.setScene(primaryViewScene);
        window.show();

    }
    public void switchToProducerLogin(ActionEvent LoginClicked) throws IOException {

        //This method switches scene to producerlogin
        Parent producerLoginViewParent = FXMLLoader.load(getClass().getResource("producerLoginView.fxml"));
        Scene producerLoginViewScene = new Scene(producerLoginViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) LoginClicked.getSource()).getScene().getWindow();
        window.setTitle("Log ind som producer");
        window.setScene(producerLoginViewScene);
        window.show();

    }

}

