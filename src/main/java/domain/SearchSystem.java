package domain;

import java.io.*;
import java.util.*;

public class SearchSystem {

    //Use Sets so we dont get duplicates of programs/persons
    private Set programSet = new HashSet<Program>();
    private Set personSet = new HashSet<Person>();

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private String fileName;
    private ArrayList<Program> programList = null;

    SearchSystem() {
        fileName = "credits.dat";
    }

    public void createCredits() {
        //Create credit and program objects
        HashMap<Person, String> creditHashMap1 = new HashMap<>();
        creditHashMap1.put(new Person("John Doe", "Johndoe@gmail.com", 11223344), "Director");
        creditHashMap1.put(new Person("Pepper Doe", "Pepperdoe@gmail.com", 22334455), "Assistant director");

        Credit credit1 = new Credit(creditHashMap1);
        Program program1 = new Program("Rooftop Prince", credit1, "12/05/1999", "Jill Valentine");

        HashMap<Person, String> creditHashMap2 = new HashMap<>();
        creditHashMap2.put(new Person("Nanna Doe", "Nannadoe@gmail.com", 33445566), "Executive producer");
        creditHashMap2.put(new Person("Bob Doe", "Bobdoe@gmail.com", 44556677), "Music");

        Credit credit2 = new Credit(creditHashMap2);
        Program program2 = new Program("The Intouchables", credit2, "12/06/1994", "Lirik");

        //Put those programs into the array for the binary file.
        ArrayList<Program> programsArray = new ArrayList<>();
        programsArray.add(program1);
        programsArray.add(program2);

        //Make a outputStream to write credit array to the file
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(programsArray);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method to read the programArray from the binary file, thereafter print them out.
    public void readCredits() {
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

/*        for (Program e : programList) {
            System.out.println(e);
            System.out.println("\n");
        }*/
    }

    public void searchProgram(String keyWord)
    {
        for(Program e : programList)
        {
            if(e.getName().contains(keyWord))
            {
                System.out.println(e);
            }
        }

    }


    //A method for adding a program.
    public void addProgram(Program program) {

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


    public static void main(String[] args) {
/*      Person person1 = new Person("Jens","Jens@gmail.com",696969696);
      SearchSystem ss = new SearchSystem();
      ss.addPerson(person1);
      ss.addPerson(person1);
      System.out.println(ss.getPerson());*/

        SearchSystem ss = new SearchSystem();
        ss.createCredits();
        ss.readCredits();
        ss.searchProgram("Intouchables");
    }
}
