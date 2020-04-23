package presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProducerLoginController {

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
