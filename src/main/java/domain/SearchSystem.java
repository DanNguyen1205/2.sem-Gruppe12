package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
public class SearchSystem {

    Set programSet = new HashSet<Program>();
    Set personSet = new HashSet<Person>();

SearchSystem(){


}

//A method for adding a program.
    public void addProgram(Program program){

    }

    //A method for adding a person.
    public void addPerson(Person person){
    personSet.add(person);



    }


    //Setter and getter for program
    public Set<Program> getProgram(){
    return this.programSet;
    }

    public void setProgram(){
    this.programSet = programSet;

      }

      //Setter and getter for person
    public Set<Person> getPerson(){
    return this.personSet;
    }

    public void setPerson(){
    this.personSet = personSet;
    }


      public static void main(String[]args){
      Person pik = new Person("Pikhoved","tarikbøsse@gmail.com",696969696);
      SearchSystem pik2 = new SearchSystem();
      pik2.addPerson(pik);
      pik2.addPerson(pik);
          System.out.println(pik2.getPerson());

      }



    }
