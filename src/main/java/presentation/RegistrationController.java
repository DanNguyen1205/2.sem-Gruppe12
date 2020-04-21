package presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationController {

    //This method will change the scene from the loginScene to the registrationView
    public void registerButtonPushed(ActionEvent registerClicked) throws IOException {

        Parent registerViewParent = FXMLLoader.load(getClass().getResource("primary.fxml"));
        Scene registerViewScene = new Scene(registerViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) registerClicked.getSource()).getScene().getWindow();
        window.setTitle("TV2 - Applikation");
        window.setScene(registerViewScene);
        window.show();

    }

    //Change scene back from register to loginpage.
    public void backToLoginPushed(ActionEvent backToLoginClicked) throws IOException {

        Parent loginViewParent = FXMLLoader.load(getClass().getResource("primary.fxml"));
        Scene loginViewScene = new Scene(loginViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) backToLoginClicked.getSource()).getScene().getWindow();
        window.setTitle("Login");
        window.setScene(loginViewScene);
        window.show();

    }

}
