package data;

import domain.Credit;
import domain.Person;
import domain.Program;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PersistenceHandler {
    //Setting up streams to read and write to our files
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private String fileName = "credits.dat";

    //Array to hold the programs from the file
    private ArrayList<Program> programList = null;

    ArrayList<Program> programsArray = new ArrayList<>();

    Credit credit;
    Program program;



    //This method creates credits
    public void createCredits() {
        //Create credit and program objects
        HashMap<Person, String> creditHashMap1 = new HashMap<>();
        creditHashMap1.put(new Person("John Doe", "Johndoe@gmail.com", "11223344"), "Director");
        creditHashMap1.put(new Person("Pepper Doe", "Pepperdoe@gmail.com", "22334455"), "Assistant director");

        Credit credit1 = new Credit(creditHashMap1);
        Program program1 = new Program("Rooftop Prince", credit1, "12/05/1999", "Jill Valentine");

        HashMap<Person, String> creditHashMap2 = new HashMap<>();
        creditHashMap2.put(new Person("Nanna Doe", "Nannadoe@gmail.com", "33445566"), "Executive producer");
        creditHashMap2.put(new Person("Bob Doe", "Bobdoe@gmail.com", "44556677"), "Music");

        Credit credit2 = new Credit(creditHashMap2);
        Program program2 = new Program("The Intouchables", credit2, "12/06/1994", "Lirik");

        //Put those programs into the array for the binary file.
        programsArray.add(program1);
        programsArray.add(program2);

        //Make a outputStream to write credit array to the file
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(fileName, true));
            //outputStream.writeObject(programsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFiles()
    {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //Reads the array from the binary file and returns it.
    public ArrayList<Program> loadCredits() {
        //Make inputStream to read objects from the file
        try {
            inputStream = new ObjectInputStream(new FileInputStream(fileName));
            programList = (ArrayList<Program>) inputStream.readObject();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return programList;

        //Commented out but it reads the programs we have loaded in.
/*        for (Program e : programList) {
            System.out.println(e);
            System.out.println("\n");
        }*/
    }



    //Method for adding a production
    public void addProduction(Program program)
    {
        //Load in the credits before we overwrite whats inside.
        programsArray = loadCredits();

        //Create stream so we can write to the file. Deletes everything in the binary file.
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Add the program we want, since we loaded everything is still intact.
        programsArray.add(program);
        //Print everything to check we got the all the content
        for(Program e : programsArray)
        {
            System.out.println(e);
        }

        //Finally write the array into the binary file
        try {
            outputStream.writeObject(programsArray);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done submitting...");
    }



}
