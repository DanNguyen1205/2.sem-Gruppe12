package domain;

import data.PersistenceHandler;
import data.Person;
import data.Program;
import data.PersonToCredit;

import java.util.*;

public class SearchSystem{
    ArrayList<Person> personArrayList = new ArrayList<>();
    ArrayList<Program> programArrayList = new ArrayList<>();
    ArrayList<PersonToCredit> personToCreditsArrayList = new ArrayList<>();

    //PersistenceHandler that we call load and save methods from. Singleton object.
    PersistenceHandler persistenceHandler = PersistenceHandler.getInstance();

    public SearchSystem() {
    }


    //This method calls persistence handler method to load in the credits that we have created.
    public void completeLoad() {
        programArrayList.clear();
        personArrayList.clear();
        personToCreditsArrayList.clear();

        programArrayList = persistenceHandler.loadPrograms();
        personArrayList = persistenceHandler.loadPersons();
        personToCreditsArrayList = persistenceHandler.loadPersonToCredit();

        //Loop through productiosnArrayList
        for(Program programElement : programArrayList)
        {
            System.out.println("For program: " + programElement.getName());
            //Loop through personToCreditsArrayList to find matching id's
            for(PersonToCredit personToCreditElement : personToCreditsArrayList)
            {
                //If the id's match then loop through personArrayList to find persons_fk and persons id
                if(programElement.getId() == personToCreditElement.getProductions_fk())
                {
                    //looping through persons
                    for(Person personElement : personArrayList)
                    {
                        if(personElement.getId() == personToCreditElement.getPersons_fk())
                        {
                            Person newPerson = new Person(personElement.getName(), personElement.getEmail());
                            newPerson.setId(personElement.getId());

                            System.out.println("Adding " + personElement.getName() + " with role: " + personToCreditElement.getRole());
                            newPerson.setRole(personToCreditElement.getRole());
                            programElement.getPersonArrayList().add(newPerson);
                        }
                    }
                }
            }
        }
    }

    //This method saerches for a program and puts it into an array to return.
    public ArrayList<Program> searchProgram(String keyWord)
    {
        ArrayList<Program> returnArray = new ArrayList<>();
        for(Program e : programArrayList)
        {
            if(e.getName().toLowerCase().contains(keyWord.toLowerCase()))
            {
                returnArray.add(e);
            }
        }
        return returnArray;
    }
}
