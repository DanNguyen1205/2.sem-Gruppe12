package presentation;

import data.Credit;
import data.Person;
import data.Program;
import domain.CreditFactory;
import domain.ProgramFactory;
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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ProducerProduktionController implements Initializable {

    @FXML
    TextField titleField;
    @FXML
    TextField producerField;
    @FXML
    TextField releaseDateField;
    @FXML
    Button insertProductionButton;
    @FXML
    Button editProductionButton;
    @FXML
    Label productionLabel;
    @FXML
    TextArea productionArea;

    @FXML
    TextField personNameField;
    @FXML
    TextField roleField;
    @FXML
    TextField emailField;
    @FXML
    Button insertCreditButton;
    @FXML
    Button editCreditButton;
    @FXML
    Button deleteCreditButton;
    @FXML
    Label creditLabel;

    @FXML
    Button submitButton;

    //Attributes for creation of credits and programs
    Program program;
    Person person;
    Credit credit;
    ProgramFactory pf;
    CreditFactory cf;

    SearchSystem ss;

    ObservableList<Person> observableList;
    @FXML
    private ListView<Person> listView;

    public void GoBack(ActionEvent Clicked) throws IOException {

        Parent newProductionViewParent = FXMLLoader.load(getClass().getResource("producerView.fxml"));
        Scene newProductionViewScene = new Scene(newProductionViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) Clicked.getSource()).getScene().getWindow();
        window.setTitle("Logged ind som producer");
        window.setScene(newProductionViewScene);
        window.show();
    }

    @FXML
    public void productionEventHandler(ActionEvent event) {
        //If you press the insert button for production
        if (event.getSource() == insertProductionButton) {
            //Checks if all the text fields are fileld out
            if (titleField.getText().isEmpty() || producerField.getText().isEmpty() || releaseDateField.getText().isEmpty()) {
                productionLabel.setText("Insert all fields please.");
                //If they are filled out then make a new program with the information.
            } else {
                productionLabel.setText("");
                String title = titleField.getText();
                String producer = producerField.getText();
                String releaseDate = releaseDateField.getText();

                //Use program factory to make a new program
                program = pf.createProgram(title, releaseDate, producer);


                //Print the inserted program out in a textarea
                productionArea.setText("Titel: " + program.getName() + "\nProducer: " + program.getProducer() + "\nUdgivelses Dato:  " + program.getReleaseDate());

                //Switcheroo where insert disable and edit enable.
                insertProductionButton.setDisable(true);
                editProductionButton.setDisable(false);
            }
        }
        //If you press the edit button for production
        if (event.getSource() == editProductionButton) {
            System.out.println("test test test");
            program.setName(titleField.getText());
            program.setProducer(producerField.getText());
            program.setReleaseDate(releaseDateField.getText());
            productionArea.setText("Titel: " + program.getName() + "\nProducer: " + program.getProducer() + "\nUdgivelses Dato:  " + program.getReleaseDate());
        }
    }

    @FXML
    public void creditEventHandler(ActionEvent event) {
        //If the insert button is pressed for credit
        if (event.getSource() == insertCreditButton) {
            //If all the fields are not filled out
            if (personNameField.getText().isEmpty() || roleField.getText().isEmpty() || emailField.getText().isEmpty()) {
                creditLabel.setText("Insert all fields");
                //If the fields are filled out update the observable list with the persons.
            } else {
                String name = personNameField.getText();
                String role = roleField.getText();
                String email = emailField.getText();

                Person newPerson = new Person(name,email, role);

                observableList.add(newPerson);
            }
        }
        //If the edit button is pressed for credits
        if (event.getSource() == editCreditButton) {
            String name = personNameField.getText();
            String email = emailField.getText();
            String role = roleField.getText();


            Person selectedPerson = listView.getSelectionModel().getSelectedItem();
            observableList.remove(selectedPerson);

            selectedPerson.setName(name);
            selectedPerson.setRole(role);
            selectedPerson.setEmail(email);

            observableList.add(selectedPerson);
        }
        //If the delete button is pressed
        if (event.getSource() == deleteCreditButton) {
            Person selectedPerson = listView.getSelectionModel().getSelectedItem();
            observableList.remove(selectedPerson);
        }
    }

    public void submitButton()
    {
        //Use creditFactory and programFactory methods to create a credit to put inside a program.
        credit = cf.createCredit(cf.createCreditMap(observableList));
        pf.setCreditToProgram(credit, program);

        //Show that we've actually made a credit.
        System.out.println(program.showCredit());

        //add the program to the binary file
        pf.addProgram(program);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initialize the factories so we can create programs and credits.
        pf = new ProgramFactory();
        cf = new CreditFactory();

        ss = new SearchSystem();

        observableList = FXCollections.observableArrayList();
        listView.setItems(observableList);

        editProductionButton.setDisable(true);
    }
}
