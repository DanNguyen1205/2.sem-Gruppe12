package presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;




public class SearchViewController {
    //This method will switch the scene back to the primary scene.
    public void changeSceneLogoutButtonPushed(ActionEvent logoutClicked) throws IOException {

        Parent loginViewParent = FXMLLoader.load(getClass().getResource("primary.fxml"));
        Scene loginViewScene = new Scene(loginViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) logoutClicked.getSource()).getScene().getWindow();
        window.setTitle("TV2 - Applikation");
        window.setScene(loginViewScene);
        window.show();


    }

}

