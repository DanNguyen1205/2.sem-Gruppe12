package presentation;

import domain.Credit;
import domain.Person;
import domain.Program;
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
import java.util.Map;
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
    TextField phoneField;
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


    Program program;
    Person person;
    Credit credit;
    ObjectOutputStream outputStream;

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
            if (titleField.getText().isEmpty() || producerField.getText().isEmpty() || releaseDateField.getText().isEmpty()) {
                productionLabel.setText("Insert all fields please. Dumbfuck");
            } else {
                productionLabel.setText("");
                String title = titleField.getText();
                String producer = producerField.getText();
                String releaseDate = releaseDateField.getText();
                program = new Program(title, producer, releaseDate);

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
            if (personNameField.getText().isEmpty() || roleField.getText().isEmpty() || emailField.getText().isEmpty() || phoneField.getText().isEmpty()) {
                creditLabel.setText("Insert all fields");
            } else {
                String name = personNameField.getText();
                String role = roleField.getText();
                String email = emailField.getText();
                String phoneNumber = phoneField.getText();

                person = new Person(name, email, phoneNumber);
                person.setRole(role);

                observableList.add(person);
            }
        }
        //If the edit button is pressed for credits
        if (event.getSource() == editCreditButton) {
            String name = personNameField.getText();
            String role = roleField.getText();
            String email = emailField.getText();
            String phoneNumber = phoneField.getText();

            Person selectedPerson = listView.getSelectionModel().getSelectedItem();
            observableList.remove(selectedPerson);

            selectedPerson.setName(name);
            selectedPerson.setRole(role);
            selectedPerson.setEmail(email);
            selectedPerson.setPhoneNumber(phoneNumber);
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
        //Use this HashMap to make a credit to put inside a program.
        HashMap<Person, String> tempMap = new HashMap<Person, String>();

        for(Person e : observableList)
        {
            tempMap.put(e, e.getRole());
        }

        //Make the program
        credit = new Credit();
        credit.setCreditMap(tempMap);
        program.setCredits(credit);

        //Send the program to the temp binary file that the admin has to confirm
        try {
            outputStream.writeObject(program);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done submitting...");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableList = FXCollections.observableArrayList();
        listView.setItems(observableList);

        editProductionButton.setDisable(true);

        //Make a new binary file to put in new programs and their credits. This binary file needs to be checked by a system adminstrator
        // before they are allowed into the main binary file that the search system uses.
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("temporary.dat", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
