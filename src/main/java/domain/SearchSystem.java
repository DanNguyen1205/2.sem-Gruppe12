package domain;

import data.PersistenceHandler;
import data.Person;
import data.Program;

import java.util.*;

public class SearchSystem{

    //Use Sets so we dont get duplicates of programs and persons
    private Set programSet = new HashSet<Program>();
    private Set personSet = new HashSet<Person>();

    private ArrayList<Program> programList = null;

    //PersistenceHandler that we call load and save methods from. Singleton object.
    PersistenceHandler persistenceHandler = PersistenceHandler.getInstance();

    public SearchSystem() {
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
