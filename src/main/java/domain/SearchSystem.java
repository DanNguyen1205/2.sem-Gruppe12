package domain;

import data.PersistenceHandler;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.*;

public class SearchSystem{

    //Use Sets so we dont get duplicates of programs and persons
    private Set programSet = new HashSet<Program>();
    private Set personSet = new HashSet<Person>();

    //Set up streams to write and read from the file
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private String fileName;
    private ArrayList<Program> programList = null;

    //PersistenceHandler that we call load and save methods from
    PersistenceHandler persistenceHandler = new PersistenceHandler();

    public SearchSystem() {
        fileName = "credits.dat";
    }

    //This method calls perstistence handler method to create the credits. Temporary because we just want credits to check on for now.
    public void createCredits() {
        persistenceHandler.createFiles();
        //persistenceHandler.createCredits();
    }

    //This method calls persistence handler method to load in the credits that we have created.
    public void loadCredits() {
        programList = persistenceHandler.loadCredits();
    }

    //This method saerches for a program and puts it into an array to return.
    public ArrayList<Program> searchProgram(String keyWord)
    {
        ArrayList<Program> tempArray = new ArrayList<>();
        for(Program e : programList)
        {
            if(e.getName().toLowerCase().contains(keyWord.toLowerCase()))
            {
                tempArray.add(e);
            }
        }
        return tempArray;
    }

    //A method for adding a program.
    public void addProgram(Program program) {
        persistenceHandler.addProduction(program);
    }

    //A method for adding a person.
    public void addPerson(Person person) {
        personSet.add(person);
    }


    //Setter and getter for program
    public Set<Program> getProgram() {
        return this.programSet;
    }

    public void setProgram() {
        this.programSet = programSet;

    }

    //Setter and getter for person
    public Set<Person> getPerson() {
        return this.personSet;
    }

    public void setPerson() {
        this.personSet = personSet;
    }

}
