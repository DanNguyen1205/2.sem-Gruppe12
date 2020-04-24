package presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProducerViewController{

    public void exitFromProducerPushed(ActionEvent Clicked) throws IOException {

        Parent startViewParent = FXMLLoader.load(getClass().getResource("startView.fxml"));
        Scene startViewScene = new Scene(startViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) Clicked.getSource()).getScene().getWindow();
        window.setTitle("TV2 - Applikation");
        window.setScene(startViewScene);
        window.show();
    }

    public void newProductionPushed(ActionEvent Clicked) throws IOException {

        Parent newProductionViewParent = FXMLLoader.load(getClass().getResource("producerNewProductionView.fxml"));
        Scene newProductionViewScene = new Scene(newProductionViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) Clicked.getSource()).getScene().getWindow();
        window.setTitle("Indsæt ny produktion");
        window.setScene(newProductionViewScene);
        window.show();
    }
}
