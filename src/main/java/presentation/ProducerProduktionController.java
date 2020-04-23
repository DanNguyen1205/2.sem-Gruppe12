package presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProducerProduktionController {
    public void GoBack(ActionEvent Clicked) throws IOException {

        Parent newProductionViewParent = FXMLLoader.load(getClass().getResource("producerView.fxml"));
        Scene newProductionViewScene = new Scene(newProductionViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) Clicked.getSource()).getScene().getWindow();
        window.setTitle("Logged ind som producer");
        window.setScene(newProductionViewScene);
        window.show();
    }


}
