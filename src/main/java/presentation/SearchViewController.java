package presentation;

import domain.Program;
import domain.SearchSystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class SearchViewController implements Initializable {
    SearchSystem searchSystem;

    ObservableList<Program> observableList;
    @FXML
    private ListView<Program> listView;

    @FXML
    TextField searchField;
    @FXML
    TextArea creditArea;

    //This method will switch the scene back to the primary scene.
    public void exitButtonPushed(ActionEvent Clicked) throws IOException {

        Parent startViewParent = FXMLLoader.load(getClass().getResource("startView.fxml"));
        Scene startViewScene = new Scene(startViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) Clicked.getSource()).getScene().getWindow();
        window.setTitle("TV2 - Applikation");
        window.setScene(startViewScene);
        window.show();
    }

    @FXML
    public void Search()
    {
        observableList.clear();
        String keyWord = searchField.getText();
        ArrayList<Program> tempArray = searchSystem.searchProgram(keyWord);

        for(Program e : tempArray)
        {
            observableList.add(e);
        }

    }

    @FXML
    public void programClickedOn (MouseEvent event)
    {
        creditArea.setText(listView.getSelectionModel().getSelectedItem().showCredit());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initiate the SearchSystem object so we can create and read the credits.
        searchSystem =  new SearchSystem();
        searchSystem.createCredits();
        searchSystem.readCredits();

        observableList = FXCollections.observableArrayList();
        listView.setItems(observableList);
    }
}

